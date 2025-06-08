package com.example.BusTicketBooking.dto;


import org.springframework.stereotype.Component;
import com.example.BusTicketBooking.model.Cancellation;

@Component
public class CancelDto {
	
	    private int id;
	    private String reason;
	    private Double refundAmount;
	    private int BookingId;
		public int getId() {
			return id;
		}
		public void setId(int id) {
			this.id = id;
		}
		public String getReason() {
			return reason;
		}
		public void setReason(String reason) {
			this.reason = reason;
		}
		public Double getRefundAmount() {
			return refundAmount;
		}
		public void setRefundAmount(Double refundAmount) {
			this.refundAmount = refundAmount;
		}
		public int getBookingId() {
			return BookingId;
		}
		public void setBookingId(int bookingId) {
			BookingId = bookingId;
		}

		 public CancelDto convertCancellationToDto(Cancellation cancellation) {
			    CancelDto dto = new CancelDto();
			    dto.setId(cancellation.getId());
			    dto.setBookingId(cancellation.getBooking().getId());
			    dto.setReason(cancellation.getReason());
			    dto.setRefundAmount(cancellation.getRefundAmount());
			    return dto;
			}

}
