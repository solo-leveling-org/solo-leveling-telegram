package com.sleepkqq.sololeveling.telegram.model.entity.user.state;

public interface BotSessionState {

  String message();

  default BotSessionState nextState(String userInput) {
    return new IdleState();
  }
}
