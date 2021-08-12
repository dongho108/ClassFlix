package dongho.classflix.domain;

import java.util.LinkedHashMap;
import java.util.Map;

public class RatingsCreate {
    private static final Map<Integer, String> ratings = new LinkedHashMap<>(){
        {
            put(5, "★★★★★");
            put(4, "★★★★");
            put(3, "★★★");
            put(2, "★★");
            put(1, "★");
        }
    };

    public static Map<Integer, String> getInstance() {
        return ratings;
    }

    private RatingsCreate() {
    }
}
