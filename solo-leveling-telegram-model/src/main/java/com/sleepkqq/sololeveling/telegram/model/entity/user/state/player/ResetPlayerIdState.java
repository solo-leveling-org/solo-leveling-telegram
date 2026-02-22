package com.sleepkqq.sololeveling.telegram.model.entity.user.state.player;

import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.localization.StateCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState;
import org.telegram.telegrambots.meta.api.objects.message.Message;

public record ResetPlayerIdState() implements BotSessionState {

  @Override
  public LocalizationCode onEnterMessageCode() {
    return StateCode.RESET_PLAYER_ENTER;
  }

  @Override
  public BotSessionState nextState(Message message) {
    if (!message.hasText()) {
      return this;
    }

    var text = message.getText().trim();

    try {
      var userId = Long.parseLong(text);
      return new ResetPlayerConfirmationState(userId);

    } catch (NumberFormatException _) {
      return this;
    }
  }
}
