package com.wohaha.notify.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document(collection = "tbl_notify")
public class Notify {

  @Id
  private Long notifySeq;
  private String content;
  private int type; //1. 채팅  2. 알림장  3. 투약 4. 등하원
  private Long typeSeq;
  private Long sendUserSeq;
  private Long receiveUserSeq;
  private boolean readState = false;
  private LocalDateTime createdAt;
}
