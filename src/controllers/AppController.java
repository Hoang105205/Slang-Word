package controllers;

import models.Dictionary;
import repositories.Repository;
import services.DictionaryService;

import java.util.List;
import java.util.Map;

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

    public boolean isSlangExist(String slang) {
        return dictionaryService.isSlangExist(slang);
    }

    public void addSlang(String slang, List<String> definitions){
        dictionaryService.addSlang(slang, definitions);
    }

    public void addDefinitions(String slang,  List<String> definitions){
        dictionaryService.addDefinitions(slang, definitions);
    }

    public void editSlang(String slang, List<String> definitions){
        dictionaryService.editSlang(slang, definitions);
    }

    public void deleteSlang(String slang){
        dictionaryService.deleteSlang(slang);
    }

    public void resetToRootData() {
        Dictionary rootDictionary = repository.loadRootData();
        this.dictionary = rootDictionary;
        this.dictionaryService = new DictionaryService(rootDictionary);
        repository.saveData(rootDictionary);
    }

    public Map.Entry<String, List<String>> getRandomSlang(){
        return dictionaryService.getRandomSlang();
    }

}
