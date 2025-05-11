package com.example.base.repository;

import com.example.base.dto.SearchTermDTO;
import com.example.base.dto.SearchTermDTO.SortBy;
import com.example.base.entity.QTerms;
import com.example.base.entity.Terms;
import com.example.base.enums.ErrorType;
import com.example.base.enums.SortType;
import com.example.base.enums.TermType;
import com.example.base.exception.CommonException;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import com.querydsl.core.types.dsl.Expressions;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.dsl.EnumPath;
import com.querydsl.core.types.OrderSpecifier;

@Repository
@RequiredArgsConstructor
public class TermsRepositoryCustomImpl implements TermsRepositoryCustom {

  private final JPAQueryFactory queryFactory;

  @Override
  public List<Terms> termsSearchByCondition(SearchTermDTO req) {
    // Terms 엔티티 기반으로 자동 생성된 Q타입 클래스
    // 주로 메인 쿼리에 사용됨
    QTerms terms = QTerms.terms;

    // 서브쿼리용 별명을 직접 지정해준 QTerms 인스턴스
    // 서브쿼리에 들어가는 테이블은 메인 쿼리와 다른 이름을 지정해줘야 충돌을 피할 수 있음
    QTerms termsSub = new QTerms("termsSub");
    // 조건절을 동적으로 조립할 수 있는 QueryDSL의 도구
    BooleanBuilder where = new BooleanBuilder();

    // 약관 id가 null이 아닐 경우, 헤당 id와 일치하는 레코드만 조회하도록 조건 추가
    if (req.id() != null) {
      where.and(terms.termId.id.eq(req.id()));
    }

    // 기본 정렬 값 세팅 (생성시간 내림차순)
    SortBy sortBy = req.sortBy() != null ? req.sortBy() : SortBy.CREATE_AT;
    SortType sortType = req.sortType() != null ? req.sortType() : SortType.DESC;

    if (sortBy == SortBy.VERSION && req.id() == null) {
      throw new CommonException(ErrorType.BAD_REQUEST);
    }
    // 정렬 조건을 저장할 변수 2개 선언
    OrderSpecifier<?> primaryOrder;
    OrderSpecifier<?> secondaryOrder;

    if (sortBy == SortBy.EFFECTIVE_DATE) {
      primaryOrder = req.sortType() == SortType.DESC ? terms.effectiveDate.desc()
          : terms.effectiveDate.asc();
      secondaryOrder =
          req.sortType() == SortType.DESC ? terms.termId.version.desc()
              : terms.termId.version.asc();
    } else if (sortBy == SortBy.CREATE_AT) {
      primaryOrder = sortType == SortType.DESC ? terms.createdAt.desc()
          : terms.createdAt.asc();
      secondaryOrder = null;

    } else {
      primaryOrder = sortType == SortType.DESC ? terms.termId.version.desc()
          : terms.termId.version.asc();
      secondaryOrder = null;
    }

    return queryFactory
        .selectFrom(terms)
        .where(where)
        .orderBy(
            secondaryOrder != null ? new OrderSpecifier[] { primaryOrder, secondaryOrder } : new OrderSpecifier[] { primaryOrder }
        )
        .offset((long) req.page() * req.size())
        .limit(req.size())
        .fetch();
  }

  @Override
  public List<Terms> activeTermList() {
    QTerms terms = QTerms.terms;
    QTerms termsSub = new QTerms("termsSub");
    BooleanBuilder where = new BooleanBuilder();

    where.and(
        Expressions.list(terms.termId.id, terms.termId.version)
            .in(
                JPAExpressions
                    .select(termsSub.termId.id, termsSub.termId.version.max())
                    .from(termsSub)
                    .where(termsSub.effectiveDate.loe(LocalDate.now()))
                    .groupBy(termsSub.termId.id)
            )
    );
    return queryFactory
        .selectFrom(terms)
        .where(where)
        .orderBy(terms.required.desc())
        .fetch();
  }

  @Override
  public Terms activeTermInfo(TermType id) {
    QTerms terms = QTerms.terms;
    QTerms termsSub = new QTerms("termsSub");

    return queryFactory
        .selectFrom(terms)
        .where(
            terms.termId.id.eq(id),
            terms.termId.version.eq(
                JPAExpressions
                    .select(termsSub.termId.version.max())
                    .from(termsSub)
                    .where(
                        termsSub.termId.id.eq(id),
                        termsSub.effectiveDate.loe(LocalDate.now())
                    )
            )
        )
        .fetchOne();
  }
}
