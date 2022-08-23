package com.example.notificationService.NotificationService.entity.es;

import com.example.notificationService.NotificationService.constant.ElasticsearchConstants;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.elasticsearch.annotations.*;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(indexName = ElasticsearchConstants.INDEX_NAME)
public class EsData {

    private int id;

    private String phoneNumber;

    private String message;

    private Date createdAt;
}
