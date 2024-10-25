package com.koreait.exam.springbatch_10_app.app.security.service;

import com.koreait.exam.springbatch_10_app.app.member.entity.Member;
import com.koreait.exam.springbatch_10_app.app.member.repository.MemberRepository;
import com.koreait.exam.springbatch_10_app.app.security.dto.MemberContext;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService { // 사용자 인증 정보 조회
    // 데이터의 출처는 DB -> Spring Security에서 사용 가능하게 변환

    // final이 붙으면, 객체는 불변성을 가진다.
    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username).get();
        List<GrantedAuthority> authorities = new ArrayList<>();

        if (member.getUsername().equals("user1")) {
            authorities.add(new SimpleGrantedAuthority("ADMIN"));
        }

        authorities.add(new SimpleGrantedAuthority("MEMBER")); // GrantedAuthority: MEMBER 권한을 부여, 권한 객체는 SimpleGrantedAuthority , 권한 정보를 담고있음, 권한 상태를 나타냄
        return new MemberContext(member, authorities);  // 무슨 권한을 가지고 있는지 한번에 나타냄
    }
}