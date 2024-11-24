package travel.travel.plan.dto;

import lombok.*;
import travel.travel.location.dto.LocationResDto;

import java.time.LocalDate;
import java.util.List;

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
