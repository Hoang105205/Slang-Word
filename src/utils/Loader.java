package utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hoang
 * @date 11/4/2025
 */
public class Loader {
    public Map<String, List<String>> slangMap;

    public Integer duplicate;

    public Integer wrongFormat;

    public List<String> duplicateList;

    public Loader(){
        slangMap = new HashMap<>();
        duplicateList = new ArrayList<>();
        duplicate = 0;
        wrongFormat = 0;
    }

    public void loadData(String rootFilePath){
        try (BufferedReader br = new BufferedReader(new FileReader(rootFilePath))){
            String line;

            while ((line = br.readLine()) != null){
                if (line.isBlank() || !line.contains("`")) {
                    System.out.println(line);
                    wrongFormat++;
                    continue;
                }

                String[] parts = line.split("`");
                String slangWord = parts[0];

                String[] meanings = parts[1].split("\\|");

                List<String> meaningsList = new ArrayList<>();
                for (String meaning : meanings){
                    meaningsList.add(meaning.trim());
                }

                if (slangMap.containsKey(slangWord)){
                    slangMap.get(slangWord).addAll(meaningsList);
                    duplicate++;
                    duplicateList.add(slangWord);
                }
                else{
                    slangMap.put(slangWord, meaningsList);
                }
            }

        } catch (IOException e){
            System.out.println("Error when reading file: " + rootFilePath);
        }
    }
}
