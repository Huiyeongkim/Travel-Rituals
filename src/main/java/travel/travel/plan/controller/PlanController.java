package travel.travel.plan.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import travel.travel.common.dto.CommonResDto;
import travel.travel.plan.dto.PlanCreateReqDto;
import travel.travel.plan.dto.PlanResDto;
import travel.travel.plan.repository.PlanRepository;
import travel.travel.plan.service.PlanService;


@RestController
@RequiredArgsConstructor
@Slf4j
public class PlanController {
    private final PlanService planService;
    private final PlanRepository planRepository;

    @PostMapping("/plan/create")
    public ResponseEntity<CommonResDto> planCreate(@RequestBody PlanCreateReqDto planCreateReqDto) {
        PlanResDto dto = planService.planCreate(planCreateReqDto);
        return new ResponseEntity<>(new CommonResDto(HttpStatus.CREATED, "계획생성이 성공적으로 되었습니다.", dto), HttpStatus.CREATED);
    }

}
