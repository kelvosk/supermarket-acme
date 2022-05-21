package br.com.notification.controller;

import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.controller.response.NotificationResponse;
import br.com.notification.service.NotificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/notifications")
public class NotificationController {

    private NotificationService notificationService;

    private NotificationController(NotificationService notificationService){
        this.notificationService = notificationService;
    }

    @Operation(summary = "create notification", description = "create notification")
    @ApiResponse(responseCode = "201", description = "Notification created")
    @PostMapping
    public ResponseEntity<NotificationResponse> createNotification(@RequestBody NotificationRequest notificationRequest){
        log.info("calling controller to create a notification {}", notificationRequest );
        this.notificationService.saveNotification(notificationRequest);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @Operation(summary = "list all notifications", description = "list notifications")
    @ApiResponse(responseCode = "200", description = "Notifications listed")
    @GetMapping
    public ResponseEntity<List<NotificationResponse>> listAllNotification(){
        log.info("calling controller to list a notification");

        return new ResponseEntity<>(this.notificationService.listAllNotification(), HttpStatus.OK);
    }


}
