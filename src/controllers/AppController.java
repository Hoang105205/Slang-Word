package controllers;

import models.Dictionary;
import repositories.Repository;

import java.util.List;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class AppController {
    private Dictionary dictionary;
    private Repository repository;

    public AppController(){
        repository = new Repository();
        dictionary = repository.loadData();
    }

    // ================== SEARCH ==================
    public List<String> searchBySlang(String word) {
        return dictionary.searchBySlang(word);
    }

//    public List<SlangWord> searchByDefinition(String keyword) {
//        return dictionary.searchByDefinition(keyword);
//    }

}
