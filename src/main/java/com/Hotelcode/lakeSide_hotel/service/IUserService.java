package com.Hotelcode.lakeSide_hotel.service;

import com.Hotelcode.lakeSide_hotel.model.User;

import java.util.List;

/**
 * @author Simpson Alfred
 */

public interface IUserService {
    User registerUser(User user);
    List<User> getUsers();
    void deleteUser(String email);
    User getUser(String email);
}
