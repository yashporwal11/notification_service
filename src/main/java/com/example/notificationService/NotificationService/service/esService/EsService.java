package com.example.notificationService.NotificationService.service.esService;

import com.example.notificationService.NotificationService.constant.ErrorConstants;
import com.example.notificationService.NotificationService.entity.es.EsData;
import com.example.notificationService.NotificationService.entity.es.EsSearchText;
import com.example.notificationService.NotificationService.entity.es.EsSearchTime;
import com.example.notificationService.NotificationService.exception.InvalidRequestException;
import com.example.notificationService.NotificationService.exception.NotFoundException;
import com.example.notificationService.NotificationService.repository.EsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.Operator;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class EsService {

    private final ObjectMapper mapper;
    private final RestHighLevelClient restHighLevelClient;
    @Value("${elasticsearch.index.name}")
    private String indexName;

    public int getAndValidatePageNumber(int page){
        if (page <= 0) {
            throw new InvalidRequestException(ErrorConstants.INVALID_PAGE_NUMBER_ERROR);
        }
        return page;
    }

    public int getAndValidatePageSize(int size){
        if (size <= 0) {
            throw new InvalidRequestException(ErrorConstants.INVALID_PAGE_SIZE_ERROR);
        }
        return size;
    }

    public List<EsData> searchByText(EsSearchText esSearchText) {
        try {

            int page = getAndValidatePageNumber(esSearchText.getPageNumber());
            int size = getAndValidatePageSize(esSearchText.getPageSize());
            int from = page <= 0 ? 0 : (page - 1) * size;

            QueryBuilder textQuery = QueryBuilders.matchQuery("message", esSearchText.getSearchText()).operator(Operator.AND);
            SearchSourceBuilder builder = new SearchSourceBuilder().from(from).size(size).query(textQuery);

            if (esSearchText.getSortBy() != null) {
                builder = builder.sort(esSearchText.getSortBy().value, esSearchText.getSortOrder() != null ? esSearchText.getSortOrder() : SortOrder.ASC);
            }

            SearchRequest request = new SearchRequest(indexName);
            request.source(builder);
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

            if (response.getHits().getHits().length == 0) {
                throw new NotFoundException(ErrorConstants.NO_DATA_FOUND_ERROR);
            }

            SearchHit[] searchHits = response.getHits().getHits();

            List<EsData> esDataReceived = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                esDataReceived.add(mapper.readValue(hit.getSourceAsString(), EsData.class));
            }
            return esDataReceived;
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getLocalizedMessage(),ex);
        }
    }

    public List<EsData> searchByTime(EsSearchTime esSearchTime) {
        try {
            int page = getAndValidatePageNumber(esSearchTime.getPageNumber());
            int size = getAndValidatePageSize(esSearchTime.getPageSize());
            int from = page <= 0 ? 0 : (page - 1) * size;

            QueryBuilder searchQuery = QueryBuilders.termQuery("phoneNumber", esSearchTime.getPhoneNumber());
            QueryBuilder dateQuery = QueryBuilders.rangeQuery("createdAt").gte(esSearchTime.getStartTime()).lte(esSearchTime.getEndTime());
            BoolQueryBuilder boolQuery = new BoolQueryBuilder();
            boolQuery.filter(searchQuery);
            boolQuery.filter(dateQuery);

            SearchSourceBuilder builder = new SearchSourceBuilder().from(from).size(size).query(boolQuery);

            if (esSearchTime.getSortBy() != null) {
                builder = builder.sort(esSearchTime.getSortBy().value, esSearchTime.getSortOrder() != null ? esSearchTime.getSortOrder() : SortOrder.ASC);
            }

            SearchRequest request = new SearchRequest(indexName);
            request.source(builder);
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

            if (response.getHits().getHits().length == 0) {
                throw new NotFoundException(ErrorConstants.NO_DATA_FOUND_ERROR);
            }

            SearchHit[] searchHits = response.getHits().getHits();

            List<EsData> esDataReceived = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                esDataReceived.add(mapper.readValue(hit.getSourceAsString(), EsData.class));
            }
            return esDataReceived;
        } catch (NotFoundException ex) {
            throw new NotFoundException(ex.getMessage());
        } catch (IOException ex) {
            throw new RuntimeException(ex.getLocalizedMessage(),ex);
        }
    }
}
