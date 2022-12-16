package dev.leonardpark.kotlinspringbootgradle.controller

import dev.leonardpark.kotlinspringbootgradle.data.ChatMessage
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.messaging.simp.SimpMessageHeaderAccessor
import org.springframework.stereotype.Controller

@Controller
class ChatController {

  /**
   * 新增聊天訊息
   */
  @MessageMapping("/sendMessage")
  @SendTo("/topic/public")
  fun sendMessage(@Payload chatMessage: ChatMessage): ChatMessage = chatMessage

  /**
   * 新增使用者
   */
  @MessageMapping("/addUser")
  @SendTo("/topic/public")
  fun addUser(@Payload chatMessage: ChatMessage, simpMessageHeaderAccessor: SimpMessageHeaderAccessor): ChatMessage {
    // 設定使用者姓名
    simpMessageHeaderAccessor.sessionAttributes?.put("username", chatMessage.sender)
    return chatMessage
  }
}
