package travel.travel.plan.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import travel.travel.member.domain.Member;
import travel.travel.member.repository.MemberRepository;
import travel.travel.plan.dto.PlanCreateReqDto;
import travel.travel.plan.domain.Plan;
import travel.travel.plan.dto.PlanResDto;
import travel.travel.plan.repository.PlanRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class PlanService{
    private final PlanRepository planRepository;
    private final MemberRepository memberRepository;

    public PlanResDto planCreate(@Valid PlanCreateReqDto planCreateReqDto) {
        String memberEmail = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findByEmailAndDelYn(memberEmail, "N").orElseThrow(()->new EntityNotFoundException("존재하지 않는 이메일입니다."));

        Plan savedPlan = planRepository.save(planCreateReqDto.toEntity(member));
        return new PlanResDto(savedPlan.getPlanId(), savedPlan.getTitle(), savedPlan.getStartDate(), savedPlan.getEndDate(), savedPlan.getDestinationId());
    }


}
