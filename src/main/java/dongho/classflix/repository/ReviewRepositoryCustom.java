package dongho.classflix.repository;

import dongho.classflix.domain.Review;

public interface ReviewRepositoryCustom {
    Review saveWithLecture(Review review);
}
