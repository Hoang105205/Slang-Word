package models;

import java.util.List;

/**
 * @author Hoang
 * @date 11/4/2025
 */
public class SlangWord {
    private String slangWord;
    private List<String> meanings;

    public SlangWord(String slangWord,  List<String> meanings) {
        this.slangWord = slangWord;
        this.meanings = meanings;
    }

    public String getSlangWord() {
        return slangWord;
    }

    public List<String> getMeanings() {
        return meanings;
    }

    public void setSlangWord(String slangWord) {
        this.slangWord = slangWord;
    }

    public void setMeanings(List<String> meanings) {
        this.meanings = meanings;
    }
}
