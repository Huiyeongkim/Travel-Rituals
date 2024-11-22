package travel.travel.member.service;


import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import travel.travel.member.domain.Member;
import travel.travel.member.dto.MemberCreateReqDto;
import travel.travel.member.dto.MemberLoginDto;
import travel.travel.member.dto.MemberPasswordResetDto;
import travel.travel.member.dto.MemberResDto;
import travel.travel.member.repository.MemberRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    public MemberResDto memberCreate(MemberCreateReqDto dto) {
        if (memberRepository.findByEmailAndDelYn(dto.getEmail(),"N").isPresent()) {
            throw new IllegalArgumentException("이미 존재하는 회원입니다.");
        }
        if (dto.getPassword().length()<8) {
            throw new IllegalArgumentException("비밀번호가 너무 짧습니다.");
        }
        String password = passwordEncoder.encode(dto.getPassword());
        Member member = dto.toEntity(password);
        Member savedMember = memberRepository.save(member);
        return savedMember.fromEntity();
    }

    public Page<MemberResDto> memberList(Pageable pageable) {
        Page<Member> members = memberRepository.findByDelYn(pageable, "N");
        return members.map(a->a.fromEntity());
    }

    public Member login(MemberLoginDto dto) {
        Member member = memberRepository.findByEmailAndDelYn(dto.getEmail(), "N").orElseThrow(()->new EntityNotFoundException("존재하지 않는 이메일입니다."));
        if (!passwordEncoder.matches(dto.getPassword(),member.getPassword())){
            System.out.println(dto.getPassword());
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        return member;
    }

    public MemberResDto myInfo() {
        String memberEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findByEmailAndDelYn(memberEmail, "N").orElseThrow(()->new EntityNotFoundException("존재하지 않는 이메일입니다."));
        return member.fromEntity();
    }

    public void resetPassword(MemberPasswordResetDto dto) {
        String memberEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findByEmailAndDelYn(memberEmail, "N").orElseThrow(()->new EntityNotFoundException("존재하지 않는 이메일입니다."));
        if (!passwordEncoder.matches(dto.getAsIsPassword(),member.getPassword())){
            throw new IllegalArgumentException("비밀번호가 일치하지 않습니다.");
        }
        member.resetPassword(passwordEncoder.encode(dto.getToBePassword()));
    }
}
