package training.java.core.settings.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import training.java.core.settings.location.entity.LocationType;

import java.io.Serializable;
import java.util.List;

public interface LocationTypeRepository extends JpaRepository<LocationType, Serializable> {
  
  LocationType getByType(String type);

  @Query("SELECT lt FROM LocationType lt WHERE lt.id IN :ids")
  List<LocationType> findLocationTypes(@Param("ids") List<Long> ids);
}