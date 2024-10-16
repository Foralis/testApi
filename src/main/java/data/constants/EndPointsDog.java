package data.constants;

public class EndPointsDog {
    public static final String BASE_API_URL = "https://dog.ceo/api/";

    // List all breeds
    public static final String LIST_ALL = BASE_API_URL + "breeds/list/all";

    // Display single random image from all dogs collection
    public static final String RANDOM_IMAGE = BASE_API_URL + "breeds/image/random";

    // Returns an array of all the images from a breed
    public static final String IMAGES_BY_BREED = BASE_API_URL + "breed/%s/images";

    // List all sub-breeds
    public static final String LIST_ALL_SUB_BREEDS = BASE_API_URL + "breed/%s/list";

    // Random image from a breed collection
    public static final String RANDOM_IMAGE_FROM_BREED = BASE_API_URL + "breed/%s/images/random";

    // Single random image from a sub breed collection
    public static final String RANDOM_IMAGE_FROM_SUB_BREED = BASE_API_URL + "breed/%s/%s/images/random";

    // Display single random image from all dogs collection
    public static final String RANDOM_IMAGE_FROM_COLLECTION = BASE_API_URL + "breeds/image/random";

    // Display multiple random images from all dogs collection
    public static final String RANDOM_IMAGE_FROM_COLLECTION_WITH_COUNT = BASE_API_URL + "breeds/image/random/%s";
}
