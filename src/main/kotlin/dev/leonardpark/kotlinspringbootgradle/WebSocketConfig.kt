package dev.leonardpark.kotlinspringbootgradle

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {

  override fun registerStompEndpoints(stompEndpointRegistry: StompEndpointRegistry) {
    stompEndpointRegistry.addEndpoint("/ws").setAllowedOrigins().withSockJS()
  }

  override fun configureMessageBroker(messageBrokerRegistry: MessageBrokerRegistry) {
    messageBrokerRegistry.setApplicationDestinationPrefixes("/app")
    messageBrokerRegistry.enableSimpleBroker("/topic")
  }
}
