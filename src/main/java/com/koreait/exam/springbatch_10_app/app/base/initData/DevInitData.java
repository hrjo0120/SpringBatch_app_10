package com.koreait.exam.springbatch_10_app.app.base.initData;

import com.koreait.exam.springbatch_10_app.app.member.service.MemberService;
import com.koreait.exam.springbatch_10_app.app.product.service.ProductService;
import com.koreait.exam.springbatch_10_app.app.song.service.SongService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// 개발 환경에서 실행됨
@Configuration
@Profile("dev")
public class DevInitData implements InitDataBefore {
    @Bean
    CommandLineRunner initData(MemberService memberService, SongService songService, ProductService productService) {
        return args -> {
            before(memberService, songService, productService);
        };
    }
}