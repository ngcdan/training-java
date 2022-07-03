package db.migration;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

@Slf4j
public class V1_0_1__TestMigrate extends BaseJavaMigration {
  @Override
  public void migrate(Context context) throws Exception {
    log.info("Migration Test..........");
  }
}