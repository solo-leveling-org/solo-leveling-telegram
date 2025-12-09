package com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState;

@JsonTypeName("TransferAmountState")
public record TransferAmountState() implements BotSessionState {

  @Override
  public LocalizationCode onEnterMessageCode() {
    return LocalizationCode.STATE_TRANSFER_AMOUNT;
  }

  @Override
  public BotSessionState nextState(String userInput) {
    try {
      var amount = Long.parseLong(userInput);
      if (amount <= 0) {
        return this;
      }
      return new TransferRecipientState(amount);
    } catch (NumberFormatException e) {
      return this;
    }
  }
}
