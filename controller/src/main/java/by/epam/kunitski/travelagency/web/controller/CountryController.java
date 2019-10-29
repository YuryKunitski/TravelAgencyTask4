package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.exeption.EntityNotFoundException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Lists;
import com.sun.net.httpserver.Authenticator;
import org.elasticsearch.action.DocWriteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.support.replication.ReplicationResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.xcontent.XContentType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin("*")
@RestController
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @Autowired
    private RestHighLevelClient client;

    @PostMapping("/index")
    public void index() throws IOException {

//        IndexRequest request = new IndexRequest("countries");
//        request.id("1");
//        String jsonString = "{" +
//                "\"name\":\"Laplandia\"" +
//                "}";
//        request.source(jsonString, XContentType.JSON);

        Map<String, Object> jsonMap = new HashMap<>();
        jsonMap.put("name", "Laplandia");
        IndexRequest indexRequest = new IndexRequest("countries")
                .id("1").source(jsonMap);

        IndexResponse indexResponse = client.index(indexRequest, RequestOptions.DEFAULT);
        System.out.println("countries response index: - "+ indexResponse.getIndex());
        System.out.println("countries response id: - "+ indexResponse.getId());
        System.out.println("countries response Shard id: - "+ indexResponse.getShardId());


        if (indexResponse.getResult() == DocWriteResponse.Result.CREATED) {
            System.out.println("--------------------- Created");
        } else if (indexResponse.getResult() == DocWriteResponse.Result.UPDATED) {
            System.out.println("--------------------- Updated");
        }
        ReplicationResponse.ShardInfo shardInfo = indexResponse.getShardInfo();
        if (shardInfo.getTotal() != shardInfo.getSuccessful()) {
            System.out.println("------------------------------ Handle the situation where number of successful shards is less than total shards");
        }
        if (shardInfo.getFailed() > 0) {
            for (ReplicationResponse.ShardInfo.Failure failure :
                    shardInfo.getFailures()) {
                String reason = failure.reason();
                System.out.println("Potential failures - "+ reason);
            }
        }

        //        Map<String, Object> message = new HashMap<>();
//        message.put("type", "text");
//
//        Map<String, Object> keyWordMap = new HashMap<>();
//        Map<String, Object> keyWordValueMap = new HashMap<>();
//        keyWordValueMap.put("type", "keyword");
//        keyWordValueMap.put("ignore_above", 256);
//        keyWordMap.put("keyword", keyWordValueMap);
//        message.put("fields", keyWordMap);
//
//        Map<String, Object> properties = new HashMap<>();
//        properties.put("id", message);
//        properties.put("name", message);
//
//        Map<String, Object> mapping = new HashMap<>();
//        mapping.put("properties", properties);
//        request.mapping(mapping);



//        GetIndexRequest getIndexRequest = new GetIndexRequest("countries");
//        boolean exists = client.indices().exists(getIndexRequest, RequestOptions.DEFAULT);
//        if (!exists) {
//            CreateIndexResponse indexResponse = client.indices().create(request, RequestOptions.DEFAULT);
//            System.out.println("response id: " + indexResponse.index());
//        }
    }

    @GetMapping("/{id}")
    public Country read(@PathVariable final String id) throws IOException {
        GetRequest getRequest = new GetRequest("countries", id);
        GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
        Country country = new ObjectMapper().readValue(getResponse.getSourceAsString(), Country.class);

        return country;
    }

    @Secured("ROLE_ADMIN")
    @PostMapping
    public ResponseEntity<Country> addCountry(@Valid @RequestBody Country country) {

        return ResponseEntity.ok(countryService.add(country));
    }

    @GetMapping("/get_by_id")
    public ResponseEntity<Country> getCountryById(@RequestParam String id) {

        return ResponseEntity.ok(countryService.getById(id).orElseThrow());
    }

    @GetMapping("get_all")
    public ResponseEntity<List<Country>> getAllCountry() {

        return ResponseEntity.ok(countryService.getAll());
    }

    @GetMapping("get_all_pagination")
    public List<Country> getAllCountryByPagination(@RequestParam Integer page) {
        Pageable pageable = PageRequest.of(page, 3, Sort.by("name"));
        Page<Country> countryPage = countryService.findAll(pageable);
        return Lists.newArrayList(countryPage);
    }

    @Secured("ROLE_ADMIN")
    @PutMapping
    public ResponseEntity<Country> updateCountry(@RequestParam String id,
                                                 @Valid @RequestBody Country country) throws EntityNotFoundException {

        return ResponseEntity.ok(countryService.update(country, id));
    }

    @Secured("ROLE_ADMIN")
    @DeleteMapping
    public ResponseEntity<?> deleteCountry(@RequestParam String id) {

        if (countryService.getById(id).isEmpty()) {
            return new ResponseEntity<Error>(HttpStatus.BAD_REQUEST);
        }

        countryService.delete(id);

        return new ResponseEntity<Authenticator.Success>(HttpStatus.OK);
    }

}
