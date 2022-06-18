package br.com.customer.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_customer")
public class CustomerEntity {

    @Id
    @SequenceGenerator(
            name = "customer_id_sequence",
            sequenceName = "customer_id_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_id_sequence"
    )
    private Long id;

    @Column(name = "customer_name")
    private String customer_name;

    @Column(name = "customer_email")
    private String customerEmail;

    @Column(name = "customer_cpf")
    private String customerCpf;
}
