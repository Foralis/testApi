package org.example;

import api.DogDataHelper;
import api.YandexDataHelper;
import api.exceptions.ApiException;
import api.exceptions.ServerException;
import api.exceptions.TestException;
import entity.Yandex.GetResourcesMetaData.Item;
import entity.Yandex.GetResourcesMetaData.ServerResponseWithMetaData;
import org.assertj.core.api.SoftAssertions;
import org.testng.annotations.Test;
import testObject.TestObject;
import utils.Util;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class TestCases {
    Util util = new Util();

    @Test(description = "Загрузка изображения породы на Яндекс диск и проверка хэш-кода")
    public void testUploadBreed() throws ServerException, ApiException, TestException, InterruptedException,
            IOException, NoSuchAlgorithmException {
        SoftAssertions softly = new SoftAssertions();

        // получили любую породу, которая существует на https://dog.ceo/
        String breed = DogDataHelper.getRandomBreed();

        TestObject testObject = new TestObject(breed);

        // выполнили метод тестового объекта
        testObject.testMe();

        // получили данные о файлах на яндекс диске
        ServerResponseWithMetaData serverResponseWithMetaData = YandexDataHelper
                .getMetaDataOfFolder(util.getFolderPathForYandexDisk(testObject.getFolderName()));

        // скачиваем файлы, чтобы вычислить хэш и сравнить между собой
        String dogFileMD5Hash = util.getMd5Hash(util.downloadFile(testObject.getImages().get(0)));
        String yandexFileMD5Hash = util.getMd5Hash(util.downloadFile(
                serverResponseWithMetaData.get_embedded().getItems().get(0).getFile()));
        String yandexMD5HashFromMetaData = serverResponseWithMetaData.get_embedded().getItems().get(0).getMd5();

        softly
                .assertThat(dogFileMD5Hash)
                .as("Чек-суммы исходного файла и файла на яндекс диске должны совпадать")
                .isEqualTo(yandexFileMD5Hash);
        softly
                .assertThat(dogFileMD5Hash)
                .as("Чек-суммы исходного файла и мета-данных яндекс диска должны совпадать")
                .isEqualTo(yandexMD5HashFromMetaData);
        softly
                .assertThat(testObject.getImages().size())
                .as("Число файлов скопированное на Яндекс диск должно соответстовать числу изображений," +
                        "найденных для породы на https://dog.ceo/")
                .isEqualTo(serverResponseWithMetaData.get_embedded().getItems().size());

        softly.assertAll();
    }

    @Test(description = "Загрузка изображения породы без подпород на Яндекс диск")
    public void testUploadBreedWithOutSubBreed() throws ServerException, ApiException, TestException,
            InterruptedException {
        SoftAssertions softly = new SoftAssertions();

        // получили породу без подпороды
        String breed = DogDataHelper.getFirstBreedWithoutSubBreed();

        TestObject testObject = new TestObject(breed);

        // выполнили метод тестового объекта
        testObject.testMe();

        // получили данные о файлах на яндекс диске
        ServerResponseWithMetaData serverResponseWithMetaData = YandexDataHelper
                .getMetaDataOfFolder(util.getFolderPathForYandexDisk(testObject.getFolderName()));

        softly
                .assertThat(util.parseImageName(testObject.getImages().get(0)))
                .as("Имя файла должно совпадать")
                .isEqualTo(serverResponseWithMetaData.get_embedded().getItems().get(0).getName());
        softly
                .assertThat(1)
                .as("Тестовый метод должен передавать только 1 изображение")
                .isEqualTo(testObject.getImages().size());
        softly
                .assertThat(1)
                .as("На Яндекс диск должно быть скопировано только 1 изображение")
                .isEqualTo(serverResponseWithMetaData.get_embedded().getItems().size());

        softly.assertAll();
    }

    @Test(description = "Проверка обработки ситуации при загрузке на яндекс диск файлов с одинаковым именем")
    public void testDuplicateBreedNames() throws ServerException, ApiException, TestException,
            InterruptedException {
        SoftAssertions softly = new SoftAssertions();

        // получили породу без подпороды
        String breed = DogDataHelper.getFirstBreedWithoutSubBreed();

        TestObject testObject = new TestObject(breed);

        // изменим данные - добавим в список файлов 2ой файл с таким же именем
        String imageUrl = testObject.getImages().get(0);
        testObject.getImages().add(imageUrl);

        // такое имя должно стать у файла с одинаковым именем
        String duplicateFileName = util.duplicateFileName(util.parseImageName(testObject.getImages().get(0)), 1);

        // выполнили метод тестового объекта
        testObject.testMe();

        // получили данные о файлах на яндекс диске
        ServerResponseWithMetaData serverResponseWithMetaData = YandexDataHelper
                .getMetaDataOfFolder(util.getFolderPathForYandexDisk(testObject.getFolderName()));

        softly
                .assertThat(2)
                .as("На Яндекс диск должно быть скопировано только 2 изображения")
                .isEqualTo(serverResponseWithMetaData.get_embedded().getItems().size());
        softly
                .assertThat(serverResponseWithMetaData
                        .get_embedded()
                        .getItems()
                        .stream()
                        .map(Item::getName)
                        .collect(Collectors.toList())
                        .contains(util.parseImageName(testObject.getImages().get(0))))
                .as("На Яндекс диск должно быть скопирован файл с именем " + testObject.getImages().get(0))
                .isTrue();
        softly
                .assertThat(serverResponseWithMetaData
                        .get_embedded()
                        .getItems()
                        .stream()
                        .map(Item::getName)
                        .collect(Collectors.toList())
                        .contains(duplicateFileName))
                .as("На Яндекс диск должно быть скопирован файл с именем " + duplicateFileName)
                .isTrue();

        softly.assertAll();
    }

    @Test(description = "Проверка получения и загрузки на яндекс диск максимального существующего числа файлов")
    public void testUploadingMaxCountExistedImagesForOneBreed() throws ServerException, ApiException,
            InterruptedException {
        SoftAssertions softly = new SoftAssertions();

        // получили породу с максимальным числом подпород
        String breed = DogDataHelper.getFirstBreedWithMaxCountOfSubBreeds();

        TestObject testObject = new TestObject(breed);

        // выполнили метод тестового объекта
        testObject.testMe();

        // получили данные о файлах на яндекс диске
        ServerResponseWithMetaData serverResponseWithMetaData = YandexDataHelper
                .getMetaDataOfFolder(util.getFolderPathForYandexDisk(testObject.getFolderName()));

        List<String> imageNamesFromYandex = serverResponseWithMetaData
                .get_embedded()
                .getItems()
                .stream()
                .map(Item::getName)
                .collect(Collectors.toList());

        List<String> imageNamesFromDog = testObject
                .getImages()
                .stream()
                .map(i -> util.parseImageName(i))
                .collect(Collectors.toList());

        Collections.sort(imageNamesFromDog);
        Collections.sort(imageNamesFromYandex);

        softly
                .assertThat(imageNamesFromDog)
                .as("Списки файлов полученных с https://dog.ceo/ должны совпадать со списком скопированных" +
                        " на Яндекс диск")
                .isEqualTo(imageNamesFromYandex);
        softly
                .assertThat(testObject.getImages().size())
                .as("На Яндекс диск должно быть скопировано изображений: " + testObject.getImages().size())
                .isEqualTo(serverResponseWithMetaData.get_embedded().getItems().size());

        softly.assertAll();
    }

    // тестовый объект не умеет обрабатывать такую ситуацию, данный автотест находит этот баг
    @Test(description = "Проверка обработки передачи несуществующей породы в тестовый объект")
    public void testUploadNotExistedBreed() throws ServerException, ApiException, InterruptedException {
        SoftAssertions softly = new SoftAssertions();

        // указываем не существующую породу
        String breed = "DOES_NOT_EXIST";

        TestObject testObject = new TestObject(breed);

        // выполнили метод тестового объекта
        testObject.testMe();

        // получили данные о файлах на яндекс диске
        ServerResponseWithMetaData serverResponseWithMetaData = YandexDataHelper
                .getMetaDataOfFolder(util.getFolderPathForYandexDisk(testObject.getFolderName()));

        softly
                .assertThat(0)
                .as("Тестовый метод должен передавать 0 изображений")
                .isEqualTo(testObject.getImages().size());
        softly
                .assertThat(0)
                .as("На Яндекс диск должно быть скопировано 0 изображений")
                .isEqualTo(serverResponseWithMetaData.get_embedded().getItems().size());

        softly.assertAll();
    }

    @Test(description = "Проверка обработки передачи несуществующей ссылки на изображение в тестовый объект")
    public void testUploadNotExistedUrlLink() throws ServerException, ApiException, InterruptedException, TestException {
        SoftAssertions softly = new SoftAssertions();

        // указываем породу
        String breed = DogDataHelper.getFirstBreedWithoutSubBreed();

        TestObject testObject = new TestObject(breed);

        // изменяем ссылку на изображение на несуществующую ссылку
        String brokenLink = util.getBrokenLink(testObject.getImages().get(0));
        testObject.setImages(new ArrayList<>(Collections.singletonList(brokenLink)));

        // выполнили метод тестового объекта
        testObject.testMe();

        // получили данные о файлах на яндекс диске
        ServerResponseWithMetaData serverResponseWithMetaData = YandexDataHelper
                .getMetaDataOfFolder(util.getFolderPathForYandexDisk(testObject.getFolderName()));

        softly
                .assertThat(0)
                .as("На Яндекс диск должно быть скопировано 0 изображений")
                .isEqualTo(serverResponseWithMetaData.get_embedded().getItems().size());

        softly.assertAll();
    }

    @Test(description = "Проверка обработки передачи, когда для породы нет изображений")
    public void testUploadBreedWithOutImages() throws ServerException, ApiException, InterruptedException, TestException {
        SoftAssertions softly = new SoftAssertions();

        // указываем породу
        String breed = DogDataHelper.getFirstBreedWithoutSubBreed();

        TestObject testObject = new TestObject(breed);

        // обнуляем список изображений
        testObject.setImages(new ArrayList<>(Collections.emptyList()));

        // выполнили метод тестового объекта
        testObject.testMe();

        // получили данные о файлах на яндекс диске
        ServerResponseWithMetaData serverResponseWithMetaData = YandexDataHelper
                .getMetaDataOfFolder(util.getFolderPathForYandexDisk(testObject.getFolderName()));

        softly
                .assertThat(0)
                .as("На Яндекс диск должно быть скопировано 0 изображений")
                .isEqualTo(serverResponseWithMetaData.get_embedded().getItems().size());

        softly.assertAll();
    }
}
