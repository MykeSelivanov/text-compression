package basic.compression;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByByteFileComparator {
    public void test() {

    }
    public boolean compareFilesByteByByte(String file1Path, String file2Path) throws IOException {
        try (FileInputStream fis1 = new FileInputStream(file1Path);
             FileInputStream fis2 = new FileInputStream(file2Path)) {

            int byte1, byte2;

            // Read bytes from both files and compare
            while ((byte1 = fis1.read()) != -1 && (byte2 = fis2.read()) != -1) {
                if (byte1 != byte2) {
                    return false;
                }
            }

            // Check if both files have reached the end
            return fis1.read() == -1 && fis2.read() == -1;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
}
