package training.java.core.settings.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import training.java.core.settings.location.entity.Location;

import java.util.List;

public interface LocationRepository extends JpaRepository<Location, Long> {

  Location getByCode(String code);
  
  @Query("SELECT l FROM Location l WHERE l.code = :code AND l.countryCode = :countryCode")
  Location getByCodeAndCountryCode(@Param("code") String code, @Param("countryCode") String countryCode);

  @Query("SELECT l FROM Location l WHERE l.id IN :ids")
  List<Location> findLocations(@Param("ids") List<Long> ids);
}