package com.hobro11.command.core.client.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hobro11.command.core.client.Coordinate;
import com.hobro11.command.core.client.GeoDataClient;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class GeoDataClientKakao implements GeoDataClient {

    @Value("${kakao.api-key}")
    private String apiKey;

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;

    private final static String HOST = "https://dapi.kakao.com";
    private final static String PATH = "/v2/local/search/address.json";
    private final static String QUERY_STRING_PREFIX = "?analyze_type=similar&page=1&size=10&query=";

    @Override
    public <T extends Coordinate> Map<Integer, T> getCandidates(String address, Class<T> type) {
        JsonNode tree;
        try {
            tree = objectMapper.readTree(read(address));
        } catch (JsonProcessingException | RestClientException e) {
            log.error("Error reading JSON", e);
            return Map.of();
        }

        Map<Integer, T> map = new HashMap<>();

        for (JsonNode document : tree.get("documents")) {
            JsonNode jsonNode = document.get("road_address");

            if (jsonNode == null) {
                continue;
            }

            Integer zoneNo = jsonNode.get("zone_no").asInt();
            Float latitude = Float.parseFloat(jsonNode.get("y").asText());
            Float longitude = Float.parseFloat(jsonNode.get("x").asText());

            try {
                T coordinate = type.getDeclaredConstructor(Float.class, Float.class)
                        .newInstance(latitude, longitude);
                map.put(zoneNo, coordinate);
            } catch (Exception e) {
                throw new IllegalArgumentException("cant cast this generic type" + type.getName());
            }
        }

        return map;
    }

    private String read(String address) throws RestClientException {
        String queryString = QUERY_STRING_PREFIX + address;
        String url = HOST + PATH + queryString;

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.add("Accept", "application/json");
        httpHeaders.add("Authorization", "KakaoAK " + apiKey);

        HttpEntity<String> httpEntity = new HttpEntity<>(null, httpHeaders);

        return restTemplate.exchange(url, HttpMethod.GET, httpEntity, String.class)
                .getBody();
    }

}
