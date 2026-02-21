package com.sleepkqq.sololeveling.telegram.model.entity;

import java.time.Instant;
import org.babyfish.jimmer.sql.MappedSuperclass;
import org.babyfish.jimmer.sql.Version;

@MappedSuperclass
public interface Model extends Auditable {

  Instant updatedAt();

  @Version
  int version();
}
