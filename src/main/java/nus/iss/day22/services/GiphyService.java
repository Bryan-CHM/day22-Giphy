package nus.iss.day22.services;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import jakarta.json.JsonValue;

@Service
public class GiphyService {

    private final String GIPHY_API_URL = "https://api.giphy.com/v1/gifs/search";

    // In command line GIPHY_API_KEY
    @Value("${giphy.api.key}")
    private String GIPHY_API_KEY; 

    public List<String> getGifs(String phrase){
        return getGifs(phrase, "10", "pg");
    }
    public List<String> getGifs(String phrase, String limit, String rating){

    String url = UriComponentsBuilder				
        .fromUriString(GIPHY_API_URL)
        .queryParam("api_key", GIPHY_API_KEY)
        .queryParam("q", phrase)
        .queryParam("limit", limit)
        .queryParam("rating", rating)
        .toUriString();

    RestTemplate template = new RestTemplate(); 
    ResponseEntity <String> resp = template.getForEntity(url, String.class);

    InputStream is = new ByteArrayInputStream (resp.getBody().getBytes());
    JsonReader reader = Json.createReader(is);
    JsonObject respJson = reader.readObject();

    List<String> gifUrls = new ArrayList<>();
    JsonArray data = respJson.getJsonArray("data");
    for(JsonValue d :data){
        
        gifUrls.add(
        d.asJsonObject()
        .getJsonObject("images")
        .getJsonObject("fixed_width")
        .getString("url"));
    }

    return gifUrls;

    }   
}
