package com.koreait.exam.springbatch_10_app.app.base.initData;

import com.koreait.exam.springbatch_10_app.app.cart.service.CartService;
import com.koreait.exam.springbatch_10_app.app.member.service.MemberService;
import com.koreait.exam.springbatch_10_app.app.product.service.ProductService;
import com.koreait.exam.springbatch_10_app.app.song.service.SongService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

// 테스트 환경에서 실행됨
@Configuration
@Profile("test")
public class TestInitData implements InitDataBefore {
    @Bean
    CommandLineRunner initData(MemberService memberService, SongService songService, ProductService productService, CartService cartService) {
        return args -> {
            before(memberService, songService, productService, cartService);
        };
    }
}
