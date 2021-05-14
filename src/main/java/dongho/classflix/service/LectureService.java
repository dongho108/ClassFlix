package dongho.classflix.service;

import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.Review;
import dongho.classflix.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;

@Transactional
@Service
@RequiredArgsConstructor
public class LectureService {

    private final LectureRepository lectureRepository;

    // 조인
    public Long join(Lecture lecture) {
        validateDuplicateLecture(lecture);
        return lectureRepository.save(lecture);
    }

    private void validateDuplicateLecture(Lecture lecture) {
        List<Lecture> findLectures = lectureRepository.findByName(lecture.getLectureName(), lecture.getTeacherName());
        if (!findLectures.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 강의입니다.");
        }
    }

    // 업데이트
    public void update(Long id, LectureDto lectureDto) {
        Lecture findLecture = lectureRepository.findById(id);
        findLecture.changeLectureData(lectureDto.getLectureName(), lectureDto.getTeacherName(), lectureDto.getContent(), lectureDto.getRepresentImage(), lectureDto.getSiteName(), lectureDto.getUri());
    }

    // 조회
    @Transactional(readOnly = true)
    public List<Lecture> findAll() {
        return lectureRepository.findAll();
    }

    public Lecture findById(Long id) {
        return lectureRepository.findById(id);
    }

    // review refresh
    public void refreshAverageRating(Long lectureId, int oldRating, int newRating) {
        Lecture findLecture = findById(lectureId);
        findLecture.updateAverageRating(oldRating, newRating);
    }

    public void deleteReview(Long lectureId, Long reviewId) {
        Lecture findLecture = findById(lectureId);
        findLecture.removeReview(reviewId);
    }
}
