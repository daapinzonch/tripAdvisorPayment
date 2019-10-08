package com.payment.payment.Service;

import com.payment.payment.Entity.Payment;
import com.payment.payment.Repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentService {
    @Autowired
    PaymentRepository paymentRepository;

    public List<Payment> getAllPayments(){
        return paymentRepository.findAll();
    }

    public Payment savePayment(Payment payment){
        //Calculate price based on reservation and publication things. TO DO
        Long resId  = payment.getReservationId();
        payment.setPrice(new BigDecimal(0));
        payment.setPaymentState(0);
        payment.setCreationDateTime(LocalDateTime.now());
        return paymentRepository.save(payment);
    }

    public Payment approvePayment(Long paymentId){
        Optional<Payment> opt = paymentRepository.findById(paymentId);
        if(opt.isPresent()){
            Payment payment = opt.get();
            payment.setPaymentState(1);
            paymentRepository.save(payment);
            return payment;
        }else{
            return null;
        }
    }
}
