package dongho.classflix.service;

import dongho.classflix.domain.Lecture;
import dongho.classflix.repository.LectureRepository;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import java.io.FileInputStream;
import java.io.File;
import java.io.OutputStream;
import org.apache.commons.io.IOUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class LectureServiceTest {
    @Autowired
    EntityManager em;

    @Autowired
    LectureService lectureService;

    @Autowired
    LectureRepository lectureRepository;


    @Test
    public void 중복강의거부() throws Exception {
        //given
        Lecture lecture1 = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        Lecture lecture2 = new Lecture("jpa", "김영한", "jpa강의입니다.", LocalDateTime.now());

        //when
        lectureService.join(lecture1);

        //then
        assertThrows(IllegalStateException.class, () -> {
            lectureService.join(lecture2);
        });

    }
    // 강의 업데이트
    @Test
    public void 강의수정() throws Exception {
        //given
        Lecture lecture = new Lecture("jpa", "김영한", "jpa강의", LocalDateTime.now());
        em.persist(lecture);

        LectureDto lectureDto = new LectureDto();
        lectureDto.setContent("data jpa 강의");

        //when
        lectureService.update(lecture.getId(), lectureDto);

        //then
        assertThat(lecture.getContent()).isEqualTo("data jpa 강의");
    }

    @Test
    public void 강의조회() throws Exception {
        //given
        Lecture lecture1 = new Lecture("스프링입문", "김영한", "좋아요", LocalDateTime.now());
        Lecture lecture2 = new Lecture("스프링코어", "김영한", "나빠요", LocalDateTime.now());
        Lecture lecture3 = new Lecture("jpa기초", "김영한", "그냥그래요", LocalDateTime.now());
        Lecture lecture4 = new Lecture("jpa활용", "김영한", "좋아요", LocalDateTime.now());
        em.persist(lecture1);
        em.persist(lecture2);
        em.persist(lecture3);
        em.persist(lecture4);

        //when
        List<Lecture> lectures = lectureService.findAll();

        //then
        assertThat(lectures.get(0).getLectureName()).isEqualTo(lecture1.getLectureName());
        assertThat(lectures.get(1).getLectureName()).isEqualTo(lecture2.getLectureName());
        assertThat(lectures.get(2).getLectureName()).isEqualTo(lecture3.getLectureName());
        assertThat(lectures.get(3).getLectureName()).isEqualTo(lecture4.getLectureName());
    }

    @Test
    public void 강의사진저장() throws Exception {
        //given
        MultipartFile mFile = getMultipartFile();

        //when
        FileInfo fileInfo = lectureService.fileSaveAndParsing(mFile);
        Lecture lecture = getLecture(fileInfo);

        MultipartFile mFile1 = getMultipartFile(lecture);

        //then
        // 1 : 인자로 받은 파일 2 : 저장한 후 다시 읽은 파일
        //파일자체, 크기, 타입 비교
        assertThat(mFile.getBytes()).isEqualTo(mFile1.getBytes());
        assertThat(mFile.getSize()).isEqualTo(mFile1.getSize());
        assertThat(mFile.getContentType()).isEqualTo(mFile1.getContentType());

    }

    private Lecture getLecture(FileInfo fileInfo) throws URISyntaxException {
        URI uri = new URI("https://www.inflearn.com/");
        Lecture lecture = new Lecture("테스트", "테스트", "좋아요", fileInfo.getFilePath(), fileInfo.getFileSize(), fileInfo.getFileName(), "인프런", uri, LocalDateTime.now());
        lectureService.join(lecture);
        return lecture;
    }

    private MultipartFile getMultipartFile(Lecture lecture) throws IOException {
        File file;
        FileItem fileItem;
        file = new File(new File("").getAbsolutePath() + "/src/main/resources/static"+ lecture.getRepresentImagePath());
        fileItem = new DiskFileItem("newFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            // Or faster..
            // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
        } catch (IOException ex) {
            // do something.
        }

        //저장한 파일 다시 multipartfile로 가져오기
        MultipartFile mFile1 = new CommonsMultipartFile(fileItem);
        return mFile1;
    }

    private MultipartFile getMultipartFile() throws IOException {
        File file = new File(new File("").getAbsolutePath() + "/src/main/resources/static/images/jpa.png");
        FileItem fileItem = new DiskFileItem("originFile", Files.probeContentType(file.toPath()), false, file.getName(), (int) file.length(), file.getParentFile());

        try {
            InputStream input = new FileInputStream(file);
            OutputStream os = fileItem.getOutputStream();
            IOUtils.copy(input, os);
            // Or faster..
            // IOUtils.copy(new FileInputStream(file), fileItem.getOutputStream());
        } catch (IOException ex) {
            // do something.
        }

        //jpa.png -> multipart 변환
        MultipartFile mFile = new CommonsMultipartFile(fileItem);
        return mFile;
    }
}