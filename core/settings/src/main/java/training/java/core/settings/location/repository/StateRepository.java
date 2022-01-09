
package training.java.core.settings.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import training.java.core.settings.location.entity.State;

import java.io.Serializable;
import java.util.List;

public interface StateRepository extends JpaRepository<State, Serializable> {
  State getByCode(String code);
  
  @Query("SELECT s FROM State s WHERE s.code = :code AND s.countryCode = :countryCode")
  State getStateByCodeAndCountryCode(@Param("code") String code, @Param("countryCode") String countryCode);
  
  @Query("SELECT s FROM State s WHERE s.id IN :ids")
  List<State> findStates(@Param("ids") List<Long> ids);

  @Query("SELECT s FROM State s WHERE s.countryCode IN :countryCode")
  List<State> findStatesInCountry(@Param("countryCode") String countryCode);
}