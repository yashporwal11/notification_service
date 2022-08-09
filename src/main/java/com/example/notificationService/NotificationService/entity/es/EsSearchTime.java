package com.example.notificationService.NotificationService.entity.es;

import com.example.notificationService.NotificationService.entity.enums.SortByType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EsSearchTime {

    private static final int DEFAULT_PAGE_SIZE = 100;

    @JsonProperty("phone_number")
    @Pattern(regexp = "^((\\+){1}91){1}[1-9]{1}[0-9]{9}$", message = "phone_number should start with +91 and should have 10 more digits")
    private String phoneNumber;

    @JsonProperty("start_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private Date endTime;

    @Enumerated(EnumType.STRING)
    @JsonProperty("sort_by")
    private SortByType sortBy;

    @JsonProperty("sort_order")
    private SortOrder sortOrder;

    @JsonProperty("page_number")
    @Positive(message = "page_number should be a positive integer")
    private int pageNumber;

    @JsonProperty("page_size")
    @Positive(message = "page_size should be a positive integer")
    private int pageSize;

    public int getPageSize(){
        return (pageSize != 0 ? pageSize : DEFAULT_PAGE_SIZE);
    }
}
