package fanta4715.expo.notification.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
@RequiredArgsConstructor
public class ExpoNotificationService {
    private static final String EXPO_SEND_URL = "https://exp.host/--/api/v2/push/send";

    /**
     * Expo 서버로 push notification을 보내는 메소드
     * @param expoToken : 보내고 싶은 사용자1의 expo token
     * @param expoToken2 : 보내고 싶은 사용자2의 expo token
     * @param title : notification의 제목
     * @param body : notification의 내용
     * @return : expo 서버로부터 받은 응답. 성공 시 "ok"를 반환
     *
     * 공식문서
     * https://docs.expo.dev/push-notifications/sending-notifications/#push-tickets
     */
    public String sendPushNotification(String expoToken, String expoToken2, String title, String body) {
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> notification = new HashMap<>();
        notification.put("to", expoToken);
        notification.put("sound", "default");
        notification.put("title", title);
        notification.put("body", body);
        notification.put("priority", "high");  // Optional: You can set the priority as default, normal or high.

        Map<String, Object> notification2 = new HashMap<>();
        notification2.put("to", expoToken2);
        notification2.put("sound", "default");
        notification2.put("title", title);
        notification2.put("body", body);
        notification2.put("priority", "high");

        HttpEntity<List<Map<String, Object>>> request = new HttpEntity<>(List.of(notification, notification2), headers);
        log.info("Request: {}", request.getBody());

        ResponseEntity<String> response = restTemplate.exchange(EXPO_SEND_URL, HttpMethod.POST, request, String.class);
        log.info("Response: {}", response.getBody());

        return response.getBody();
    }
}
