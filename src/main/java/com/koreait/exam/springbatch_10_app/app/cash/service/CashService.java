package com.koreait.exam.springbatch_10_app.app.cash.service;

import com.koreait.exam.springbatch_10_app.app.cash.entity.CashLog;
import com.koreait.exam.springbatch_10_app.app.cash.repository.CashRepository;
import com.koreait.exam.springbatch_10_app.app.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class CashService {
    private final CashRepository cashRepository;

    public CashLog addCash(Member member, long price, String eventType) {
        CashLog cashLog = CashLog.builder()
                .member(member)
                .price(price)
                .eventType(eventType)
                .build();
        cashRepository.save(cashLog);
        return cashLog;
    }
}
