package dongho.classflix.service;

import dongho.classflix.domain.Lecture;
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
        List<Lecture> findLectures = lectureRepository.findByName(lecture.getLectureName());
        if (!findLectures.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 강의입니다.");
        }
        return lectureRepository.save(lecture);
    }

    // 업데이트
    public void update(Long id, String lectureName, String teacherName, String content, byte[] representImage, String siteName, URI uri) {
        Lecture findLecture = lectureRepository.findById(id);
        findLecture.changeLectureData(lectureName, teacherName, content, representImage, siteName, uri);
    }
}
