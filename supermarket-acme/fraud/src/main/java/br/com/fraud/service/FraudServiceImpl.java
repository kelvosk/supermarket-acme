package br.com.fraud.service;

import br.com.fraud.repository.FraudRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class FraudServiceImpl implements  FraudService{

    private final FraudRepository fraudRepository;

    public FraudServiceImpl(FraudRepository fraudRepository) {
        this.fraudRepository = fraudRepository;
    }

    @Override
    public boolean isFraud(Long customerId) {
        try {
            var fraud = this.fraudRepository.findByCustomerId(customerId);
            return fraud.isFraud();
        } catch (Exception e) {
            log.info("Error finding fraud, reason: " + e.getMessage());
        }

        return true;
    }
}
