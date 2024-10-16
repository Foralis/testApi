package api;

import api.exceptions.ApiException;
import api.exceptions.ServerException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import data.constants.EndPointsDog;
import data.constants.EndPointsYandex;
import data.constants.ServerStatuses;
import entity.Breed;
import entity.ServerResponse;
import io.restassured.response.Response;
import utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static api.ApiHelper.*;
import static java.net.HttpURLConnection.*;

public class YandexDataHelper {

    public static void listFiles() throws ApiException {

        Response response = getGetRequestYandex(EndPointsYandex.LIST_FILES);

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        System.out.println(response);
    }

    public static void uploadFileToYandexDiskByUrl(String fileUrl) throws ApiException {
        String path = new Util().getPathToUploadImageAtYandexDisk("test", fileUrl);

        Response response = getPostRequestYandex(String.format(EndPointsYandex.UPLOAD_FILE_BY_URL, path, fileUrl));

        if (response.getStatusCode() != HTTP_ACCEPTED) throw new ApiException(response.getStatusCode());

        System.out.println(response);
    }
}
