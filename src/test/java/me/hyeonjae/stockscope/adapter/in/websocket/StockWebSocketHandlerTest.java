package me.hyeonjae.stockscope.adapter.in.websocket;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
@DisplayName("StockWebSocketHandler 테스트")
class StockWebSocketHandlerTest {

    @Mock
    private WebSocketSession session;
    
    private StockWebSocketHandler webSocketHandler;
    
    @BeforeEach
    void setUp() {
        webSocketHandler = new StockWebSocketHandler();
    }
    
    @Test
    @DisplayName("메시지를 받으면 연결된 모든 세션에 브로드캐스트한다")
    void handleTextMessage_ShouldBroadcastMessage() throws Exception {
        // given
        String payload = "test message";
        TextMessage message = new TextMessage(payload);
        webSocketHandler.afterConnectionEstablished(session);
        
        // when
        webSocketHandler.handleTextMessage(session, message);
        
        // then
        verify(session).sendMessage(any(TextMessage.class));
    }
    
    @Test
    @DisplayName("웹소켓 연결이 성공하면 세션을 저장한다")
    void afterConnectionEstablished_ShouldAddSession() throws Exception {
        // when
        webSocketHandler.afterConnectionEstablished(session);
        
        // then
        webSocketHandler.handleTextMessage(session, new TextMessage("test"));
        verify(session).sendMessage(any(TextMessage.class));
    }
    
    @Test
    @DisplayName("웹소켓 연결이 종료되면 세션을 제거한다")
    void afterConnectionClosed_ShouldRemoveSession() throws Exception {
        // given
        webSocketHandler.afterConnectionEstablished(session);
        
        // when
        webSocketHandler.afterConnectionClosed(session, CloseStatus.NORMAL);
        webSocketHandler.handleTextMessage(session, new TextMessage("test"));
        
        // then
        verify(session, times(0)).sendMessage(any(TextMessage.class));
    }
}