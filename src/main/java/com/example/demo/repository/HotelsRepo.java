package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Hotels;

public interface HotelsRepo extends JpaRepository<Hotels, Integer> {

	String query = "select h.baseFare from Hotels h where h.hotelName=?1";

	@Query(query)
	Integer findprice(String hotelName);

	String query1 = "select h from Hotels h where h.hotelName=?1";

	@Query(query1)
	Hotels findHotel(String hotelName);

	String query2 = "SELECT h FROM Hotels h WHERE h.location = ?1 AND h.date BETWEEN ?2 AND ?3";

	@Query(query2)
	List<Hotels> findHotelsByLocationAndDateRange(String location, String checkInDate, String checkOutDate);
}
