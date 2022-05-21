package br.com.notification.service;

import br.com.notification.config.ConvertUtils;
import br.com.notification.controller.request.NotificationRequest;
import br.com.notification.controller.response.NotificationResponse;
import br.com.notification.model.NotificationEntity;
import br.com.notification.repository.NotificationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class NotificationServiceImpl implements NotificationService {

    private NotificationRepository notificationRepository;
    private final ConvertUtils convertUtils;

    public NotificationServiceImpl(NotificationRepository notificationRepository, ConvertUtils convertUtils) {
        this.notificationRepository = notificationRepository;
        this.convertUtils = convertUtils;
    }

    @Override
    public void saveNotification(NotificationRequest notificationRequest) {
        this.notificationRepository.save(
                (NotificationEntity) convertUtils.convertRequestToEntity(notificationRequest, NotificationEntity.class));
    }

    @Override
    public List<NotificationResponse> listAllNotification() {
        return convertUtils.convertToListResponse(this.notificationRepository.findAll(), NotificationResponse.class);
    }
}
