package com.koreait.exam.springbatch_10_app.app.base.initData;

import com.koreait.exam.springbatch_10_app.app.member.MemberService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("test")
public class TestInitData implements InitDataBefore {
    @Bean
    CommandLineRunner initData(MemberService memberService) {
        return args -> {
            before(memberService);
        };
    }
}
