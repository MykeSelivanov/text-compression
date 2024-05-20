package basic.compression;

import java.io.*;
import java.util.HashMap;

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
            StringBuilder encodedTextBuilder = new StringBuilder();

            while((line = reader.readLine()) != null) {
                String[] words = line.split("\\s");
                for (String word: words) {
                    word = word.trim();
                    if (!encodedMap.containsKey(word)) {
                        encodedMap.put(word, code);
                    }
                    encodedTextBuilder.append(code).append(" ");
                    code++;
                }
            }
            String encodedText = encodedTextBuilder.toString();
            encodedText = encodedText.trim();

            EncodedDataModel encodedDataModel = new EncodedDataModel(encodedText, encodedMap);
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