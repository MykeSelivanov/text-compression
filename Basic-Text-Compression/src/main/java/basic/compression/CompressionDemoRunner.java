package basic.compression;

import java.io.IOException;

public class CompressionDemoRunner {
    public static void main(String[] args) {
        System.out.println("Current working directory: " + System.getProperty("user.dir"));
        String fileToCompressFilePath = "Basic-Text-Compression/data/input.txt";
        String compressedFilePath = "Basic-Text-Compression/data/output.sc";
        String decompressedFilePath = "Basic-Text-Compression/data/readable.txt";

        Compressor compressor = new Compressor();
        compressor.compress(fileToCompressFilePath, compressedFilePath);

        Decompressor decompressor = new Decompressor();
        decompressor.decompress(compressedFilePath, decompressedFilePath);

        ByByteFileComparator byByteFileComparator = new ByByteFileComparator();
        try {
            boolean equalByBytes = byByteFileComparator.compareFilesByteByByte(fileToCompressFilePath, decompressedFilePath);
            System.out.println("Files are equal byte by byte: " + equalByBytes);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
