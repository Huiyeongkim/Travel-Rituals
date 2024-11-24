package travel.travel.plan.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import travel.travel.member.domain.Member;
import travel.travel.plan.domain.Plan;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanUpdateReqDto {
    private String title;
    private String content;

    private LocalDate startDate;
    private LocalDate endDate;

    public Plan toEntity(Member member) {
        return Plan.builder()
                .title(this.title)
                .content(this.content)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .member(member)
                .build();
    }
}