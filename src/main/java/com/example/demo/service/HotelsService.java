package com.example.demo.service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Hotels;
import com.example.demo.repository.HotelsRepo;

@Service
public class HotelsService {

	@Autowired
	private HotelsRepo repo;

	// Insert a Hotel
	public Hotels insert(Hotels hotels) {
		return repo.save(hotels);
	}

	// Retrieve all Hotels
	public List<Hotels> getAll() {
		return repo.findAll();
	}

	// Retrieve a Hotel by ID
	public Optional<Hotels> findById(int id) {
		return repo.findById(id);
	}

	// Retrieve price by name
	public Integer findprice(String hotelName) {
		return repo.findprice(hotelName);
	}

	// Retrieve a Hotel by name
	public Hotels findHotel(String hotelName) {
		return repo.findHotel(hotelName);
	}

	// Retrieve a Hotel by location
	public List<Hotels> findHotelByLocation(String location, LocalDate checkInDate, LocalDate checkOutDate) {
		List<Hotels> hotels = repo.findHotelsByLocationAndDateRange(location, checkInDate.toString(),
				checkOutDate.toString());

		System.out.println(hotels);

		Map<String, List<Hotels>> groupedHotels = hotels.stream().collect(Collectors.groupingBy(Hotels::getHotelName));

		return groupedHotels.values().stream()
				.filter(hotelList -> isAvailableForDateRange(hotelList, checkInDate, checkOutDate))
				.map(hotelList -> hotelList.get(0)).collect(Collectors.toList());
	}

	private boolean isAvailableForDateRange(List<Hotels> hotelList, LocalDate checkInDate, LocalDate checkOutDate) {
		long daysBetween = ChronoUnit.DAYS.between(checkInDate, checkOutDate);
		return hotelList.size() >= daysBetween + 1;
	}

	// update Hotel
	public Hotels updateHotel(Hotels hotel) {
		return repo.save(hotel);
	}

	// Delete a Hotel by ID
	public String deleteById(int id) {
		repo.deleteById(id);
		return "Deleted";
	}
}
