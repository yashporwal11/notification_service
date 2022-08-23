package com.example.notificationService.NotificationService.entity.es;

import com.example.notificationService.NotificationService.constant.ElasticsearchConstants;
import com.example.notificationService.NotificationService.constant.UtilConstants;
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
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EsSearchTime {

    @JsonProperty("phone_number")
    @Pattern(regexp = "^((\\+){1}91){1}[1-9]{1}[0-9]{9}$", message = "phone_number should start with +91 and should have 10 more digits")
    private String phoneNumber;

    @JsonProperty("start_time")
    @JsonFormat(pattern = UtilConstants.DATE_TIME_PATTERN)
    private Date startTime;

    @JsonProperty("end_time")
    @JsonFormat(pattern = UtilConstants.DATE_TIME_PATTERN)
    private Date endTime;

    @Enumerated(EnumType.STRING)
    @JsonProperty("sort_by")
    private SortByType sortBy;

    @JsonProperty("sort_order")
    private SortOrder sortOrder;

    @JsonProperty("page_number")
    private int pageNumber;

    @JsonProperty("page_size")
    private int pageSize;

    public int getPageSize(){
        return (pageSize != 0 ? pageSize : ElasticsearchConstants.DEFAULT_PAGE_SIZE);
    }

    public int getPageNumber() {
        return (pageNumber != 0 ? pageNumber : ElasticsearchConstants.DEFAULT_PAGE_NUMBER);
    }
}
