package travel.travel.plan.dto;

import lombok.*;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PlanResDto {
    private Long planId;
    private String title;
    private String content;
    private LocalDate startDate;
    private LocalDate endDate;

    private Long destinationId;
    private String destinationName;
}
