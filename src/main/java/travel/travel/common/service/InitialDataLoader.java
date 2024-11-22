package travel.travel.common.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import travel.travel.member.domain.Role;
import travel.travel.member.dto.MemberCreateReqDto;
import travel.travel.member.repository.MemberRepository;
import travel.travel.member.service.MemberService;

// CommandLineRunner를 상속함으로서 해당 컴포넌트가 스프링 빈으로 등록되는 시점에 run 메서드 실행
// 스프링 빈으로 등록되는 시점 = 서버 켜질 때
@Component
public class InitialDataLoader implements CommandLineRunner {
    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;
    @Override
    public void run(String... args) throws Exception {
        if(memberRepository.findByEmailAndDelYn("admin@test.com","N").isEmpty()) {
            memberService.memberCreate(MemberCreateReqDto.builder().name("admin").email("admin@test.com").password("12341234").role(Role.ADMIN).build());
        }
        if(memberRepository.findByEmailAndDelYn("test@naver.com","N").isEmpty()) {
            memberService.memberCreate(MemberCreateReqDto.builder().name("test").email("test@naver.com").password("12341234").role(Role.USER).build());
        }
    }
}
