package models;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * @author Hoang
 * @date 11/5/2025
 */
public class Dictionary implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    public Map<String, List<String>> slangMap;

    public  Dictionary(Map<String, List<String>> data) {
        this.slangMap = data;
    }

}
