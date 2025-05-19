//package com.example.admin.controller;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doThrow;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
//
//import com.example.admin.service.TermService;
//import com.example.admin.service.dto.UpdateReqTermDTO;
//import com.example.base.entity.TermId;
//import com.example.base.entity.Terms;
//import com.example.base.enums.TermType;
//import com.example.base.repository.TermsRepository;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import java.time.LocalDate;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.mock.mockito.MockBean;
//import org.springframework.http.MediaType;
//import org.springframework.orm.ObjectOptimisticLockingFailureException;
//import org.springframework.test.web.servlet.MockMvc;
//
//@SpringBootTest
//@AutoConfigureMockMvc // MockMvc를 자동으로 주입 가능하게 만들어주는 설정
//public class TermControllerTest {
//
//  @Autowired
//  MockMvc mockMvc;
//
//  @Autowired
//  TermsRepository termsRepository;
//
//  @Autowired
//  EntityManagerFactory emf;
//
//  @Autowired
//  private ObjectMapper objectMapper;
//
//  @Mock
//  private TermService termService;
//
//  @Test
//  void 낙관적예외_핸들테스트() throws Exception {
//
//    TermId termId = new TermId(TermType.TERMS_OF_USE, LocalDate.of(2125, 5, 9));
//    termsRepository.save(new Terms(termId, "최초내용", true));
//
//    EntityManager em1 = emf.createEntityManager();
//    em1.getTransaction().begin();
//    Terms term1 = em1.find(Terms.class, termId);
//    term1.updateTerm("1번수정", true);
//    em1.getTransaction().commit();
//    em1.close();
//
//    UpdateReqTermDTO updateReq = new UpdateReqTermDTO("2번수정", false);
//
//        // then
//    mockMvc.perform(put("/api/v1/admin/terms/{id}/{effectiveDate}", "TERMS_OF_USE", "2125-05-09")
//            .contentType(MediaType.APPLICATION_JSON)
//            .content(objectMapper.writeValueAsString(updateReq)))
//        .andExpect(status().isConflict())
//        .andExpect(jsonPath("$.code").value("100002"))
//        .andExpect(jsonPath("$.message").value("다른 관리자가 먼저 수정했습니다. 다시 시도해주세요."))
//        .andExpect(jsonPath("$.data").isEmpty());
//
//  }
//
//
//
//
//}
