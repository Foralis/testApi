package org.example.data.constants;

public class Constants {
    public static final String BASE_API_URL = "https://dog.ceo/api/";

    // List all breeds
    public static final String LIST_ALL = BASE_API_URL + "breeds/list/all";

    // Display single random image from all dogs collection
    public static final String RANDOM_IMAGE = BASE_API_URL + "breeds/image/random";

    // Returns an array of all the images from a breed
    public static final String IMAGES_BY_BREED = BASE_API_URL + "breed/hound/images";

    // List all sub-breeds
    public static final String LIST_ALL_SUB_BREEDS = BASE_API_URL + "breed/hound/list";
}
