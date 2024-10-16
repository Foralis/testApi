package testObject;

import api.DogDataHelper;
import api.YandexDataHelper;
import api.exceptions.ApiException;
import api.exceptions.ServerException;
import lombok.Data;
import utils.Util;

import java.util.List;

@Data
public class TestObject {
    String breed;
    private List<String> images;
    String folderName;

    public TestObject(String breed) throws ServerException, ApiException {
        this.breed = breed;

        // получаем список изображений для породы
        images = DogDataHelper.getBreedImages(breed);

        // генерируем уникальное имя папки, которая позже будет создана на Яндексе диске
        folderName = new Util().generateRandomFolderName();
    }

    public void testMe() throws ApiException, InterruptedException {
        // создаем на Яндекс диске директорию
        YandexDataHelper.createFolderAtYandexDisk(new Util().getFolderPathForYandexDisk(folderName));

        // копируем изображения в директорию на Яндекс диске
        for (String imageUrl : images) {
            YandexDataHelper.uploadFileToYandexDiskByUrl(folderName, imageUrl);
        }
    }
}
