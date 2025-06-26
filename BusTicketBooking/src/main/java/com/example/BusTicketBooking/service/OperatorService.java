package com.example.BusTicketBooking.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.BusTicketBooking.dto.OperatorStatsDto;
import com.example.BusTicketBooking.model.Bus;
import com.example.BusTicketBooking.model.Operator;
import com.example.BusTicketBooking.model.User;
import com.example.BusTicketBooking.repository.BookingRepository;
import com.example.BusTicketBooking.repository.BusRepository;
import com.example.BusTicketBooking.repository.OperatorRepository;


@Service
public class OperatorService {
	
	private OperatorRepository operatorRepository;
	private UserService userservice;
	private BusRepository busRepository;
	private BookingRepository bookingRepository;
	  Logger logger = LoggerFactory.getLogger(OperatorService.class);
	
	
	

	public OperatorService(OperatorRepository operatorRepository, UserService userservice, BusRepository busRepository,
			com.example.BusTicketBooking.repository.BookingRepository bookingRepository) {
		super();
		this.operatorRepository = operatorRepository;
		this.userservice = userservice;
		this.busRepository = busRepository;
		this.bookingRepository = bookingRepository;
	}

	public Operator insertoperator(Operator operator) {
		User user=operator.getUser();
		user.setRole("OPERATOR");
		user=userservice.signUp(user);
		operator.setUser(user);
		return operatorRepository.save(operator);
	}
	
	public List<Operator> getAllOperators() {	
		return operatorRepository.findAll() ;
	}

	public void deleteOperator(int id) {
		 operatorRepository.deleteById(id);
		
	}

	public Operator getOperatorByUsername(String username) {
		return operatorRepository.getOperatorByUsername(username);
	}

	public Operator uploadProfilePic(MultipartFile file, String username) throws IOException{
		  /* Fetch Author Info by username */
        Operator operator = operatorRepository.getOperatorByUsername(username);
        logger.info("This is author --> " + operator.getName());
        /* extension check: jpg,jpeg,png,gif,svg : */
        String originalFileName = file.getOriginalFilename(); // profile_pic.png
        logger.info(originalFileName.getClass().toString());

        logger.info("" + originalFileName.split("\\.").length);
        String extension = originalFileName.split("\\.")[1]; // png
        if (!(List.of("jpg", "jpeg", "png", "gif", "svg").contains(extension))) {
            logger.error("extension not approved " + extension);
            throw new RuntimeException("File Extension " + extension + " not allowed " + "Allowed Extensions"
                    + List.of("jpg", "jpeg", "png", "gif", "svg"));
        }
        logger.info("extension approved " + extension);
        /* Check the file size */
        long kbs = file.getSize() / 1024;
        if (kbs > 3000) {
            logger.error("File oversize " + kbs);
            throw new RuntimeException("Image Oversized. Max allowed size is " + kbs);
        }
        logger.info("Profile Image Size " + kbs + " KBs");

        /* Check if Directory exists, else create one */
        String uploadFolder = "D:\\React-Project\\react-bus-ui\\public\\images";
        Files.createDirectories(Path.of(uploadFolder));
        logger.info(Path.of(uploadFolder) + " directory ready!!!");
        /* Define the full path */
        Path path = Paths.get(uploadFolder, "\\", originalFileName);
        /* Upload file in the above path */
        Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        /* Set url of file or image in author object */
        operator.setProfilePic(originalFileName);
        /* Save author Object */
        return operatorRepository.save(operator);
    }

	public Operator getAuthorInfo(String username) {
		 /* Fetch Author Info by username */
        return operatorRepository.getOperatorByUsername(username);
	}
	
	public OperatorStatsDto getBusStats(String username, OperatorStatsDto dto) {
	    Operator operator = operatorRepository.getOperatorByUsername(username);
	    List<Bus> buses = busRepository.  findByOperator(operator);
	    List<String> busNumbers = new ArrayList<>();
	    List<Long> passengerCounts = new ArrayList<>();

	    buses.stream().forEach(bus -> {
	        long count = bookingRepository.countPassengersByBusId(bus.getId());
	        busNumbers.add(bus.getBusNumber());
	        passengerCounts.add(count);
	    });

	    dto.setBusNumbers(busNumbers);
	    dto.setPassengerCounts(passengerCounts);
	    return dto;
	}
 
	}


