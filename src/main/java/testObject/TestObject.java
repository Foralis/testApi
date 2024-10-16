package testObject;

import api.DogDataHelper;
import api.YandexDataHelper;
import api.exceptions.ApiException;
import api.exceptions.ServerException;

import java.util.List;

public class TestObject {
    public void testMe(String breed) throws ServerException, ApiException {
        List<String> images = DogDataHelper.getBreedImages(breed);

        for (String image : images) {
            System.out.println(image);
        }

        for (String imageUrl : images) {
            YandexDataHelper.uploadFileToYandexDiskByUrl(imageUrl);
        }
    }
}
