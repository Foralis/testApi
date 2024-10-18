package org.example;

import api.DogDataHelper;
import api.YandexDataHelper;
import api.exceptions.ApiException;
import api.exceptions.ServerException;
import api.exceptions.TestException;
import org.testng.annotations.Test;
import testObject.TestObject;
import utils.Util;

public class TestCases {

    Util util = new Util();

    @Test(description = "Загрузка изображения породы на Яндекс диск")
    public void testUploadBreed() throws ServerException, ApiException, TestException {
        // получили породу, которая существует на https://dog.ceo/
        String breed = DogDataHelper.getFirstBreedWithoutSubBreed();

        // получили url изображения
        String imageUrlFromDog = DogDataHelper.getBreedImages(breed).get(0);

        // создали папку которая будет создана на Яндексе диски и куда будет скопировано изображение
        String folderName = util.generateFolderName();

        // загрузили изображение на Яндекс диск
        YandexDataHelper.uploadFileToYandexDiskByUrl(folderName, imageUrlFromDog);

        String imageName = util.parseImageName(imageUrlFromDog);

        String imageUrlFromYandexDisk = YandexDataHelper.getDownloadUrlFromYandexDisk(util
                .getPathToUploadImageAtYandexDisk(folderName, imageUrlFromDog));

//        new TestObject().testMe(breed);
        System.out.println("The end");
    }

}
