package com.payment.payment.Entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "Details about Payment entity")
public class Payment {

    public Long getId() {
        return id;
    }

    @Id
    @GeneratedValue
    @ApiModelProperty(notes = "The database generated Payment ID")
    Long id;

    public Long getReservationId() {
        return reservationId;
    }

    @NotNull
    @ApiModelProperty(notes = "The associated reservation ID")
    Long reservationId;

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setPaymentState(int paymentState) {
        this.paymentState = paymentState;
    }


    BigDecimal price;

    public void setCreationDateTime(LocalDateTime creationDateTime) {
        this.creationDateTime = creationDateTime;
    }

    @NotNull
    LocalDateTime creationDateTime;

    int paymentState;

}
