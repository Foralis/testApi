package utils;

import data.constants.YandexHeaders;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import testObject.TestObject;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Properties;

public class Util {
    public String getOauthToken() {
        String token = "";

        try (InputStream input = TestObject.class.getClassLoader().getResourceAsStream("application.properties")) {

            Properties prop = new Properties();

            if (input == null) {
                System.out.println("Не найден файл application.properties");
                return null;
            }

            prop.load(input);

            token = prop.getProperty("oauth.token");
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        return token;
    }

    public String parseImageName(String url) {
        String[] arrayWithImageName = url.split("/");

        LinkedList<String> listWithImageName = new LinkedList<>(Arrays.asList(arrayWithImageName));

        return listWithImageName.getLast();
    }

    public String getBrokenLink(String url) {
        return url.replace(parseImageName(url), "imageDoesNotExist.jpg");
    }

    public String getPathToUploadImageAtYandexDisk(String folderName, String imageUrl) {
        return String.format(YandexHeaders.UPLOAD_PATH_FOR_IMAGE, folderName, parseImageName(imageUrl));
    }

    public String getFolderPathForYandexDisk(String folderName) {
        return String.format(YandexHeaders.UPLOAD_PATH, folderName);
    }

    public String generateRandomFolderName() {
        return "test_" + System.currentTimeMillis() + "_" + RandomUtils.nextInt(1, 1000);
    }

    public String getMd5Hash(String filePath) throws IOException, NoSuchAlgorithmException {
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        byte[] hash = MessageDigest.getInstance("MD5").digest(data);
        String checksum = new BigInteger(1, hash).toString(16);
        return checksum;
    }

    public String downloadFile(String sourceUrl) throws IOException {
        File file = new File("target/" + RandomStringUtils.randomAlphabetic(10) + ".jpg");

        FileUtils.copyURLToFile(
                new URL(sourceUrl),
                file,
                10000,
                10000
        );

        System.out.println(file.getCanonicalPath());

        return file.getAbsolutePath();
    }

    public String duplicateFileName(String fileName, int i) {
        String[] array = fileName.split("\\.");

        // "n02110627_13060.jpg" -> "n02110627_13060 (1).jpg"
        return array[0] + " (" + i + ")" + ".jpg";
    }
}
