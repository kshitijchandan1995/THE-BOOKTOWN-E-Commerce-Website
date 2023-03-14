package com.app.controller;


import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.dto.web_product.cart.CreateOrderRequest;
import com.app.dto.web_product.cart.CreateOrderResponse;
import com.app.dto.web_product.cart.PreviewOrderRequest;
import com.app.dto.web_product.cart.PreviewOrderResponse;
import com.app.service.OrderService;


@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class OrderController {
    
    @Autowired
    OrderService orderService;
    
    @PostMapping("/order")
    public ResponseEntity<CreateOrderResponse> createOrder(@RequestBody CreateOrderRequest createOrderRequest) {
   System.out.println(createOrderRequest.getUserName()+"ggg"+createOrderRequest.getAddressId());
        CreateOrderResponse createOrderResponse = orderService.createOrder(createOrderRequest);
        return ResponseEntity.ok(createOrderResponse);
    }

//    @GetMapping("/order/{orderId}")
//    public ResponseEntity<CreateOrderResponse> getOrderById(@PathVariable("orderId") String orderId) {
//
//        CreateOrderResponse createOrderResponse = orderService.getOrderById(orderId);
//        return ResponseEntity.ok(createOrderResponse);
//    }
//
//    @GetMapping("/order/myorders")
//    public ResponseEntity<List<CreateOrderResponse>> getMyOrders() {
//
//        List<CreateOrderResponse> createOrderResponse = orderService.getMyOrders();
//        return ResponseEntity.ok(createOrderResponse);
//    }
//
//    @GetMapping("/orders")
//    @PreAuthorize("hasAuthority('ADMIN_USER')")
//    public ResponseEntity<List<CreateOrderResponse>> getAllOrders() {
//        List<CreateOrderResponse> createOrderResponse = orderService.getAllOrders();
//        return ResponseEntity.ok(createOrderResponse);
//    }

    @PostMapping("/previewOrder")
    public ResponseEntity<PreviewOrderResponse> previewOrder(@RequestBody @Valid PreviewOrderRequest previewOrderRequest) {

        PreviewOrderResponse previewOrderResponse = orderService.previewOrder(previewOrderRequest);

        return ResponseEntity.ok(previewOrderResponse);
    }
}
