package sda.academy.travelagency.Service.service;

import sda.academy.travelagency.Entity.Hotel;

import java.util.List;
import java.util.Optional;

public interface HotelService {
    public List<Hotel> getAllHotels();

   // public List<Hotel> findByHotelName(String hotelName);

    public void deleteHotel(int hotelId);

    public Hotel updateHotel(int id, Hotel updatedHotel);

    //  public Hotel getHotelById(Integer id);

    public Hotel addHotel(Hotel hotel);

   // public Hotel findHotelById(int hotelId);


    public Hotel findByHotelId(int id);
}
