package api;

import api.exceptions.ApiException;
import api.exceptions.ServerException;
import api.exceptions.TestException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.constants.EndPointsDog;
import data.constants.ServerStatuses;
import entity.Breed;
import entity.DogServerResponse;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static api.ApiHelper.getGetRequest;
import static java.net.HttpURLConnection.HTTP_OK;

public class DogDataHelper {
    public static List<Breed> listAllBreeds() throws ApiException, ServerException {
        Response response = getGetRequest(EndPointsDog.LIST_ALL);

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        DogServerResponse dogServerResponse = response
                .then()
                .extract()
                .as(DogServerResponse.class);

        if (!dogServerResponse.status.equals(ServerStatuses.SUCCESS))
            throw new ServerException(dogServerResponse.status);

        Map<String, List<String>> breedMap = new Gson().fromJson(
                dogServerResponse.message.toString(),
                new TypeToken<Map<String, List<String>>>() {
                }.getType());

        List<Breed> breeds = new ArrayList<>();

        for (Map.Entry<String, List<String>> entry : breedMap.entrySet()) {
            breeds.add(new Breed(entry.getKey(), entry.getValue()));
        }

        return breeds;
    }

    public static String getRandomBreedImage(String breed) throws ApiException, ServerException {
        Response response = getGetRequest(String.format(EndPointsDog.RANDOM_IMAGE_FROM_BREED, breed));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        DogServerResponse dogServerResponse = response
                .then()
                .extract()
                .as(DogServerResponse.class);

        if (!dogServerResponse.status.equals(ServerStatuses.SUCCESS))
            throw new ServerException(dogServerResponse.status);

        String image = dogServerResponse.message.toString();

        return image;
    }

    public static String getRandomSubBreedImage(String breed, String subBreed) throws ApiException, ServerException {

        Response response = getGetRequest(String.format(EndPointsDog.RANDOM_IMAGE_FROM_SUB_BREED, breed, subBreed));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        DogServerResponse dogServerResponse = response
                .then()
                .extract()
                .as(DogServerResponse.class);

        if (!dogServerResponse.status.equals(ServerStatuses.SUCCESS))
            throw new ServerException(dogServerResponse.status);

        String image = dogServerResponse.message.toString();

        return image;
    }

    public static List<String> listAllSubBreeds(String breed) throws ApiException, ServerException {
        Response response = getGetRequest(String.format(EndPointsDog.LIST_ALL_SUB_BREEDS, breed));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        DogServerResponse dogServerResponse = response
                .then()
                .extract()
                .as(DogServerResponse.class);

        if (!dogServerResponse.status.equals(ServerStatuses.SUCCESS))
            throw new ServerException(dogServerResponse.status);

        List<String> subBreeds = new Gson().fromJson(
                dogServerResponse.message.toString(),
                new TypeToken<List<String>>() {
                }.getType());

        return subBreeds;
    }

    public static List<String> getBreedImages(String breed) throws ServerException, ApiException {
        List<String> subBreeds = DogDataHelper.listAllSubBreeds(breed);
        List<String> images = new ArrayList<>();

        if (subBreeds.isEmpty()) {
            images.add(DogDataHelper.getRandomBreedImage(breed));
        } else {
            for (String subBreed : subBreeds) {
                images.add(getRandomSubBreedImage(breed, subBreed));
            }
        }

        return images;
    }

    public static String getRandomBreed() throws ServerException, ApiException, TestException {
        List<Breed> breeds = listAllBreeds();

        if (breeds.isEmpty()) {
            throw new TestException("There is no any breed");
        }

        return breeds.get(RandomUtils.nextInt(0, breeds.size())).getName();
    }

    public static String getFirstBreedWithoutSubBreed() throws ServerException, ApiException, TestException {
        return listAllBreeds()
                .stream()
                .filter(i -> i.isHasSubBreed())
                .findFirst()
                .orElseThrow(() -> new TestException("There is no breed without sub-breed"))
                .getName();
    }

    public static List<String> getRandomImagesFromCollectionsWithCount(int count) throws ServerException, ApiException {
        Response response = getGetRequest(String.format(EndPointsDog.RANDOM_IMAGE_FROM_COLLECTION_WITH_COUNT, count));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        DogServerResponse dogServerResponse = response
                .then()
                .extract()
                .as(DogServerResponse.class);

        if (!dogServerResponse.status.equals(ServerStatuses.SUCCESS))
            throw new ServerException(dogServerResponse.status);

        return response.jsonPath().getList("message");
    }

    public static List<String> getAllImagesByBreed(String breed) throws ServerException, ApiException {
        Response response = getGetRequest(String.format(EndPointsDog.IMAGES_BY_BREED, breed));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        DogServerResponse dogServerResponse = response
                .then()
                .extract()
                .as(DogServerResponse.class);

        if (!dogServerResponse.status.equals(ServerStatuses.SUCCESS))
            throw new ServerException(dogServerResponse.status);

        List<String> images = response.jsonPath().getList("message");

        return images;
    }

    public static String getFirstBreedWithMaxCountOfSubBreeds() throws ServerException, ApiException {
        List<Breed> breeds = listAllBreeds();

        Breed breedWithMaxCountOfSubBreeds = breeds.get(0);

        for (Breed breed : breeds) {
            if (breed.getSubBreed().size() > breedWithMaxCountOfSubBreeds.getSubBreed().size()) {
                breedWithMaxCountOfSubBreeds = breed;
            }
        }

        return breedWithMaxCountOfSubBreeds.getName();
    }
}
