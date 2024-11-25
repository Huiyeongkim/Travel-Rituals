package travel.travel.location.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import travel.travel.image.dto.ImageResDto;
import travel.travel.location.domain.Category;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LocationResDto {
    private Long locationId;
    private String locationName;
    private double latitude;
    private double longitude;
    private String address;
    private LocalDate day;
    private Integer scheduleOrder;
    private Category category;
    private ImageResDto image;
}