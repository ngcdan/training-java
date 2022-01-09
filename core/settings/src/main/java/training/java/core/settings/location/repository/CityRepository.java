package training.java.core.settings.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import training.java.core.settings.location.entity.City;

import java.io.Serializable;
import java.util.List;

public interface CityRepository extends JpaRepository<City, Serializable> {
  City getByCode(String code);

  @Query("SELECT c FROM City c WHERE c.id IN :ids")
  List<City> findCities(@Param("ids") List<Long> ids);

  @Query("SELECT c FROM City c WHERE c.stateCode IN :stateCode")
  List<City> findCitiesInState(@Param("stateCode") String stateCode);
}