package com.example.notificationService.NotificationService.entity.es;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

import javax.persistence.Id;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = "es_data")
public class EsData {

    @Id
    @Field(type = FieldType.Keyword)
    private int id;

    @Field(type = FieldType.Keyword)
    private String phoneNumber;

    @MultiField(
        mainField = @Field(type = FieldType.Text, fielddata = true),
        otherFields = {
                @InnerField(suffix = "raw", type = FieldType.Keyword)
        }
    )
    private String message;

    @Field(type = FieldType.Date)
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date createdAt;

}
