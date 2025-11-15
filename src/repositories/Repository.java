package repositories;

import models.Dictionary;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Hoang
 * @date 11/4/2025
 */
public class Repository {
    private static final String DATA_FILE = "resources/data.DAT";
    private static final String ROOT_FILE = "resources/slang.txt";

    public Repository(){

    }

    public static boolean fileExists(String path){
        File file = new File(path);
        return file.exists() && file.isFile();
    }

    public Dictionary loadData(){
        Dictionary dictionary = null;
        if (fileExists(DATA_FILE)){
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DATA_FILE))){
                dictionary = (Dictionary) ois.readObject();
            }
            catch (IOException | ClassNotFoundException e){
                System.out.println("Error when reading file: " + DATA_FILE);
            }
        }
        else{
            Map<String, List<String>> slangMap = new HashMap<>();

            try (BufferedReader br = new BufferedReader(new FileReader(ROOT_FILE))){
                String line;

                // skip the first line
                br.readLine();

                while ((line = br.readLine()) != null){
                    if (line.isBlank() || !line.contains("`")) {
                        continue;
                    }

                    String[] parts = line.split("`");

                    String slangWord = parts[0].trim();

                    String[] meanings = parts[1].split("\\|");

                    List<String> meaningsList = new ArrayList<>();
                    for (String meaning : meanings){
                        meaningsList.add(meaning.trim());
                    }

                    if (slangMap.containsKey(slangWord)){
                        slangMap.get(slangWord).addAll(meaningsList);
                    }
                    else{
                        slangMap.put(slangWord, meaningsList);
                    }
                }

                List<String> searchedSlang = new ArrayList<>();

                dictionary = new Dictionary(slangMap, searchedSlang);

                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))){
                    oos.writeObject(dictionary);
                }


            } catch (IOException e){
                System.out.println("Error when reading file: " + ROOT_FILE);
            }
        }

        return dictionary;
    }

    public void saveData(Dictionary dictionary){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))){
            oos.writeObject(dictionary);
        }
        catch (IOException e){
            System.out.println("Error when saving file: " + DATA_FILE);
        }
    }

    public Dictionary loadRootData(){
        Dictionary dictionary = null;
        Map<String, List<String>> slangMap = new HashMap<>();

        try (BufferedReader br = new BufferedReader(new FileReader(ROOT_FILE))){
            String line;

            // skip the first line
            br.readLine();

            while ((line = br.readLine()) != null){
                if (line.isBlank() || !line.contains("`")) {
                    continue;
                }

                String[] parts = line.split("`");

                String slangWord = parts[0].trim();

                String[] meanings = parts[1].split("\\|");

                List<String> meaningsList = new ArrayList<>();
                for (String meaning : meanings){
                    meaningsList.add(meaning.trim());
                }

                if (slangMap.containsKey(slangWord)){
                    slangMap.get(slangWord).addAll(meaningsList);
                }
                else{
                    slangMap.put(slangWord, meaningsList);
                }
            }

            List<String> searchedSlang = new ArrayList<>();

            dictionary = new Dictionary(slangMap, searchedSlang);

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DATA_FILE))){
                oos.writeObject(dictionary);
            }

        } catch (IOException e){
            System.out.println("Error when reading file: " + ROOT_FILE);
        }

        return dictionary;
    }
}
