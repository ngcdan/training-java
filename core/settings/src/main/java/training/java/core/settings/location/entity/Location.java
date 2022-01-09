package training.java.core.settings.location.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.java.core.settings.db.entity.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(
  name = Location.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = Location.TABLE_NAME + "_code",
      columnNames = { "country_code", "state_code", "city_code", "code" }
    )
  },
  indexes = { @Index(columnList = "code,label") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class Location extends AbstractPersistable<Long> {
  public static final String TABLE_NAME = "settings_location";

  @NotNull
  private String code;

  private String label;

  @Column(name = "other_label")
  private String otherLabel;

  @Column(length = 65536)
  private String address;
  private String district;

  @Column(name = "city_code")
  private String cityCode;

  @Column(name = "state_code")
  private String stateCode;

  @Column(name = "country_code")
  private String countryCode;

  @Column(name = "postal_code")
  private String postalCode;

  @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
  @JoinColumn(name = "location_id", referencedColumnName = "id")
  private List<LocationAlias> aliases = new ArrayList<>();

  @ManyToMany(cascade = { CascadeType.MERGE })
  @JoinTable(
    name = "settings_location_type_rel",
    joinColumns = @JoinColumn(name = "location_id"),
    inverseJoinColumns = @JoinColumn(name = "location_type_id")
  )
  private List<LocationType> locationTypes;

  public Location withCity(City city) {
   cityCode = city.getCode();
   return this;
  }
}