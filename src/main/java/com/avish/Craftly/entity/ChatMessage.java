package com.avish.Craftly.entity;

import com.avish.Craftly.enums.MessageRole;

import java.time.Instant;

public class ChatMessage {
    Long id;

    ChatSession chatSession;

    String content;

    MessageRole role;

    String toolCalls;//JSON array

    Integer tokenUsed;

    Instant createdAt;


}
