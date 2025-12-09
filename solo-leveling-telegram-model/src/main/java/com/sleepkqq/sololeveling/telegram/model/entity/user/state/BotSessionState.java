package com.sleepkqq.sololeveling.telegram.model.entity.user.state;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.feedback.FeedbackMessageState;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer.TransferAmountState;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer.TransferConfirmationState;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.transfer.TransferRecipientState;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
    @JsonSubTypes.Type(value = IdleState.class, name = "IdleState"),
    @JsonSubTypes.Type(value = TransferAmountState.class, name = "TransferAmountState"),
    @JsonSubTypes.Type(value = TransferRecipientState.class, name = "TransferRecipientState"),
    @JsonSubTypes.Type(value = TransferConfirmationState.class, name = "TransferConfirmationState"),
    @JsonSubTypes.Type(value = FeedbackMessageState.class, name = "FeedbackMessageState")
})
public interface BotSessionState {

  /**
   * Локализационный код для сообщения при ВХОДЕ в состояние
   */
  LocalizationCode onEnterMessageCode();

  /**
   * Параметры для сообщения при входе
   */
  default Map<String, Object> onEnterMessageParams() {
    return Map.of();
  }

  /**
   * Локализационный код для сообщения при ВЫХОДЕ из состояния
   *
   * @return null если не нужно отправлять сообщение при выходе
   */
  @Nullable
  default LocalizationCode onExitMessageCode() {
    return null;
  }

  /**
   * Параметры для сообщения при выходе
   */
  default Map<String, Object> onExitMessageParams() {
    return Map.of();
  }

  /**
   * Обработка пользовательского ввода и переход в следующее состояние
   */
  default BotSessionState nextState(String userInput) {
    return new IdleState();
  }
}
