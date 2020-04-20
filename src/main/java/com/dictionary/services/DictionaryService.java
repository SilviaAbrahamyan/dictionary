package com.dictionary.services;

import java.io.IOException;
import java.net.URI;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.*;


import com.dictionary.models.Dictionary;
import com.dictionary.repositories.DictionaryRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;


/**
 * Created by Bajal on 8/29/2017.
 */

@Service
@RequiredArgsConstructor
public class DictionaryService {

    public List<com.dictionary.models.Dictionary> dictionary;

    HttpClient httpClient;

 @Autowired
 DictionaryRepository dictionaryRepository;

    public List<String> search(String word) {

        List<com.dictionary.models.Dictionary> d = dictionaryRepository.findByWord(word);

        return d.size() == 0 ? getMeaningFromExternalSource(word) : getMeaning(d);
    }
private List<String> getMeaning(List<com.dictionary.models.Dictionary> d){
    List<String> stringList = new ArrayList<>();
    for (Iterator<com.dictionary.models.Dictionary> it = d.iterator(); it.hasNext(); ) {
        com.dictionary.models.Dictionary dictionary = it.next();
        stringList.add(dictionary.getMean().replaceAll("<br>", "\n"));
    }
        return stringList;
}


    private List<String> getMeaningFromExternalSource(String word) {
        httpClient = HttpClient.newHttpClient();
        var meanings = new ArrayList<String>();
        HttpRequest request = getRequest(word);
        HttpResponse<String> response;


        try {
             response = httpClient.send(
                request,
                HttpResponse.BodyHandlers.ofString()
            );
          meanings =  handleResponse(response, meanings, word);
        } catch (IOException e) {
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return meanings;
    }

    private ArrayList<String> handleResponse(HttpResponse<String> response, ArrayList<String> meanings, String word) {
        String match = "<div class=\"content-core-arm w-100\">";
        String matchEng = "<div class=\"content-core-arm w-100 content-core-eng\">";
        String matchDarcvacq = "<div class=\"content-core-arm content-core-phrase w-100\">";
        String body = response.body();

        String firstSubstring = response
                .body()
                .substring(body.indexOf(match) +  match.length());
       String firstWord = firstSubstring
               .substring(0, firstSubstring.indexOf("</div>"));

       firstSubstring =  firstSubstring
               .substring(firstSubstring.indexOf(match) +  match.length());
       String secondWord =  firstSubstring
               .substring(0, firstSubstring.indexOf("</div>"));

        firstSubstring =  firstSubstring
                .substring(firstSubstring.indexOf(match) +  match.length());
        String thirdWord =  firstSubstring
                .substring(0, firstSubstring.indexOf("</div>"));

        firstSubstring =  firstSubstring
                .substring(firstSubstring.indexOf(match) +  match.length());
        String fourWord = firstSubstring
                .substring(0, firstSubstring.indexOf("</div>"));

        firstSubstring =  firstSubstring
                .substring(firstSubstring.indexOf(matchEng) +  matchEng.length());
        String fiveWord = firstSubstring
                .substring(0, firstSubstring.indexOf("</div>"));

        firstSubstring =  firstSubstring
                .substring(firstSubstring.indexOf(matchDarcvacq) +  matchDarcvacq.length());
        String sixWord = firstSubstring
                .substring(0, firstSubstring.indexOf("</div>"));

        com.dictionary.models.Dictionary dictionary1 = new com.dictionary.models.Dictionary(word, firstWord, 1);
        com.dictionary.models.Dictionary dictionary2 = new com.dictionary.models.Dictionary(word, secondWord, 2);
        com.dictionary.models.Dictionary dictionary3 = new com.dictionary.models.Dictionary(word, thirdWord, 3);
        com.dictionary.models.Dictionary dictionary4 = new com.dictionary.models.Dictionary(word, fourWord, 4);
        com.dictionary.models.Dictionary dictionary5 = new com.dictionary.models.Dictionary(word, fiveWord, 5);
        com.dictionary.models.Dictionary dictionary6 = new com.dictionary.models.Dictionary(word, sixWord, 6);
        add(dictionary1);
        add(dictionary2);
        add(dictionary3);
        add(dictionary4);
        add(dictionary5);
        add(dictionary6);
      System.out.println(firstWord);
      System.out.println(secondWord);
      System.out.println(thirdWord);
      System.out.println(fourWord);
      System.out.println(fiveWord);
      System.out.println(sixWord);
        List<com.dictionary.models.Dictionary> d = dictionaryRepository.findByWord(word);
        meanings = (ArrayList<String>) getMeaning(d);
        return meanings;
    }

    public void add(com.dictionary.models.Dictionary dictionaryEntry) {
        dictionaryRepository.save(dictionaryEntry);
   }

    private HttpRequest getRequest(String word) {

        HttpRequest content_type = HttpRequest.newBuilder()
                .uri(URI.create("https://bararanonline.com/" + word))
                .header("CONTENT_TYPE", MediaType.APPLICATION_JSON_VALUE)
                .GET()
                .build();
        return  content_type;
    }


    public List<String> searchAutocomplete(String word) {

        List<com.dictionary.models.Dictionary> byWordLike = dictionaryRepository.findByWordLike("%"+word+"%");
        List<String> dictionaryEntries = new ArrayList<>();
        for (Dictionary entry : byWordLike) {
            dictionaryEntries.add(entry.getWord());
        }
        Set<String> set = new HashSet<>(dictionaryEntries);
        dictionaryEntries.clear();
        dictionaryEntries.addAll(set);
        return dictionaryEntries;
    }
}
