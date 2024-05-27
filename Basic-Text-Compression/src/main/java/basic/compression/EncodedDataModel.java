package basic.compression;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;

public class EncodedDataModel implements Serializable {
    private List<String> encodedText;
    private HashMap<String, Integer> wordToCode;

    public EncodedDataModel(List<String> encodedText, HashMap<String, Integer> wordToCode) {
        this.encodedText = encodedText;
        this.wordToCode = wordToCode;
    }

    public List<String> getEncodedText() {
        return encodedText;
    }

    public void setEncodedText(List<String> encodedText) {
        this.encodedText = encodedText;
    }

    public HashMap<String, Integer> getWordToCode() {
        return wordToCode;
    }

    public void setWordToCode(HashMap<String, Integer> wordToCode) {
        this.wordToCode = wordToCode;
    }

    @Override
    public String toString() {
        return "EncodedDataModel{" +
                "encodedText='" + encodedText + '\'' +
                ", encodedMap=" + wordToCode +
                '}';
    }
}
