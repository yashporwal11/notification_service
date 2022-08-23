package com.example.notificationService.NotificationService.entity.es;

import com.example.notificationService.NotificationService.constant.ElasticsearchConstants;
import com.example.notificationService.NotificationService.entity.enums.SortByType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EsSearchText {

    @JsonProperty("search_text")
    private String searchText;

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
