package com.example.notificationService.NotificationService.controller.esController;

import com.example.notificationService.NotificationService.constant.ElasticsearchConstants;
import com.example.notificationService.NotificationService.entity.es.EsData;
import com.example.notificationService.NotificationService.entity.es.EsSearchText;
import com.example.notificationService.NotificationService.entity.es.EsSearchTime;
import com.example.notificationService.NotificationService.response.GenericResponse;
import com.example.notificationService.NotificationService.service.esService.EsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class EsController {

    private final EsService esService;

    @Autowired
    public EsController(EsService esService){
        this.esService = esService;
    }

    @GetMapping(ElasticsearchConstants.SEARCH_BY_TEXT_ENDPOINT)
    public ResponseEntity searchByText(@Valid @RequestBody EsSearchText esSearchText){

        List<EsData> esData = esService.searchByText(esSearchText);

        GenericResponse<List<EsData>> response = new GenericResponse<>();
        response.setData(esData);

        return ResponseEntity.ok().body(response);
    }

    @GetMapping(ElasticsearchConstants.SEARCH_BY_TIME_ENDPOINT)
    public ResponseEntity searchByTime(@Valid @RequestBody EsSearchTime esSearchTime){

        List<EsData> esData = esService.searchByTime(esSearchTime);

        GenericResponse<List<EsData>> response = new GenericResponse<>();
        response.setData(esData);

        return ResponseEntity.ok().body(response);
    }

}
