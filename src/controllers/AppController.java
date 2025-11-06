package controllers;

import models.Dictionary;
import utils.Loader;

import java.util.List;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class AppController {
    private Dictionary dictionary;
    private Loader loader;

    public AppController(){
        loader = new Loader();
        loader.loadData("src/resources/slang.txt");
        dictionary = new Dictionary(loader.slangMap);
    }

    // ================== SEARCH ==================
    public List<String> searchBySlang(String word) {
        return dictionary.searchBySlang(word);
    }

//    public List<SlangWord> searchByDefinition(String keyword) {
//        return dictionary.searchByDefinition(keyword);
//    }

}
