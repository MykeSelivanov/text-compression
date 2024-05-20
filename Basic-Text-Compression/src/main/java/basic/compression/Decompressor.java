package basic.compression;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;

public class Decompressor {
    /**
     * Deserializes the data from the input file
     * Decodes the text using map
     * Writes the result to a new file
     *
     * @param inputFilePath - path to input file to be decompressed
     * @param outputFilePath - path to write the result of decompression
     */
    public void decompress(String inputFilePath, String outputFilePath) {
        try {
            FileInputStream fileInputStream = new FileInputStream(inputFilePath);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath));

            EncodedDataModel encodedData = (EncodedDataModel) objectInputStream.readObject();

            HashMap <String, Integer> encodedMap = encodedData.getEncodedMap();
            HashMap <Integer, String> codeToWordMap = swapHashMap(encodedMap);

            String encodedText = encodedData.getEncodedText();
            String[] encodedTextArr = encodedText.split(" ");
            StringBuilder decompressedSb = new StringBuilder();
            for (String number: encodedTextArr) {
                Integer code = Integer.parseInt(number);
                String word = codeToWordMap.get(code);
                decompressedSb.append(word).append(" ");
            }

            String decompressedStr = decompressedSb.toString().trim();
            writer.write(decompressedStr);
            writer.close();

        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Ooops, hit an exception! => " + e.getLocalizedMessage() + "\n");
            throw new RuntimeException(e);
        }
    }

    private HashMap<Integer, String> swapHashMap(HashMap<String, Integer> map) {
        HashMap<Integer, String> swappedMap = new HashMap<>();
        map.forEach((key, value) -> swappedMap.put(value, key));
        return swappedMap;
    }
}
