package utils;

import data.constants.EndPointsYandex;
import org.apache.commons.lang3.RandomUtils;
import testObject.TestObject;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
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

        System.out.println(token);
        return token;
    }

    public String parseImageName(String name) {
        String[] arrayWithImageName = name.split("/");

        LinkedList<String> listWithImageName = new LinkedList<>(Arrays.asList(arrayWithImageName));

        return listWithImageName.getLast();
    }

    public String getPathToUploadImageAtYandexDisk(String folderName, String imageUrl) {
        return String.format(EndPointsYandex.UPLOAD_PATH_FOR_IMAGE, folderName, parseImageName(imageUrl));
    }

    public String getPathToCreateFolder(String folderName) {
        return String.format(EndPointsYandex.UPLOAD_PATH, folderName);
    }

    public String generateFolderName() {
        return "test_" + System.currentTimeMillis() + "_" + RandomUtils.nextInt(1, 1000);
    }

    public String getMd5Hash(String filePath) throws IOException, NoSuchAlgorithmException {
        byte[] data = Files.readAllBytes(Paths.get(filePath));
        byte[] hash = MessageDigest.getInstance("MD5").digest(data);
        String checksum = new BigInteger(1, hash).toString(16);
        return checksum;
    }
}
