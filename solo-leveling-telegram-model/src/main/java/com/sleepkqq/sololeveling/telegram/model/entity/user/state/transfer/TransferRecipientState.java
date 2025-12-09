package com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState;

@JsonTypeName("WaitingRecipientState")
public record TransferRecipientState(long amount) implements BotSessionState {

  @Override
  public LocalizationCode onEnterMessageCode() {
    return LocalizationCode.STATE_TRANSFER_RECIPIENT;
  }

  @Override
  public BotSessionState nextState(String userInput) {
    if (!userInput.startsWith("@")) {
      return this;
    }

    return new TransferConfirmationState(amount, userInput);
  }
}
