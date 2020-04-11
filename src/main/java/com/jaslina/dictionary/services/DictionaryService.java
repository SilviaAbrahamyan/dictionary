package com.jaslina.dictionary.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

import com.jaslina.dictionary.models.Dictionary;
import com.jaslina.dictionary.repositories.DictionaryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

/**
 * Created by Bajal on 8/29/2017.
 */

@Service
@RequiredArgsConstructor
public class DictionaryService {

    public List<Dictionary> dictionary;

    HttpClient httpClient;
    DictionaryRepository dictionaryRepository;

    public List<String> search(String word) {

        Dictionary d = dictionaryRepository.findByWord(word);
        return d == null ? getMeaningFromExternalSource(word) : d.getMeaning();
    }

    private List<String> getMeaningFromExternalSource(String word) {
        var meanings = new ArrayList<String>();
        try {
            var response = httpClient.send(
                getRequest(word),
                HttpResponse.BodyHandlers.ofString()
            );
            handleResponse(response, meanings);
        } catch (IOException e) {
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return meanings;
    }

    private void handleResponse(HttpResponse<String> response, ArrayList<String> meanings) {
//map response to something
    }

    public void add(Dictionary dictionaryEntry) {
        dictionaryRepository.save(dictionaryEntry);
    }

    private HttpRequest getRequest(String word) {

        return HttpRequest.newBuilder()
            .uri(URI.create("https://bararanonline.com/" + word))
            .header("CONTENT_TYPE", MediaType.APPLICATION_JSON_VALUE)
            .GET()
            .build();
    }
}
