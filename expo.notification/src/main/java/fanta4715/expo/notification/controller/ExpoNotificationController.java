package fanta4715.expo.notification.controller;

import fanta4715.expo.notification.service.ExpoNotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ExpoNotificationController {

    private final ExpoNotificationService expoNotificationService;

    @PostMapping("/send")
    public ResponseEntity<?> sendNotification(
            @RequestParam String token1,
            @RequestParam String token2,
            @RequestParam String title,
            @RequestParam String body
    ) {
        expoNotificationService.sendPushNotification(token1, token2, title, body);
        return ResponseEntity.ok().body("Notification sent successfully!");
    }
}
