package travel.travel.location.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import travel.travel.image.domain.Image;
import travel.travel.image.dto.ImageResDto;
import travel.travel.image.repository.ImageRepository;
import travel.travel.image.service.ImageService;
import travel.travel.location.domain.Location;
import travel.travel.location.dto.LocationCreateReqDto;
import travel.travel.location.dto.LocationResDto;
import travel.travel.location.dto.LocationUpdateReqDto;
import travel.travel.location.repository.LocationRepository;
import travel.travel.plan.domain.Plan;
import travel.travel.plan.repository.PlanRepository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import javax.validation.Valid;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LocationService {
    private final LocationRepository locationRepository;
    private final PlanRepository planRepository;
    private final ImageService imageService;
    private final ImageRepository imageRepository;

    public LocationResDto LocationCreate(@Valid LocationCreateReqDto locationCreateReqDto, MultipartFile file) throws IOException {
        Plan plan = planRepository.findById(locationCreateReqDto.getPlanId())
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 계획입니다."));
        ImageResDto imageResDto = imageService.uploadFile(file);
        Image image = imageRepository.save(imageResDto.toEntity());

        Location savedLocation = locationRepository.save(locationCreateReqDto.toEntity(plan, image));
        return savedLocation.fromEntity();
    }

    public List<LocationResDto> LocationReadList(Long planId) {
        Plan plan =  planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 계획입니다."));
        List<LocationResDto> locations = locationRepository.findByPlan(plan).stream()
                .map(Location::fromEntity)
                .collect(Collectors.toList());

        return locations;
    }

    public List<LocationResDto> LocationReadDayList(Long planId, LocalDate day) {
        Plan plan =  planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 계획입니다."));
        List<LocationResDto> locations = locationRepository.findByPlanAndDayOrderByScheduleOrderAsc(plan, day).stream()
                .map(Location::fromEntity)
                .collect(Collectors.toList());
        return locations;
    }

    public List<LocationResDto> LocationUpdate(@Valid List<LocationUpdateReqDto> locationUpdateReqDtos, Long planId, LocalDate day) {
        Plan plan =  planRepository.findById(planId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않은 계획입니다."));
        List<LocationResDto> dtos = new ArrayList<>();

        for (LocationUpdateReqDto locationUpdateReqDto : locationUpdateReqDtos) {
            Location existingLocation =  locationRepository.findById(locationUpdateReqDto.getLocationId()).orElseThrow();
            if (!existingLocation.getPlan().equals(plan) || !existingLocation.getDay().equals(day)) {
                throw new IllegalArgumentException();
            }
            existingLocation.updateLocation(locationUpdateReqDto.toEntity());
            Location savedLocation = locationRepository.save(existingLocation);
            dtos.add(savedLocation.fromEntity());
        }

        dtos.sort(Comparator.comparing(LocationResDto::getScheduleOrder));
        return dtos;
    }

    public LocationResDto locationDelete(Long locationId) {
        Location existingLocation = locationRepository.findById(locationId)
                .orElseThrow(() -> new EntityNotFoundException("존재하지 않는 위치입니다."));
        locationRepository.delete(existingLocation);
        return existingLocation.fromEntity();
    }

}