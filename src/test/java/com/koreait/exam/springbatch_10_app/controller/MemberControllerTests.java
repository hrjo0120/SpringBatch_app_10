package com.koreait.exam.springbatch_10_app.controller;

import com.koreait.exam.springbatch_10_app.app.member.service.MemberService;
import com.koreait.exam.springbatch_10_app.app.member.controller.MemberController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc // MockMVC 설정 / MockMVC == 실제 웹 서버를 띄우지 않아도 HTTP 요청을 흉내내서 컨트롤러 메서드를 실행 ==  톰캣이나 다른 서버를 실행시키지 않아도 된다.
@Transactional // 테스트 메소드가 끝나면 다시 롤백
@ActiveProfiles("test")
public class MemberControllerTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private MemberService memberService;

    @Test
    @DisplayName("회원가입 폼")
    void t1() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(get("/member/join"))   // member/join GET 요청 -> 폼 내놔
                .andDo(print());
        // THEN
        resultActions
                .andExpect(status().is2xxSuccessful())  // 성공(200번대)했는지? 내가 반환받은 결과가 200번대인지 확인, 성공으로 끝났는지 실패인지 알려줌
                .andExpect(handler().handlerType(MemberController.class))   // 요청을 Member Controller가 처리했는지?
                .andExpect(handler().methodName("showJoin"))    // 실행 된 method가 showJoin인지?
                .andExpect(content().string(containsString("회원가입")));   // 해당 페이지에 "회원가입" 텍스트가 있는지?
    }

    @Test
    @DisplayName("회원가입")
    void t2() throws Exception {
        // WHEN
        ResultActions resultActions = mvc
                .perform(post("/member/join")   // member/join POST 요청 해
                        .with(csrf())   // csrf 토큰을 추가해서 보안 검증 통과
                        .param("username", "user999")   // 파라미터로 넘기겠다. 회원가입 데이터임( 밑 3줄)
                        .param("password", "1234")
                        .param("email", "user999@test.com")
                )
                .andDo(print());
        // THEN
        resultActions
                .andExpect(status().is3xxRedirection()) // 리다이렉션(300번대)이 되었는지?
                .andExpect(handler().handlerType(MemberController.class))
                .andExpect(handler().methodName("join"))
                .andExpect(redirectedUrlPattern("/member/login?msg=**"));   // 리다이렉트 URL 패턴이 "/member/login?msg=**" 형식인지?
        assertThat(memberService.findByUsername("user999").isPresent()).isTrue();   //memberService.findByUsername("user999").isPresent() : memberSerivice에서 user999를 찾았을 때 존재하니? 가 True 인지
    }
}
