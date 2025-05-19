//package com.example.admin.service;
//
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//
//import com.example.admin.service.dto.ReqTermDTO;
//import com.example.admin.service.dto.UpdateReqTermDTO;
//import com.example.base.entity.TermId;
//import com.example.base.entity.Terms;
//import com.example.base.enums.TermType;
//import com.example.base.repository.TermsRepository;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.OptimisticLockException;
//import jakarta.persistence.Persistence;
//import jakarta.persistence.PersistenceUnit;
//import jakarta.persistence.RollbackException;
//import java.time.LocalDate;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//public class TermServiceTest {
//
//  @Autowired
//  private TermService termService;
//
//  @Autowired
//  TermsRepository termsRepository;
//
//  @PersistenceUnit
//  EntityManagerFactory emf;
//
//  @Test
//  void createTermSuccess() {
//
//    // given
//    ReqTermDTO req = new ReqTermDTO(TermType.TERMS_OF_USE,
//        LocalDate.of(2125, 5, 1),
//        "test contents",false);
//
//    termService.createTerm(req);
//  }
//
//  @Test
//  void updateTermSuccess() {
//    // given
//
//    ReqTermDTO req = new ReqTermDTO(TermType.TERMS_OF_USE,
//        LocalDate.of(2125, 5, 1),
//        "test contents",false);
//
//    termService.createTerm(req);
//
//    // when
//    UpdateReqTermDTO updateReq = new UpdateReqTermDTO("test contents", true);
//
//    // then
//    termService.updateTerm(req.id(),req.effectiveDate(),updateReq);
//  }
//
//  @Test
//  void optimistsic_locking_conflict() {
//    // given
//    TermId termId = new TermId(TermType.TERMS_OF_USE, LocalDate.of(2125,5,9));
//    termsRepository.save(new Terms(termId, "동시테스트내용",true));
//
//    // when
//
//    // 1번 트랜잭션
//    EntityManager em1 = emf.createEntityManager();
//    em1.getTransaction().begin();
//    Terms term1 = em1.find(Terms.class, termId);
//
//    // 2번 트랜잭션
//    EntityManager em2 = emf.createEntityManager();
//    em2.getTransaction().begin();
//    Terms term2 = em2.find(Terms.class, termId);
//
//    // 1번에서 term 업데이트
//    term1.updateTerm("1번업데이트",true);
//    em1.getTransaction().commit();
////    em1.close();
//
//    term2.updateTerm("2번업데이트", false);
//    // 낙관적 락에서 update, delete를 할 경우, where 조건에 version이 들어가 있음. 해당 버전이 이미 올라갔을 경우 찾을 수 없으므로,
//    // 낙관적 락으로 판단해 OptimisticLockException 예외 처리가 됨
////    em2.remove(term2);
//    // then
//    // 해당 코드 블록에서 예외가 발생해야 하고, 그 예외 타입은 RollbackException 이어야함.
//    assertThatThrownBy(() -> {
//      em2.getTransaction().commit();
//    }).isInstanceOf(RollbackException.class).hasCauseInstanceOf(OptimisticLockException.class);
//
//    // 테스트 데이터를 삭제하고 싶음
//    // commit을 했으면 그 이후로는 트랜잭션 끝남. 다시 트랜잭션을 시작해야 remove 사용 가능
//    em1.getTransaction().begin();
//    em1.remove(term1);
//    em1.getTransaction().commit();
//
//    em1.close();
//    em2.close();
//    emf.close();
//  }
//
//
//
//}
