package com.sleepkqq.sololeveling.telegram.model.entity.user.state.task;

import com.sleepkqq.sololeveling.telegram.localization.LocalizationCode;
import com.sleepkqq.sololeveling.telegram.localization.StateCode;
import com.sleepkqq.sololeveling.telegram.model.entity.user.state.BotSessionState;
import com.sleepkqq.sololeveling.telegram.task.TaskTopic;
import java.util.List;
import one.util.streamex.StreamEx;
import org.telegram.telegrambots.meta.api.objects.message.Message;

public record DeprecateTasksByTopicState() implements BotSessionState {

  @Override
  public LocalizationCode onEnterMessageCode() {
    return StateCode.TASKS_DEPRECATE_BY_TOPIC_ENTER;
  }

  @Override
  public List<Object> onEnterMessageParams() {
    return List.of(StreamEx.of(TaskTopic.getEntries()).joining("\n"));
  }

  @Override
  public BotSessionState nextState(Message message) {
    try {
      var taskTopic = TaskTopic.valueOf(message.getText());
      return new DeprecateTasksByTopicConfirmationState(taskTopic);

    } catch (IllegalArgumentException _) {
      return this;
    }
  }
}
