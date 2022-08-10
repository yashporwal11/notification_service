package com.example.notificationService.NotificationService.service.esService;

import com.example.notificationService.NotificationService.entity.es.EsData;
import com.example.notificationService.NotificationService.entity.es.EsSearchText;
import com.example.notificationService.NotificationService.entity.es.EsSearchTime;
import com.example.notificationService.NotificationService.exception.InvalidRequestException;
import com.example.notificationService.NotificationService.exception.NotFoundException;
import com.example.notificationService.NotificationService.repository.EsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class EsService {
    private final EsRepository esRepository;

    @Autowired
    public EsService(EsRepository esRepository) {
        this.esRepository = esRepository;
    }

    @Autowired
    private ObjectMapper mapper;
    @Autowired
    private RestHighLevelClient restHighLevelClient;

    public void save(final EsData esData) {
        esRepository.save(esData);
    }

    public EsData findById(final int id) {
        return esRepository.findById(id).orElse(null);
    }

    public List<EsData> searchByText(EsSearchText esSearchText) {
        try {

            int page = esSearchText.getPageNumber();
            int size = esSearchText.getPageSize();
            int from = page <= 0 ? 0 : (page-1) * size;

            if(page<=0){
                throw new InvalidRequestException("page_number should be a positive integer");
            }

            if(size <=0){
                throw new InvalidRequestException("page_size should be a positive integer");
            }

            QueryBuilder textQuery = QueryBuilders.matchQuery("message",esSearchText.getSearchText()).operator(Operator.AND);
            SearchSourceBuilder builder = new SearchSourceBuilder().from(from).size(size).query(textQuery);

            if (esSearchText.getSortBy() != null) {
                builder = builder.sort(
                        esSearchText.getSortBy().value,
                        esSearchText.getSortOrder() != null ? esSearchText.getSortOrder() : SortOrder.ASC
                );
            }

            SearchRequest request = new SearchRequest("es_data");
            request.source(builder);
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

            if(response.getHits().getHits().length==0){
                throw new NotFoundException("No data found");
            }

            SearchHit[] searchHits = response.getHits().getHits();

            List<EsData> esDataReceived = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                esDataReceived.add(
                        mapper.readValue(hit.getSourceAsString(), EsData.class)
                );
            }
            return esDataReceived;
        }
        catch (NotFoundException e)
        {
            throw new NotFoundException(e.getMessage());
        }
        catch (InvalidRequestException e){
            throw new InvalidRequestException(e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<EsData> searchByTime(EsSearchTime esSearchTime){
        try {
            int page = esSearchTime.getPageNumber();
            int size = esSearchTime.getPageSize();
            int from = page <= 0 ? 0 : (page-1) * size;

            if(page<=0){
                throw new InvalidRequestException("page_number should be a positive integer");
            }

            if(size <=0){
                throw new InvalidRequestException("page_size should be a positive integer");
            }

            QueryBuilder searchQuery = QueryBuilders.termQuery("phoneNumber", esSearchTime.getPhoneNumber());
            QueryBuilder dateQuery = QueryBuilders.rangeQuery("createdAt").gte(esSearchTime.getStartTime()).lte(esSearchTime.getEndTime());
            BoolQueryBuilder boolQuery = new BoolQueryBuilder();
            boolQuery.filter(searchQuery);
            boolQuery.filter(dateQuery);

            SearchSourceBuilder builder = new SearchSourceBuilder().from(from).size(size).query(boolQuery);

            if (esSearchTime.getSortBy() != null) {
                builder = builder.sort(
                        esSearchTime.getSortBy().value,
                        esSearchTime.getSortOrder() != null ? esSearchTime.getSortOrder() : SortOrder.ASC
                );
            }

            SearchRequest request = new SearchRequest("es_data");
            request.source(builder);
            SearchResponse response = restHighLevelClient.search(request, RequestOptions.DEFAULT);

            if(response.getHits().getHits().length==0){
                throw new NotFoundException("No data found");
            }

            SearchHit[] searchHits = response.getHits().getHits();

            List<EsData> esDataReceived = new ArrayList<>(searchHits.length);
            for (SearchHit hit : searchHits) {
                esDataReceived.add(
                        mapper.readValue(hit.getSourceAsString(), EsData.class)
                );
            }
            return esDataReceived;
        }
        catch (NotFoundException e)
        {
            throw new NotFoundException(e.getMessage());
        }
        catch (InvalidRequestException e){
            throw new InvalidRequestException(e.getMessage());
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
