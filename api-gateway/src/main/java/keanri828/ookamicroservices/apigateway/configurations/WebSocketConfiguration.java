package keanri828.ookamicroservices.apigateway.configurations;

import keanri828.ookamicroservices.apigateway.handler.ApiWebSocketHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfiguration implements WebSocketConfigurer {

    private final static String API_ENDPOINT = "/socket/websocket";
    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(getApiWebSocketHandler(), API_ENDPOINT)
                .setAllowedOrigins("*");
    }

    @Bean
    public WebSocketHandler getApiWebSocketHandler() {
        return new ApiWebSocketHandler();
    }
}
