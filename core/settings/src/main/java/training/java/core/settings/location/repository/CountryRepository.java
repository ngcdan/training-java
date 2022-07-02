
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

  @Query("SELECT c FROM Country c, CountryGroupRelation r WHERE c.id = r.countryId AND r.groupId = :groupId")
  List<Country> findByCountryGroup(@Param("groupId") long groupId);
  
  
  @Query("SELECT c FROM Country c, CountryGroupRelation r  " +
      "WHERE ( c.id = r.countryId AND r.groupId = :groupId ) " +
      "AND (c.code LIKE :pattern% OR c.label LIKE :pattern%)")
  List<Country> findByCountries(@Param("groupId") long groupId, @Param("pattern") String pattern);
  
  @Query( "SELECT c FROM Country c WHERE c.code LIKE :pattern% OR c.label LIKE :pattern%")
  List<Country> findByCountries(@Param("pattern") String pattern);

  @Query("SELECT c FROM Country c WHERE c.id IN :ids")
  public List<Country> findCountries(@Param("ids") List<Long> ids);

}