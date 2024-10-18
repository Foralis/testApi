package testObject;

import api.DogDataHelper;
import api.YandexDataHelper;
import api.exceptions.ApiException;
import api.exceptions.ServerException;
import entity.UploadInfo.UploadInfo;
import utils.Util;

import java.util.List;

public class TestObject {
    public UploadInfo testMe(String breed) throws ServerException, ApiException {
        List<String> images = DogDataHelper.getBreedImages(breed);

        for (String image : images) {
            System.out.println(image);
        }

        String folderName = new Util().generateFolderName();

        for (String imageUrl : images) {
            YandexDataHelper.uploadFileToYandexDiskByUrl(folderName, imageUrl);
        }

        return new UploadInfo(breed, folderName, images);
    }
}
