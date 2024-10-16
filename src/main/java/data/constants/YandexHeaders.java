package data.constants;

import utils.Util;

public class YandexHeaders {
    public static final String AUTHORIZATION = "Authorization";
    public static final String AUTHORIZATION_TOKEN = "OAuth " + new Util().getOauthToken();

    // upload path for image with image file included
    public static final String UPLOAD_PATH_FOR_IMAGE = "disk:/%s/%s";

    // upload path for image without image file
    public static final String UPLOAD_PATH = "disk:/%s";
}
