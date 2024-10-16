package api;

import api.exceptions.ApiException;
import data.constants.EndPointsYandex;
import data.constants.ServerStatuses;
import entity.Yandex.GetResourcesMetaData.ServerResponseWithMetaData;
import entity.YandexAnswerWithHref;
import entity.YandexOperationStatus;
import io.restassured.response.Response;
import utils.Util;

import static api.ApiHelper.*;
import static java.net.HttpURLConnection.*;

public class YandexDataHelper {
    public static Util util = new Util();

    public static String uploadFileToYandexDiskByUrl(String folderName, String fileUrl) throws ApiException,
            InterruptedException {
        String path = util.getPathToUploadImageAtYandexDisk(folderName, fileUrl);

        Response response = getPostRequestYandex(String.format(EndPointsYandex.UPLOAD_FILE_BY_URL, path, fileUrl));

        if (response.getStatusCode() != HTTP_ACCEPTED) throw new ApiException(response.getStatusCode());

        YandexAnswerWithHref url = response
                .then()
                .extract()
                .as(YandexAnswerWithHref.class);

        waitUploadingFilesToYandexDisk(url.getHref());

        return folderName;
    }

    public static void createFolderAtYandexDisk(String path) throws ApiException {
        Response response = getPutRequestYandex(String.format(EndPointsYandex.CREATE_FOLDER, path));

        if (response.getStatusCode() != HTTP_CREATED) throw new ApiException(response.getStatusCode());
    }

    public static String getDownloadUrlFromYandexDisk(String path) throws ApiException {
        Response response = getGetRequestYandex(String.format(EndPointsYandex.DOWNLOAD_URL, path));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        YandexAnswerWithHref url = response
                .then()
                .extract()
                .as(YandexAnswerWithHref.class);

        return url.getHref();
    }

    public static ServerResponseWithMetaData getMetaDataOfFolder(String path) throws ApiException {
        Response response = getGetRequestYandex(String.format(EndPointsYandex.GET_METADATA, path));

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        ServerResponseWithMetaData serverResponseWithMetaData = response
                .then()
                .extract()
                .as(ServerResponseWithMetaData.class);

        return serverResponseWithMetaData;
    }

    public static String getYandexOperationStatus(String operationId) throws ApiException {
        Response response = getGetRequestYandex(operationId);

        if (response.getStatusCode() != HTTP_OK) throw new ApiException(response.getStatusCode());

        return response
                .then()
                .extract()
                .as(YandexOperationStatus.class)
                .getStatus();
    }

    public static void waitUploadingFilesToYandexDisk(String operationId) throws InterruptedException, ApiException {
        for (int i = 0; i < 10; i++) {
            if (getYandexOperationStatus(operationId).equals(ServerStatuses.SUCCESS)) {
                break;
            }
            Thread.sleep(500);
        }
    }
}
