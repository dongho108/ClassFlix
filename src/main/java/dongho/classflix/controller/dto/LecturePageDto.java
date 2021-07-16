package dongho.classflix.controller.dto;

import dongho.classflix.domain.Lecture;

import java.util.List;

public class LecturePageDto {
    private List<Lecture> result;
    private int total;

    public LecturePageDto(List<Lecture> result, int total) {
        this.result = result;
        this.total = total;
    }
}
