package com.example.BusTicketBooking;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
	
	@Autowired
	private JwtFilter jwtFilter;
	

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf((csrf) -> csrf.disable()) 
			.authorizeHttpRequests(authorize -> authorize
					.requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
					.requestMatchers("/api/user/signup").permitAll()
					.requestMatchers("/api/user/token").permitAll()
					.requestMatchers("/api/admin/add").permitAll()
					.requestMatchers("/api/user/details").permitAll()
												/*PASSENGER*/
					.requestMatchers("/api/passenger/add").permitAll()
					.requestMatchers("/api/passenger/get-one").hasAuthority("PASSENGER")
					.requestMatchers("/api/passenger/all").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/passenger/delete/{Id}").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/passenger/upload/profile-pic").permitAll()
												/*OPERATOR*/
					.requestMatchers("/api/operator/add").permitAll()//hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/operator/all").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/operator/delete/{Id}").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/operator/get-one").hasAuthority("PASSENGER")
					.requestMatchers("/api/operator/get").hasAuthority("OPERATOR")
					.requestMatchers("/api/operator/upload/profile-pic").permitAll()
					.requestMatchers("/api/operator/bus-stats").permitAll()
												/* ROUTE*/
					.requestMatchers("/api/route/add").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/route/get/{id}").permitAll()
					.requestMatchers("/api/route/get-all").permitAll()
					.requestMatchers("/api/route/update/{id}").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/route/delete/{id}").hasAnyAuthority("ADMIN","OPERATOR")
												 /* BUS*/
					.requestMatchers("/api/bus/add/{routeId}").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/bus/get/{id}").permitAll()
					.requestMatchers("/api/bus/get-all").permitAll()
					.requestMatchers("/api/bus/search").permitAll()
					.requestMatchers("/api/bus/get-by-operator").permitAll()
					.requestMatchers("/api/bus/update/{id}").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/bus/delete/{id}").hasAnyAuthority("ADMIN","OPERATOR")
												 /* SCHEDULE*/
					.requestMatchers("/api/schedule/add/{busId}/{routeId}").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/schedule/get-all").permitAll()
					.requestMatchers("/api/schedule/delete/{id}").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/schedule/update/{id}").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/schedule/get/{id}").permitAll()
												   /* SEATS*/
					.requestMatchers("/api/seat/generate/{scheduleId}").hasAnyAuthority("ADMIN","OPERATOR")
					.requestMatchers("/api/seat/available/{scheduleId}").permitAll()
					.requestMatchers("/api/seat/all/{scheduleId}").permitAll()
					.requestMatchers("/api/seat/seats/{scheduleId}").permitAll()
					                               /* BOOKING*/
					.requestMatchers("/api/booking/add/{scheduleId}").hasAuthority("PASSENGER")
					.requestMatchers("/api/booking/passenger/bookings").permitAll()
					.requestMatchers("/api/booking/{bookingid}").permitAll()
					.requestMatchers("/api/booking/cancel/{id}").hasAuthority("PASSENGER")
												 /* PAYMENT*/
					.requestMatchers("/api/payment/make/{bookingId}").hasAuthority("PASSENGER")
					.requestMatchers("/api/payment/get/{Id}").hasAnyAuthority("ADMIN","OPERATOR")
					 							/* CANCELLATION*/
					.requestMatchers("/api/cancel/{bookingId}").hasAuthority("PASSENGER")
					.requestMatchers("/api/cancel/get/{Id}").hasAnyAuthority("ADMIN","OPERATOR")
				.anyRequest().authenticated()  
			)
			
			.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
		 .httpBasic(Customizer.withDefaults()); //<- this activated http basic technique
		return http.build();
	}
	 @Bean
		public PasswordEncoder passwordEncoder() {
		    return new BCryptPasswordEncoder();
		}
	 
	 @Bean
		AuthenticationManager getAuthManager(AuthenticationConfiguration auth) 
				throws Exception {
			  return auth.getAuthenticationManager();
		 }

}
