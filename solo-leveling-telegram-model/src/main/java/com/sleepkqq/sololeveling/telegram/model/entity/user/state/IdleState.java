package com.sleepkqq.sololeveling.telegram.model.entity.user.state;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.localization.StateCode;

@JsonTypeName("IdleState")
public record IdleState() implements BotSessionState {

  @Override
  public LocalizationCode onEnterMessageCode() {
    return StateCode.IDLE;
  }
}
