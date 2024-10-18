package org.example;

import api.DogDataHelper;
import api.YandexDataHelper;
import api.exceptions.ApiException;
import api.exceptions.ServerException;
import api.exceptions.TestException;
import entity.UploadInfo.UploadInfo;
import entity.Yandex.GetResourcesMeta.ServerResponseWithMetaData;
import org.testng.annotations.Test;
import testObject.TestObject;
import utils.Util;

public class TestCases {

    Util util = new Util();
    TestObject testObject = new TestObject();

    @Test(description = "Загрузка изображения породы на Яндекс диск")
    public void testUploadBreed() throws ServerException, ApiException, TestException, InterruptedException {
        // получили породу, которая существует на https://dog.ceo/
        String breed = DogDataHelper.getRandomBreed();

//        // получили url изображения
//        String imageUrlFromDog = DogDataHelper.getBreedImages(breed).get(0);
//
//        // создали папку которая будет создана на Яндексе диски и куда будет скопировано изображение
//        String folderName = util.generateFolderName();
//
//        // загрузили изображение на Яндекс диск
//        YandexDataHelper.uploadFileToYandexDiskByUrl(folderName, imageUrlFromDog);
//
//        String imageName = util.parseImageName(imageUrlFromDog);
        UploadInfo uploadInfo = testObject.testMe(breed);

        Thread.sleep(3000);

        String imageUrlFromYandexDisk = YandexDataHelper.getDownloadUrlFromYandexDisk(util
                .getPathToUploadImageAtYandexDisk(uploadInfo.getFolderName(), uploadInfo.getImages().get(0)));

//        new TestObject().testMe(breed);
        System.out.println(imageUrlFromYandexDisk);

        ServerResponseWithMetaData serverResponseWithMetaData = YandexDataHelper
                .getMetaDataOfFolder(util.getPathToCreateFolder(uploadInfo.getFolderName()));

        System.out.println(imageUrlFromYandexDisk);
    }

}
