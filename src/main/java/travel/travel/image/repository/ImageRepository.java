package travel.travel.image.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import travel.travel.image.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
