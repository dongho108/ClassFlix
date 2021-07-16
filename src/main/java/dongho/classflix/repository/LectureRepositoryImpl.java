package dongho.classflix.repository;

import com.querydsl.core.QueryResults;
import com.querydsl.core.types.Order;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import dongho.classflix.controller.dto.HomeLectureDto;
import dongho.classflix.controller.dto.PageDto;
import dongho.classflix.controller.dto.QHomeLectureDto;
import dongho.classflix.domain.Lecture;
import dongho.classflix.domain.QLecture;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.persistence.EntityManager;
import java.util.List;

import static dongho.classflix.domain.QLecture.*;


public class LectureRepositoryImpl implements LectureRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public LectureRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<HomeLectureDto> findAllPageSort(Pageable pageable) {
        JPAQuery<HomeLectureDto> query = queryFactory
                .select(new QHomeLectureDto(
                        lecture.id.as("lectureId"),
                        lecture.representImagePath,
                        lecture.lectureName.as("lectureName"),
                        lecture.averageRating
                ))
                .from(lecture)
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize());

        for (Sort.Order o : pageable.getSort()) {
            PathBuilder pathBuilder = new PathBuilder(lecture.getType(), lecture.getMetadata());
            query.orderBy(new OrderSpecifier(o.isAscending() ? Order.ASC : Order.DESC,
                    pathBuilder.get(o.getProperty())));
        }

        QueryResults<HomeLectureDto> results = query.fetchResults();
        List<HomeLectureDto> content = results.getResults();
        long total = results.getTotal();

        return new PageImpl(content, pageable, total);
    }

}
