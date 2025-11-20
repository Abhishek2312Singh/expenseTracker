package com.example.Expense.Management.service;

import com.example.Expense.Management.config.SecurityConfig;
import com.example.Expense.Management.dto.UserInputDto;
import com.example.Expense.Management.entity.User;
import com.example.Expense.Management.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepo userRepo;

    @Autowired
    private SecurityConfig config;

    public String addUser(UserInputDto userInputDto){
        User user = new User();
        user.setFirstName(userInputDto.getFirstName());
        user.setLastName(userInputDto.getLastName());
        user.setMobile(userInputDto.getMobile());
        user.setEmail(userInputDto.getEmail());

        String username =String.valueOf( userInputDto.getFirstName().charAt(0)) +
              String.valueOf(userInputDto.getLastName().charAt(0));

        Integer count = userRepo.countByUsernameStartingWith(username);
        username = username + (1001 + count);
        user.setUsername(username);
        user.setPassword(config.encoder().encode(userInputDto.getPassword()));

        userRepo.save(user);
        return "User Created!!! User Id : " + username + "Save it for future use!!";
    }
}
