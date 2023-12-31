package com.pongchi.glimelight.service;

import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.pongchi.glimelight.api.v1.dto.member.MemberDto;
import com.pongchi.glimelight.api.v1.dto.member.MemberRegisterRequestDto;
import com.pongchi.glimelight.common.ResponseCode;
import com.pongchi.glimelight.domain.member.Member;
import com.pongchi.glimelight.domain.member.MemberRepository;
import com.pongchi.glimelight.exception.CustomException;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Transactional
    public UUID register(MemberRegisterRequestDto requestDto) {
        if (memberRepository.existsByEmail( requestDto.getEmail() )) {
            throw new CustomException(ResponseCode.CONCLICT_MEMBER);
        }

        Member member = requestDto.toEntity();
        member.hashPassword(passwordEncoder);

        return memberRepository.save(member).getId();
    }

    @Transactional(readOnly = true)
    public MemberDto findById(String stringId) {
        UUID id = UUID.fromString(stringId);
        Member member = memberRepository.findById(id)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_MEMBER)
            );
        return new MemberDto(member);
    }

    @Transactional(readOnly = true)
    public MemberDto findById(UUID id) {
        Member member = memberRepository.findById(id)
            .orElseThrow(
                () -> new CustomException(ResponseCode.NOT_FOUND_MEMBER)
            );
        return new MemberDto(member);
    }
}
