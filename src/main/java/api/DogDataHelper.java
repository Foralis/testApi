package api;

import api.exceptions.ApiException;
import api.exceptions.ServerException;
import api.exceptions.TestException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.constants.EndPointsDog;
import data.constants.ServerStatuses;
import entity.Breed;
import entity.ServerResponse;
import io.restassured.response.Response;
import org.apache.commons.lang3.RandomUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static api.ApiHelper.getGetRequest;
import static java.net.HttpURLConnection.HTTP_OK;

public class DogDataHelper {
    public static List<Breed> listAllBreeds() throws ApiException, ServerException {

        Response response = getGetRequest(EndPointsDog.LIST_ALL);

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        ServerResponse serverResponse = response
                .then()
                .extract()
                .as(ServerResponse.class);

        if (!serverResponse.status.equals(ServerStatuses.SUCCESS)) throw new ServerException(serverResponse.status);

        Map<String, List<String>> breedMap = new Gson().fromJson(
                serverResponse.message.toString(),
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

        ServerResponse serverResponse = response
                .then()
                .extract()
                .as(ServerResponse.class);

        if (!serverResponse.status.equals(ServerStatuses.SUCCESS)) throw new ServerException(serverResponse.status);

        String image = serverResponse.message.toString();

        return image;
    }

    public static String getRandomSubBreedImage(String breed, String subBreed) throws ApiException, ServerException {

        Response response = getGetRequest(String.format(EndPointsDog.RANDOM_IMAGE_FROM_SUB_BREED, breed, subBreed));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        ServerResponse serverResponse = response
                .then()
                .extract()
                .as(ServerResponse.class);

        if (!serverResponse.status.equals(ServerStatuses.SUCCESS)) throw new ServerException(serverResponse.status);

        String image = serverResponse.message.toString();

        return image;
    }

    public static List<String> listAllSubBreeds(String breed) throws ApiException, ServerException {

        Response response = getGetRequest(String.format(EndPointsDog.LIST_ALL_SUB_BREEDS, breed));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        ServerResponse serverResponse = response
                .then()
                .extract()
                .as(ServerResponse.class);

        if (!serverResponse.status.equals(ServerStatuses.SUCCESS)) throw new ServerException(serverResponse.status);

        List<String> subBreeds = new Gson().fromJson(
                serverResponse.message.toString(),
                new TypeToken<List<String>>(){}.getType());

        return subBreeds;
    }

    public static List<String> getBreedImages(String breed) throws ServerException, ApiException {
//        List<Breed> breeds = DataHelper.listAllBreeds();

        List<String> subBreeds = DogDataHelper.listAllSubBreeds(breed);
        List<String> images = new ArrayList<>();

        if (subBreeds.isEmpty()) {
            images.add(DogDataHelper.getRandomBreedImage(breed));
        } else {
//            subBreeds.forEach(i -> images.add(getRandomSubBreedImage(breed, i)));
            for (String subBreed : subBreeds) {
                images.add(getRandomSubBreedImage(breed, subBreed));
            }
        }


        return images;
    }

    public static String getRandomBreed() throws ServerException, ApiException, TestException {
        List<Breed> breeds = listAllBreeds();

        if(breeds.isEmpty()) {
            throw new TestException("There is no any breed");
        }

        return breeds.get(RandomUtils.nextInt(0, breeds.size())).getName();
    }

    public static String getFirstBreedWithoutSubBreed() throws ServerException, ApiException, TestException {
        return listAllBreeds()
                .stream()
                .filter(i -> !i.isHasSubBreed())
                .findFirst()
                .orElseThrow(() -> new TestException("There is no breed without sub-breed"))
                .getName();
    }

    public static String getBreedWithMaxCountOfSubBreed() throws ServerException, ApiException, TestException {
        List<Breed> breeds = listAllBreeds();

        return listAllBreeds()
                .stream()
                .filter(i -> !i.isHasSubBreed())
                .findFirst()
                .orElseThrow(() -> new TestException("There is no breed without sub-breed"))
                .getName();
    }
}
