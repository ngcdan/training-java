package training.java.core.settings.location.entity;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.java.core.settings.db.entity.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(
  name = LocationAlias.TABLE_NAME
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class LocationAlias extends AbstractPersistable<Long> {
  public static final String TABLE_NAME = "settings_location_reference_code";

  @NotNull
  private String code;

  @NotNull
  private String type;

  private String label;

  private String creator;

  private String description;
}