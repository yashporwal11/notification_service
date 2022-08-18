package com.example.notificationService.NotificationService.controller.esController;

import com.example.notificationService.NotificationService.constant.ElasticsearchConstants;
import com.example.notificationService.NotificationService.entity.es.EsData;
import com.example.notificationService.NotificationService.entity.es.EsSearchText;
import com.example.notificationService.NotificationService.entity.es.EsSearchTime;
import com.example.notificationService.NotificationService.response.GenericResponse;
import com.example.notificationService.NotificationService.service.esService.EsService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@Slf4j
public class EsController {

    private final EsService esService;

    @GetMapping(value = ElasticsearchConstants.SEARCH_BY_TEXT_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchByText(@Valid @RequestBody EsSearchText esSearchText){

        log.info("EsSearchText received is: " + esSearchText);

        List<EsData> esData = esService.searchByText(esSearchText);

        GenericResponse<List<EsData>> response = new GenericResponse<>();
        response.setData(esData);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(value = ElasticsearchConstants.SEARCH_BY_TIME_ENDPOINT, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity searchByTime(@Valid @RequestBody EsSearchTime esSearchTime){

        log.info("EsSearchTime received is: " + esSearchTime);

        List<EsData> esData = esService.searchByTime(esSearchTime);

        GenericResponse<List<EsData>> response = new GenericResponse<>();
        response.setData(esData);

        return ResponseEntity.ok().body(response);
    }
}
