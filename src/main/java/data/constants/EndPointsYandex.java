package data.constants;

import utils.Util;

public class EndPointsYandex {
    public static final String BASE_API_URL = "https://cloud-api.yandex.net";

    // получить список файлов упорядоченный по имени
    public static final String LIST_FILES = BASE_API_URL + "/v1/disk/resources/files";

    // загрузить файл в диск по URL
    public static final String UPLOAD_FILE_BY_URL = BASE_API_URL + "/v1/disk/resources/upload?path=%s&url=%s";


    // headers
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_TOKEN = "OAuth " + new Util().getOauthToken();

    // upload path
    public static final String UPLOAD_PATH = "disk:/%s/%s";

}
