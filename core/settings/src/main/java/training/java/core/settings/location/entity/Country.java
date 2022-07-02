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
  name = Country.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(
      name = Country.TABLE_NAME + "_code",
      columnNames = { "code" }
    )
  },
  indexes = { @Index(columnList="code, label") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor @Getter @Setter
public class Country extends AbstractPersistable<Long> {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "settings_country";

  @NotNull
  private String code;

  @NotNull
  private  String label;

  private  String label2;

  private String description;

  @Column(name = "phone_code")
  private String phoneCode;

  @Column(name = "address_format")
  private String addressFormat;

  private String currency;
}