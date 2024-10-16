package utils;

import data.constants.EndPointsYandex;
import testObject.TestObject;

import java.io.IOException;
import java.io.InputStream;
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

        System.out.println(token);
        return token;
    }

    public String parseImageName(String name) {
        String[] arrayWithImageName = name.split("/");

        LinkedList<String> listWithImageName = new LinkedList<>(Arrays.asList(arrayWithImageName));

        return listWithImageName.getLast();
    }

    public String getPathToUploadImageAtYandexDisk(String folderName, String imageUrl) {
        return String.format(EndPointsYandex.UPLOAD_PATH, folderName, parseImageName(imageUrl));
    }
}
