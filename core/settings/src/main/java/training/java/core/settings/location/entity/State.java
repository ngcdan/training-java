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
  name = State.TABLE_NAME,
  uniqueConstraints = { 
    @UniqueConstraint(
      name = State.TABLE_NAME + "_code",
      columnNames = { "code", "country_code", "state_code" }
    )
  },
  indexes = { @Index(columnList="code, label") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class State extends AbstractPersistable<Long> {
  public static final String TABLE_NAME = "settings_state";

  @NotNull
  @Column(name = "country_code")
  private String countryCode;

  @NotNull
  private String code;

  @NotNull
  @Column(name = "state_code")
  private String stateCode;

  private String label;
  
  private String description;
}