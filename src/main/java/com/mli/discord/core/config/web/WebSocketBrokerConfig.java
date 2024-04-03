package com.mli.discord.core.config.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import com.mli.discord.core.Interceptor.CustomHttpSessionHandshakeInterceptor;
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketBrokerConfig implements WebSocketMessageBrokerConfigurer {

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-message")
                .setAllowedOrigins("http://localhost:8090")
                .withSockJS()
                .setInterceptors(new CustomHttpSessionHandshakeInterceptor());
    }

    // @Override
    // public void configureMessageBroker(MessageBrokerRegistry config) {
    // config.enableSimpleBroker("/topic");
    // config.setApplicationDestinationPrefixes("/app");
    // }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/topic"); // Use /topic/message/ as the broker prefix
        config.setApplicationDestinationPrefixes("/app");
    }
    
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(new ChannelInterceptor() {
            @Override
            public Message<?> preSend(Message<?> message, MessageChannel channel) {
                StompHeaderAccessor accessor =
                    MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
                if (StompCommand.SUBSCRIBE.equals(accessor.getCommand()) || StompCommand.UNSUBSCRIBE.equals(accessor.getCommand())) {
                    String destination = accessor.getDestination();
                    System.out.println(accessor.getCommand() + " to: " + destination);
                }
                return message;
            }
        });
    }
}