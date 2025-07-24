package com.bal.task_service.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "AUTH-SERVICE")
public interface AuthServiceClient  {

    @GetMapping("/api/auth/validate-token")
    String getUserIdFromToken(String token);
}
