package basic.compression;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Compressor {
    /**
     * Reads the file
     * Encodes the words from that file
     * Serializes encoded word sequence and encoding schema into a byte[]
     * Writes object with encoded data to the output file
     *
     * @param inputFilePath - path to input file to be compressed
     * @param outputFilePath - path to write the compressed output file
     */
    public void compress(String inputFilePath, String outputFilePath) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFilePath));
            String line;
            int code = 0;
            HashMap<String, Integer> encodedMap = new HashMap<>();
            List<String> encodedTextList = new ArrayList<>();

            while((line = reader.readLine()) != null) {
                String[] words = line.split("\\s");
                for (String word: words) {
                    if (!encodedMap.containsKey(word)) {
                        encodedMap.put(word, code);
                        encodedTextList.add(Integer.toString(code));
                        code++;
                    } else {
                        int existingCode = encodedMap.get(word);
                        encodedTextList.add(Integer.toString(existingCode));
                    }
                }
                encodedTextList.add(System.lineSeparator());
            }
            // Remove one extra lineSeparator
            encodedTextList.removeLast();
            reader.close();

            EncodedDataModel encodedDataModel = new EncodedDataModel(encodedTextList, encodedMap);
            byte[] serializedData = serializeObjectToByteArray(encodedDataModel);
            writeSerializedDataToFile(serializedData, outputFilePath);

        } catch (IOException e) {
            System.err.println("Ooops, hit an exception! => " + e.getLocalizedMessage() + "\n");
            throw new RuntimeException(e);
        }
    }

    private byte[] serializeObjectToByteArray(Object obj) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(obj);
        objectOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private void writeSerializedDataToFile(byte[] serializedData, String outputFilePath) throws IOException {
        try (FileOutputStream fileOutputStream = new FileOutputStream(outputFilePath)) {
            fileOutputStream.write(serializedData);
        }
    }
}