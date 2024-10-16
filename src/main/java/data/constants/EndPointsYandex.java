package data.constants;

public class EndPointsYandex {
    public static final int MAX_COUNT_METADATA_LIMIT = 50;

    public static final String BASE_API_URL = "https://cloud-api.yandex.net";

    // получить список файлов упорядоченных по имени
    public static final String LIST_FILES = BASE_API_URL + "/v1/disk/resources/files";

    // загрузить файл в диск по URL
    public static final String UPLOAD_FILE_BY_URL = BASE_API_URL + "/v1/disk/resources/upload?path=%s&url=%s";

    // создать папку
    public static final String CREATE_FOLDER = BASE_API_URL + "/v1/disk/resources?path=%s";

    // получить метаинформацию о файле или каталоге
    public static final String GET_METADATA = BASE_API_URL + "/v1/disk/resources?path=%s&limit=" + MAX_COUNT_METADATA_LIMIT;

    // получить url для загрузки
    public static final String DOWNLOAD_URL = BASE_API_URL + "/v1/disk/resources/download?path=%s";

    // получить статус операции
    public static final String OPERATION_STATUS = BASE_API_URL + "/v1/disk/operations/%s";
}
