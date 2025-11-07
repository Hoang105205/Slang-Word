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
    private final Repository repository;

    public DictionaryService(Dictionary dictionary,  Repository repository) {
        this.dictionary = dictionary;
        this.repository = repository;
    }

    public List<String> searchBySlang(String word) {
        dictionary.searchedSlang.add(word);
        repository.saveData(dictionary);
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
}
