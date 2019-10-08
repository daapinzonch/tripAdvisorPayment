package com.payment.payment.Controller;

import com.payment.payment.Entity.Payment;
import com.payment.payment.Service.PaymentService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

@RestController
@Api(value = "Reservation Microservices", description = "Operations related to Payments")
@RequestMapping("/payment")
public class PaymentController {
    @Autowired
    PaymentService paymentService;

    @ApiOperation(value = "Get all payment records", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "List retrieved successfully"),
            @ApiResponse(code = 403, message = "Error")
    })
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Payment>> getAllPayments() {
        try {
            return new ResponseEntity<>(paymentService.getAllPayments(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation(value = "Store a payment record")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Payment saved successfully"),
            @ApiResponse(code = 403, message = "Error")
    })
    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<String> savePayment(@ApiParam(value = "Reservation Id", required = true)@RequestBody Payment payment) {
        try {
            paymentService.savePayment(payment);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }

    @ApiOperation(value = "Mark a payment record as approved")
    @ApiResponses(value = {
            @ApiResponse(code = 400, message = "Could not find payment record with that id"),
            @ApiResponse(code = 200, message = "Payment marked as approved"),
            @ApiResponse(code = 403, message = "Error")
    })
    @RequestMapping(value = "/approve/{paymentId}", method = RequestMethod.POST)
    public ResponseEntity<String> approvePayment(
            @ApiParam(value = "Payment Id", required = true) @PathVariable("paymentId") Long paymentId) {
        try {
            Payment result = paymentService.approvePayment(paymentId);
            if (result == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            } else {
                return new ResponseEntity<>(HttpStatus.OK);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.toString(), HttpStatus.FORBIDDEN);
        }
    }

}
