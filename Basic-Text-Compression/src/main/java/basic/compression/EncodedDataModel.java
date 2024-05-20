package basic.compression;

import java.io.Serializable;
import java.util.HashMap;

public class EncodedDataModel implements Serializable {
    private String encodedText;
    private HashMap<String, Integer> encodedMap;

    public EncodedDataModel(String encodedText, HashMap<String, Integer> encodedMap) {
        this.encodedText = encodedText;
        this.encodedMap = encodedMap;
    }

    public String getEncodedText() {
        return encodedText;
    }

    public void setEncodedText(String encodedText) {
        this.encodedText = encodedText;
    }

    public HashMap<String, Integer> getEncodedMap() {
        return encodedMap;
    }

    public void setEncodedMap(HashMap<String, Integer> encodedMap) {
        this.encodedMap = encodedMap;
    }

    @Override
    public String toString() {
        return "EncodedDataModel{" +
                "encodedText='" + encodedText + '\'' +
                ", encodedMap=" + encodedMap +
                '}';
    }
}
