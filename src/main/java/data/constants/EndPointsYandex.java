package data.constants;

import utils.Util;

public class EndPointsYandex {
    public static final String BASE_API_URL = "https://cloud-api.yandex.net";

    // получить список файлов упорядоченный по имени
    public static final String LIST_FILES = BASE_API_URL + "/v1/disk/resources/files";

    // загрузить файл в диск по URL
    public static final String UPLOAD_FILE_BY_URL = BASE_API_URL + "/v1/disk/resources/upload?path=%s&url=%s";

    // создать папку
    public static final String CREATE_FOLDER = BASE_API_URL + "/v1/disk/resources?path=%s";

    // создать папку
    public static final String DOWNLOAD_URL = BASE_API_URL + "/v1/disk/resources/download?path=%s";


    // headers
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_TOKEN = "OAuth " + new Util().getOauthToken();

    // upload path for image with image file included
    public static final String UPLOAD_PATH_FOR_IMAGE = "disk:/%s/%s";

    // upload path for image without image file
    public static final String UPLOAD_PATH = "disk:/%s";

}
