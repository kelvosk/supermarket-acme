package br.com.fraud.controller.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudResponse {

    private boolean fraud;

    private String cpf;

    private String customerName;
}
