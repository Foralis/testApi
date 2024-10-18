package org.example;

import api.DogDataHelper;
import api.YandexDataHelper;
import api.exceptions.ApiException;
import api.exceptions.ServerException;
import entity.Breed;
import org.apache.commons.lang3.RandomUtils;
import org.testng.annotations.Test;
import testObject.TestObject;
import utils.Util;

import java.time.LocalDateTime;
import java.util.List;

public class Tests {


    @Test
    public void testGettingAllBreeds() throws ApiException, ServerException {
        List<Breed> breeds = DogDataHelper.listAllBreeds();
        breeds.forEach(System.out::println);
    }

    @Test
    public void testGetBreedImage() throws ApiException, ServerException {
        String image = DogDataHelper.getRandomBreedImage("hound");
        System.out.println(image);
    }

    @Test
    public void testGetSubBreedImage() throws ApiException, ServerException {
        String image = DogDataHelper.getRandomSubBreedImage("hound", "afghan");
        System.out.println(image);
    }

    @Test
    public void testGetSubBreeds() throws ApiException, ServerException {
        List<String> subBreeds = DogDataHelper.listAllSubBreeds("hound");
        System.out.println(subBreeds);
    }

//    @Test
//    public void test() throws ApiException, ServerException {
//        List<String> imagesWithSubBreed = TestObject.getBreedImages("hound");
//        List<String> imagesWithOutSubBreed = TestObject.getBreedImages("redbone");
//        System.out.println("subBreeds");
//    }

    @Test
    public void testYandex() throws ApiException {
        YandexDataHelper.listFiles();
    }

    @Test
    public void testParseImageName() {
        System.out.println(new Util().parseImageName("https://images.dog.ceo/breeds/hound-basset/n02088238_9626.jpg"));
    }

//    @Test
//    public void testYandexUpload() throws ApiException {
//        YandexDataHelper.uploadFileToYandexDiskByUrl("https://images.dog.ceo/breeds/hound-basset/n02088238_9626.jpg");
//    }

    @Test
    public void testObject() throws ApiException, ServerException {
        new TestObject().testMe("hound");
    }

    @Test
    public void testTime() {
        System.out.println("test_" + System.currentTimeMillis() + "_" + RandomUtils.nextInt(1, 1000));
        System.out.println("test_" + System.currentTimeMillis() + "_" + RandomUtils.nextInt(1, 1000));
        System.out.println("test_" + System.currentTimeMillis() + "_" + RandomUtils.nextInt(1, 1000));
        System.out.println("test_" + System.currentTimeMillis() + "_" + RandomUtils.nextInt(1, 1000));
        System.out.println("test_" + System.currentTimeMillis() + "_" + RandomUtils.nextInt(1, 1000));
        System.out.println("test_" + System.currentTimeMillis() + "_" + RandomUtils.nextInt(1, 1000));

    }
}
