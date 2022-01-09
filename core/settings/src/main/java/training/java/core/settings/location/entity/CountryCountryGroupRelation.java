package training.java.core.settings.location.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.java.core.settings.db.entity.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
  name = CountryCountryGroupRelation.TABLE_NAME
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor @Getter @Setter
public class CountryCountryGroupRelation extends AbstractPersistable<Long> {
  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "settings_country_group_rel";

  @NotNull
  @Column(name = "country_id")
  private Long countryId;

  @NotNull
  @Column(name = "country_group_id")
  private Long countryGroupId;

  public CountryCountryGroupRelation(CountryGroup group, Country country) {
   countryGroupId = group.getId();
   countryId = country.getId();
  }
}