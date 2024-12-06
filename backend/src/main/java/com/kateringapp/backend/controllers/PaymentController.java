package com.kateringapp.backend.controllers;

import com.kateringapp.backend.dtos.PaymentRequest;
import com.kateringapp.backend.dtos.PaymentResponse;
import com.kateringapp.backend.services.PaymentService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/create")
    public ResponseEntity<PaymentResponse> createPayment(@RequestBody PaymentRequest paymentRequest, @AuthenticationPrincipal Jwt jwt) {
        PaymentResponse paymentResponse = paymentService.createPayment(paymentRequest, jwt);
        return ResponseEntity.ok(paymentResponse);
    }

    @PostMapping("/{id}/process")
    public ResponseEntity<PaymentResponse> processPayment(@PathVariable String id, @AuthenticationPrincipal Jwt jwt) {
        PaymentResponse paymentResponse = paymentService.processPayment(id, jwt);
        return ResponseEntity.ok(paymentResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PaymentResponse> getPaymentStatus(@PathVariable String id) {
        PaymentResponse paymentResponse = paymentService.getPaymentStatus(id);
        return ResponseEntity.ok(paymentResponse);
    }
}
