package com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState;
import java.util.Map;

@JsonTypeName("WaitingConfirmationState")
public record TransferConfirmationState(int amount, String recipientUsername)
    implements BotSessionState {

  @Override
  public LocalizationCode messageCode() {
    return LocalizationCode.STATE_TRANSFER_CONFIRMATION;
  }

  @Override
  public Map<String, Object> messageParams() {
    return Map.of(
        "amount", amount,
        "recipient", recipientUsername
    );
  }
}
