package services;

import models.Dictionary;
import repositories.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

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
        dictionary.searchedSlang.add(word);
        return dictionary.slangMap.get(word);
    }

    public List<String> searchByDefinition(String keyword) {
        List<String> result = new ArrayList<>();

        String pattern = "\\b" + Pattern.quote(keyword.trim().toLowerCase()) + "\\b";
        Pattern regex = Pattern.compile(pattern);

        for (Map.Entry<String, List<String>> entry : dictionary.slangMap.entrySet()) {
            List<String> definitions = entry.getValue();
            for (String definition : definitions) {
                if (regex.matcher(definition.toLowerCase()).find()) {
                    result.add(entry.getKey());
                    break;
                }
            }
        }
        return result;
    }

    public boolean isSlangExist(String slang) {
        return dictionary.slangMap.containsKey(slang);
    }

    public List<String> getSearchHistory() {
        return dictionary.searchedSlang;
    }

    public void addSlang(String slang, List<String> definitions){
        dictionary.slangMap.put(slang, new ArrayList<>(definitions));
    }

    public void addDefinitions(String slang, List<String> definitions){
        List<String> meanings = dictionary.slangMap.get(slang);
        meanings.addAll(definitions);
    }

    public void editSlang(String slang, List<String> meanings) {
        dictionary.slangMap.put(slang, new ArrayList<>(meanings));
    }

    public void deleteSlang(String slang) {
        dictionary.slangMap.remove(slang);
    }
}
