package com.example.notificationService.NotificationService.entity.es;

import com.example.notificationService.NotificationService.entity.enums.SortByType;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.elasticsearch.search.sort.SortOrder;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Positive;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EsSearchText {
    private static final int DEFAULT_PAGE_SIZE = 100;

    @JsonProperty("search_text")
    private String searchText;

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
