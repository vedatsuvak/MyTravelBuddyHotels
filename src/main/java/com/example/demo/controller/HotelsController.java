package com.example.demo.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Hotels;
import com.example.demo.service.HotelsService;

@RestController
//spring boot app is open to gt accessed for any external applciation 
//@CrossOrigin(origins = "localhost:4200")
@CrossOrigin(origins = "*")
public class HotelsController {
	@Autowired
	private HotelsService service;

	@PostMapping("/insert")
	public Hotels insert(@RequestBody Hotels hotel) {
		service.insert(hotel);
		return hotel;

	}

	@PostMapping("/update")
	public String updateHotel(@RequestBody Hotels hotel) {
		service.updateHotel(hotel);
		return "Hey, Hotel: " + hotel.getHotelId() + ", successfully updated!";

	}

	@GetMapping("/getAllHotels")
	public List<Hotels> findAllHotels() {
		return service.getAll();
	}

	@GetMapping("/getOne/{id}")
	public Hotels findHotel(@PathVariable int id) {
		return service.findById(id).orElse(null);
	}

	@GetMapping("/getPrice/{hotelName}")
	public Integer findprice(@PathVariable String hotelName) {
		int price;
		price = service.findprice(hotelName);
		return price;
	}

	@GetMapping("/findHotel/{hotelName}")
	public Hotels findHotel(@PathVariable String hotelName) {
		return service.findHotel(hotelName);
	}

	@GetMapping("/getHotelsByLocation/{location}/{checkInDate}/{checkOutDate}")
	public List<Hotels> findHotelByLocation(@PathVariable String location, @PathVariable String checkInDate,
			@PathVariable String checkOutDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate checkIn = LocalDate.parse(checkInDate, formatter);
		LocalDate checkOut = LocalDate.parse(checkOutDate, formatter);
		return service.findHotelByLocation(location, checkIn, checkOut);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable int id) {
		service.deleteById(id);
		return "Hotel deleted successfully";
	}
}
