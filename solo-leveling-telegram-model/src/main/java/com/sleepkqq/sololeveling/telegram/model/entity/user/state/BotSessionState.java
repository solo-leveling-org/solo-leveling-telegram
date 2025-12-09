package com.sleepkqq.sololeveling.telegram.model.entity.user.state;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer.*;
import java.util.Map;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    include = JsonTypeInfo.As.PROPERTY,
    property = "@type",
    defaultImpl = IdleState.class
)
@JsonSubTypes({
    @JsonSubTypes.Type(value = IdleState.class, name = "IdleState"),
    @JsonSubTypes.Type(value = TransferAmountState.class, name = "WaitingAmountState"),
    @JsonSubTypes.Type(value = TransferRecipientState.class, name = "WaitingRecipientState"),
    @JsonSubTypes.Type(value = TransferConfirmationState.class, name = "WaitingConfirmationState")
})
public interface BotSessionState {

  LocalizationCode messageCode();

  default Map<String, Object> messageParams() {
    return Map.of();
  }

  default BotSessionState nextState(String userInput) {
    return new IdleState();
  }
}
