package controllers;

import models.Dictionary;
import repositories.Repository;
import services.DictionaryService;

import java.util.List;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class AppController {
    private Dictionary dictionary;
    private Repository repository;
    private DictionaryService  dictionaryService;

    public AppController(){
        repository = new Repository();
        dictionary = repository.loadData();
        dictionaryService = new DictionaryService(dictionary);
    }

    public void saveData(){
        repository.saveData(dictionary);
    }

    // ================== SEARCH ==================
    public List<String> searchBySlang(String word) {
        return dictionaryService.searchBySlang(word);
    }


    public List<String> searchByDefinition(String keyword) {
        return dictionaryService.searchByDefinition(keyword);
    }

    public List<String> getSearchHistory(){
        return dictionaryService.getSearchHistory();
    }

}
