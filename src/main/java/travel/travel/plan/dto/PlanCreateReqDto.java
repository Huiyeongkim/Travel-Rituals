package travel.travel.plan.dto;

import lombok.*;
import travel.travel.location.dto.LocationResDto;
import travel.travel.member.domain.Member;
import travel.travel.plan.domain.Destination;
import travel.travel.plan.domain.Plan;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanCreateReqDto {
    private String title;
    private String content;

    private LocalDate startDate;
    private LocalDate endDate;

    private String destinationName;

    public Plan toEntity(Member member, Destination destination) {
        return Plan.builder()
                .title(this.title)
                .content(this.content)
                .startDate(this.startDate)
                .endDate(this.endDate)
                .destination(destination)
                .member(member)
                .build();
    }
}
