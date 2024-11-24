package travel.travel.location.dto;

import lombok.*;
import travel.travel.location.domain.Location;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
public class LocationUpdateReqDto {
    private Long locationId;
    private Integer scheduleOrder;

    public Location toEntity() {
        return Location.builder()
                .scheduleOrder(this.scheduleOrder)
                .build();
    }
}
