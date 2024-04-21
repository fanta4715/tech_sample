package fanta4715.expo.notification.service;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
public class ExpoNotificationService {
    private static final String EXPO_SEND_URL = "https://exp.host/--/api/v2/push/send";
    public String sendPushNotification(String expoToken, String title, String body) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        Map<String, Object> notification = new HashMap<>();
        notification.put("to", expoToken);
        notification.put("title", title);
        notification.put("body", body);
        notification.put("priority", "high");  // Optional: You can set the priority as default, normal or high.

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(notification, headers);

        ResponseEntity<String> response = restTemplate.exchange(EXPO_SEND_URL, HttpMethod.POST, request, String.class);
        return response.getBody();
    }
}
