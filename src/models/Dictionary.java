package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class Dictionary {
    private Map<String, List<String>> slangMap;

    public  Dictionary() {
        this.slangMap = new HashMap<String, List<String>>();
    }

    public List<String> searchBySlang(String word) {
        return null;
    }
    public List<String> searchByDefinition(String keyword) {
        return null;
    }

}
