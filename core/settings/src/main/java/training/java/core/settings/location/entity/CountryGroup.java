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
  name = CountryGroup.TABLE_NAME,
  uniqueConstraints = {
    @UniqueConstraint(columnNames = {"name"}),
  },
  indexes = { @Index(columnList="name") }
)
@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@Getter @Setter
public class CountryGroup extends AbstractPersistable<Long> {
  private static final long serialVersionUID = 1L;
  public static final String TABLE_NAME = "settings_country_group";

  @Column(name="parent_id_path")
  private String parentIdPath;

  @Column(name="parent_id")
  private Long   parentId;

  @NotNull
  private String name;

  @NotNull
  private String label;

  public CountryGroup(String label, String name) {
    this.name  = name;
    this.label = label;
  }

  public CountryGroup withParent(CountryGroup parent) {
    this.parentId = parent.getId();
    if (parent.getParentIdPath() == null) {
      this.parentIdPath = parent.getId().toString();
    } else {
      this.parentIdPath = parent.getParentIdPath() + "/" + parent.getId();
    }
    return this;
  }
}