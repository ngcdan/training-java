
package training.java.core.settings.location.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import training.java.core.settings.location.entity.Country;

import java.io.Serializable;
import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Serializable> {
  Country getByCode(String code);

  @Query(
    "SELECT c FROM Country c, CountryCountryGroupRelation r " + 
    "WHERE c.id = r.countryId AND r.countryGroupId = :countryGroupId"
  )
  List<Country> findByCountryGroup(@Param("countryGroupId") long countryGroupId);
  
  
  @Query(
    "SELECT c FROM Country c, CountryCountryGroupRelation r " + 
    " WHERE ( c.id = r.countryId AND r.countryGroupId = :countryGroupId ) AND (c.code LIKE :pattern% OR c.label LIKE :pattern%)"
  )
  List<Country> findByCountries(@Param("countryGroupId") long countryGroupId, @Param("pattern") String pattern);
  
  @Query( "SELECT c FROM Country c WHERE c.code LIKE :pattern% OR c.label LIKE :pattern%")
  List<Country> findByCountries(@Param("pattern") String pattern);

  @Query("SELECT c FROM Country c WHERE c.id IN :ids")
  public List<Country> findCountries(@Param("ids") List<Long> ids);

}