package br.com.notification.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationResponse {

    private String customer_cpf;
    private String message;
    private String sender;
    private String customer_email;
    private Boolean fraud;
}
