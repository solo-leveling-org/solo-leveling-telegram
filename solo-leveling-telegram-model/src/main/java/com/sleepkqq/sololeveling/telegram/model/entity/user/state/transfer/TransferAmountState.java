package com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState;

@JsonTypeName("WaitingAmountState")
public record TransferAmountState() implements BotSessionState {

  @Override
  public LocalizationCode messageCode() {
    return LocalizationCode.STATE_TRANSFER_AMOUNT;
  }

  @Override
  public BotSessionState nextState(String userInput) {
    try {
      return new TransferRecipientState(Integer.parseInt(userInput));
    } catch (NumberFormatException e) {
      return this;
    }
  }
}
