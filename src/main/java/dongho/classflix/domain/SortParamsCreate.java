package dongho.classflix.domain;

import java.util.Arrays;
import java.util.List;

public class SortParamsCreate {
    private static final List<SortParams> sortParams = Arrays.asList(
            new SortParams("createdDate,DESC", "최신순"),
            new SortParams("lectureName,ASC", "이름순")
    );

    public static List<SortParams> getInstance() {
        return sortParams;
    }

    private SortParamsCreate() {
    }
}
