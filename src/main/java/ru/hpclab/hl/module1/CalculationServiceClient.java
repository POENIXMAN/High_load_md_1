package ru.hpclab.hl.module1;



import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "calculation-service", url = "${calculation.service.url}")
public interface CalculationServiceClient {
    @GetMapping("/calculations/average/class/{subjectId}/year/{year}")
    double calculateAverageGradeForClass(
            @PathVariable("subjectId") UUID subjectId,
            @PathVariable("year") int year);
}
