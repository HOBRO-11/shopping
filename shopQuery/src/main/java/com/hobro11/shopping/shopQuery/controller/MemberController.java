package com.hobro11.shopping.shopQuery.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hobro11.shopping.shopQuery.service.MemberQueryService;
import com.hobro11.shopping.shopQuery.service.dto.MemberDetailDto;
import com.hobro11.shopping.shopQuery.service.dto.MemberSimpleDto;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberQueryService memberQueryService;
    
    @GetMapping("/{memberId}")
    public MemberSimpleDto getMemberSimple(@PathVariable("memberId") final Long memberId) {
        return memberQueryService.getMemberSimple(memberId).orElse(null);
    }

    @GetMapping("/{memberId}/detail")
    public MemberDetailDto getMemberDetail(@PathVariable("memberId") final Long memberId) {
        return memberQueryService.getMemberDetail(memberId).orElse(null);
    }
}
