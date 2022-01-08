package training.java.settings.location;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import training.java.core.settings.db.entity.AbstractPersistable;

import javax.persistence.Entity;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;

@Entity
@Table(
  name = LocationType.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = LocationType.TABLE_NAME + "_type",
      columnNames = { "type" }),
  },
  indexes = { @Index(columnList="type") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class LocationType extends AbstractPersistable<Long> {
  private static final long serialVersionUID = 1L;

  public static final String TABLE_NAME = "settings_location_type";

  @NotNull
  private String type;
  
  @NotNull
  private String label;
}