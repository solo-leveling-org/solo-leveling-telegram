package com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState;

@JsonTypeName("WaitingRecipientState")
public record TransferRecipientState(int amount) implements BotSessionState {

  @Override
  public LocalizationCode messageCode() {
    return LocalizationCode.STATE_TRANSFER_RECIPIENT;
  }

  @Override
  public BotSessionState nextState(String userInput) {
    return new TransferConfirmationState(amount, userInput);
  }
}
