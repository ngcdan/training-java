package training.java.core.settings.location.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.java.core.settings.db.entity.AbstractPersistable;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(
  name = City.TABLE_NAME,
  uniqueConstraints = { 
    @UniqueConstraint(
      name = City.TABLE_NAME + "_code",
      columnNames = { "country_code", "state_code", "code" }
    ),
  },
  indexes = { @Index(columnList="code,label") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class City extends AbstractPersistable<Long> {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "settings_city";

  @NotNull
  @Column(name = "country_code")
  private String  countryCode;

  @Column(name = "state_code")
  private String  stateCode;

  @NotNull
  @Column(unique = true)
  private String code;

  private String label;
  private String description;

  public City(String code, String name) {
    this.label = name;
    this.code = code;
    this.description = name + "[" + code + "]";
  }
}