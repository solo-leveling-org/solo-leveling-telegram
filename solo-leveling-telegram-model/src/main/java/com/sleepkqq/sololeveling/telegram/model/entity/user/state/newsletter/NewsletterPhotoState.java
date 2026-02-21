package com.sleepkqq.sololeveling.telegram.model.entity.user.state.newsletter;

import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState;
import java.util.List;
import org.telegram.telegrambots.meta.api.objects.message.Message;

public record NewsletterPhotoState(
    String name,
    List<LocalizedMessageDto> localizations
) implements BotSessionState {

  private static final String SKIP = "skip";

  @Override
  public LocalizationCode onEnterMessageCode() {
    return LocalizationCode.STATE_NEWSLETTER_PHOTO_ENTER;
  }

  @Override
  public BotSessionState nextState(Message message) {
    if (message.hasText() && message.getText().toLowerCase().contains(SKIP)) {
      return new NewsletterDateTimeState(name, localizations, null);
    }

    if (message.hasPhoto()) {
      var fileId = message.getPhoto().getLast().getFileId();
      return new NewsletterDateTimeState(name, localizations, fileId);
    }

    return this;
  }
}
