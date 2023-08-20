package pojos;

import java.time.LocalDate;

import javax.persistence.*;

@Embeddable
// the annotations not acceptable are Entity, Id, Table & generated value
public class PaymentDetails {

	@Enumerated(EnumType.STRING)
	@Column(name = "payment_mode", length = 20)
	private PaymentMode mode;
	@Column(name = "payment_date")
	private LocalDate paymentDate;
	private double amount;

	public PaymentDetails() {
		System.out.println("In payment mode ctor");
	}

	public PaymentDetails(PaymentMode mode, LocalDate paymentDate, double amount) {
		super();
		this.mode = mode;
		this.paymentDate = paymentDate;
		this.amount = amount;
	}

	public PaymentMode getMode() {
		return mode;
	}

	public void setMode(PaymentMode mode) {
		this.mode = mode;
	}

	public LocalDate getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "PaymentDetails [mode=" + mode + ", paymentDate=" + paymentDate + ", amount=" + amount + "]";
	}

}
