package com.example.FirstSpringBoot.api.Service;

import com.example.FirstSpringBoot.api.Daos.UserDao;
import com.example.FirstSpringBoot.api.Model.UserMflix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<UserMflix> getAllUser() {

        List<UserMflix> listOfAllUsers = new ArrayList<>();

        // Apply some logic to serve request to controller.


        // Do Some Database Interaction work with userDao.
        listOfAllUsers = userDao.getAllUser();

        // Again(if needs) apply some business logic to satisfy request.

        return listOfAllUsers;
    }

    public UserMflix insertUser(UserMflix user) {
        UserMflix returnedUserInst = userDao.InsertUser(user);
        return returnedUserInst;
    }

    public List<UserMflix> findUser(String name) {
        List<UserMflix> ListOfUsers = userDao.findUser(name);

        return ListOfUsers;
    }
}
