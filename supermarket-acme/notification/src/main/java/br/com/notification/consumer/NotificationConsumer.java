package br.com.notification.consumer;

import br.com.clients.fraud.request.NotificationPayload;
import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.service.NotificationService;
import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Not;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;

    @RabbitListener(queues = "${rabbitmq.queue.notification}")
    public void consumer(NotificationPayload notificationResponseMessage) throws JSONException {
        log.info("Consumed {} from queue", notificationResponseMessage);
        this.notificationService.saveNotification(convertPayloadNotification(notificationResponseMessage));
    }

    private NotificationRequest convertPayloadNotification(NotificationPayload notificationPayload) {

        return NotificationRequest.builder()
                .sender(notificationPayload.getSender())
                .customer_cpf(notificationPayload.getCustomer_cpf())
                .customer_email(notificationPayload.getCustomer_email())
                .message(notificationPayload.getMessage())
                .build();
    }

    @Bean
    public Jackson2JsonMessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

}
