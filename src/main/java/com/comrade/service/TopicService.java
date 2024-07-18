package com.comrade.service;

import com.comrade.annotations.DcTransaction;

public interface TopicService {
  @DcTransaction
  void createTopic();
  void view();
}
