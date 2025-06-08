package com.example.BusTicketBooking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.example.BusTicketBooking.dto.PaymentDto;
import com.example.BusTicketBooking.model.Booking;
import com.example.BusTicketBooking.model.Passenger;
import com.example.BusTicketBooking.model.Payment;
import com.example.BusTicketBooking.repository.BookingRepository;
import com.example.BusTicketBooking.repository.PaymentRepository;
import com.example.BusTicketBooking.service.PaymentService;

public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepo;

    @Mock
    private BookingRepository bookingRepo;

    @Mock
    private PaymentDto paymentDtoMock;

    @InjectMocks
    private PaymentService paymentService;

    private Booking booking;
    private Passenger passenger;
    private Payment payment;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        passenger = new Passenger();
        passenger.setName("Smrthi");

        booking = new Booking();
        booking.setId(1);
        booking.setTotalFare(1000);
        booking.setPassenger(passenger);

        payment = new Payment();
        payment.setId(1);
        payment.setAmount(1000);
        payment.setStatus("PAID");
        payment.setPaymentTime(LocalDateTime.now());
        payment.setBooking(booking);

        // Inject the real PaymentDto (not a mock) if needed
        paymentDtoMock = new PaymentDto();
        paymentService = new PaymentService(paymentRepo, bookingRepo, paymentDtoMock);
    }

    @Test
    public void testMakePayment() {
        when(bookingRepo.findById(1)).thenReturn(Optional.of(booking));
        when(paymentRepo.save(any(Payment.class))).thenReturn(payment);

        PaymentDto result = paymentService.makePayment(1);

        assertEquals(1, result.getId());
        assertEquals(1000, result.getAmount());
        assertEquals("PAID", result.getStatus());
        assertEquals("Smrthi", result.getPassengerName());

        verify(bookingRepo, times(1)).findById(1);
        verify(paymentRepo, times(1)).save(any(Payment.class));
    }

    @Test
    public void testGetPaymentById() {
        when(paymentRepo.findById(1)).thenReturn(Optional.of(payment));

        PaymentDto result = paymentService.getPaymentById(1);

        assertEquals(1, result.getId());
        assertEquals(1000, result.getAmount());
        assertEquals("Smrthi", result.getPassengerName());
        assertEquals("PAID", result.getStatus());

        verify(paymentRepo, times(1)).findById(1);
    }

    @Test
    public void testGetAllPayments() {
        List<Payment> paymentList = Arrays.asList(payment);

        when(paymentRepo.findAll()).thenReturn(paymentList);

        List<PaymentDto> result = paymentService.getAllPayments();

        assertEquals(1, result.size());
        assertEquals("Smrthi", result.get(0).getPassengerName());

        verify(paymentRepo, times(1)).findAll();
    }

    @Test
    public void testMakePayment_BookingNotFound() {
        when(bookingRepo.findById(999)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            paymentService.makePayment(999);
        });

        assertEquals("Invalid Booking ID", ex.getMessage());
        verify(bookingRepo, times(1)).findById(999);
    }

    @Test
    public void testGetPaymentById_NotFound() {
        when(paymentRepo.findById(999)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            paymentService.getPaymentById(999);
        });

        assertEquals("Invalid Payment ID", ex.getMessage());
        verify(paymentRepo, times(1)).findById(999);
    }
}

