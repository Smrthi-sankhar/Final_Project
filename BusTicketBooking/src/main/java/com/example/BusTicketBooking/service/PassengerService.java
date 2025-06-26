package com.example.BusTicketBooking.service;


import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.BusTicketBooking.model.Passenger;
import com.example.BusTicketBooking.model.User;
import com.example.BusTicketBooking.repository.PassengerRepository;



@Service
public class PassengerService {
	
	private PassengerRepository passengerRepository;
	private UserService userservice;
	
	 Logger logger = LoggerFactory.getLogger(PassengerService.class);

	

    public PassengerService(PassengerRepository passengerRepository, UserService userservice) {
		super();
		this.passengerRepository = passengerRepository;
		this.userservice = userservice;
	}

	public Passenger insertpassenger(Passenger passenger) {
    	User user =passenger.getUser();
    	user.setRole("PASSENGER");
    	user =userservice.signUp(user);
    	passenger.setUser(user);
		return passengerRepository.save(passenger);
	}

	public List<Passenger> getAllPassengers() {
		
		return passengerRepository.findAll() ;
	}

	public void deletePassenger(int id) {
		Passenger passenger=passengerRepository.findById(id).orElseThrow(() -> new RuntimeException("invalid id"));
		passengerRepository.delete(passenger);
	}

	public Passenger getPassengerByUsername(String username) {
		return passengerRepository.getPassengerByUsername(username);
		
		
	}

	public Passenger getAuthorInfo(String username) {
	
			 /* Fetch Author Info by username */
	        return passengerRepository .getPassengerByUsername(username);
		}

	public Passenger uploadProfilePic(MultipartFile file, String username) throws IOException {
		  /* Fetch Author Info by username */
		 Passenger passenger = passengerRepository.getPassengerByUsername(username);
        logger.info("This is author --> " + passenger.getName());
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
        passenger.setProfilePic(originalFileName);
        /* Save author Object */
        return passengerRepository.save(passenger);
    }
	}
		
	
	

	
	

