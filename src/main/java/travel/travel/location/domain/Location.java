package travel.travel.location.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import travel.travel.location.dto.LocationResDto;
import travel.travel.plan.domain.Plan;

import javax.persistence.*;
import java.time.LocalDate;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Table(name= "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    private String locationName;

    private double latitude;
    private double longitude;

    private String address;

    private LocalDate day;
    private Integer scheduleOrder;

    @Enumerated(EnumType.STRING)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    private Plan plan;

    public LocationResDto fromEntity() {
        return LocationResDto.builder()
                .locationId(this.locationId)
                .locationName(this.locationName)
                .latitude(this.latitude)
                .longitude(this.longitude)
                .address(this.address)
                .day(this.day)
                .scheduleOrder(this.scheduleOrder)
                .category(this.category)
                .build();
    }

    public void updateLocation(Location location) {
        this.scheduleOrder = location.getScheduleOrder();
    }
}
