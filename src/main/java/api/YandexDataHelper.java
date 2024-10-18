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
import entity.YandexDownloadUrl;
import io.restassured.response.Response;
import utils.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static api.ApiHelper.*;
import static java.net.HttpURLConnection.*;

public class YandexDataHelper {
    public static Util util = new Util();

    public static void listFiles() throws ApiException {
        Response response = getGetRequestYandex(EndPointsYandex.LIST_FILES);

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());
    }

    public static String uploadFileToYandexDiskByUrl(String folderName, String fileUrl) throws ApiException {
        String path = util.getPathToUploadImageAtYandexDisk(folderName, fileUrl);

        createFolderAtYandexDisk(util.getPathToCreateFolder(folderName));

        Response response = getPostRequestYandex(String.format(EndPointsYandex.UPLOAD_FILE_BY_URL, path, fileUrl));

        if (response.getStatusCode() != HTTP_ACCEPTED) throw new ApiException(response.getStatusCode());

        return folderName;
    }

    public static void createFolderAtYandexDisk(String path) throws ApiException {
        Response response = getPutRequestYandex(String.format(EndPointsYandex.CREATE_FOLDER, path));

        if (response.getStatusCode() != HTTP_CREATED) throw new ApiException(response.getStatusCode());
    }

    public static String getDownloadUrlFromYandexDisk(String path) throws ApiException {
        Response response = getGetRequestYandex(String.format(EndPointsYandex.DOWNLOAD_URL, path));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        YandexDownloadUrl url = response
                .then()
                .extract()
                .as(YandexDownloadUrl.class);

        return url.getHref();
    }
}
