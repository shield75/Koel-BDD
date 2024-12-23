package utils;

import java.io.File;

public class FileUtils {

    public static boolean isFileDownloaded(String downloadPath, String fileName, int timeout) throws InterruptedException {
        File file = new File(downloadPath + "/" + fileName);
        int elapsedTime = 0;

        // Wait for the file to appear in the directory
        while (!file.exists() && elapsedTime < timeout) {
            Thread.sleep(10000); // Wait 1 second
            elapsedTime++;
        }

        return file.exists();
    }

    public static void deleteFile(String downloadPath, String fileName) {
        File file = new File(downloadPath + "/" + fileName);
        if (file.exists()) {
            file.delete();
            System.out.println("Deleted file: " + fileName);
        }
    }
}
