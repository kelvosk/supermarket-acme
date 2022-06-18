package br.com.notification.model;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_notification")
public class NotificationEntity {

    @Id
    @SequenceGenerator(
            name = "notification_id_sequence",
            sequenceName = "notification_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue (
            strategy = GenerationType.SEQUENCE,
            generator = "notification_id_sequence"
    )
    @Column(name = "id")
    private Long id;

    @Column(name = "sent_at")
    private LocalDateTime sentAt = LocalDateTime.now();

    @Column(name = "message")
    private String message;

    @Column(name = "sender")
    private String sender;

    @Column(name = "customer_email")
    private String customer_email;

    @Column(name = "fraud")
    private Boolean fraud = true;

    @Column(name = "customer_cpf")
    private String customer_cpf;
}
