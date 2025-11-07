package services;

import models.Dictionary;

import java.util.List;

/**
 * @author Hoang
 * @date 11/7/2025
 */
public class DictionaryService {
    private final Dictionary dictionary;

    public DictionaryService(Dictionary dictionary) {
        this.dictionary = dictionary;
    }

    public List<String> searchBySlang(String word) {
        return dictionary.slangMap.get(word);
    }
    public List<String> searchByDefinition(String keyword) {
        return null;
    }
}
