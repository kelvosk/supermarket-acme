package br.com.clients.fraud.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InternalResponseFraud {

    private Boolean isFraud;
}
