package training.java.core.settings.db.entity;

import org.springframework.data.domain.Persistable;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@MappedSuperclass
public class AbstractPersistable<PK extends Serializable> implements Persistable<PK> {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected PK     id;

  @Override
  public PK getId() { return id;}

  @Override
  public boolean isNew() {
    return null == getId();
  }

  @Override
  public String toString() {
    return String.format("Entity of type %s with id: %s", this.getClass().getName(), getId());
  }
}