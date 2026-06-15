package com.company.singleresp;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.fasterxml.jackson.databind.ObjectMapper;

//Handles incoming JSON requests that work on User resource/entity
public class UserController {
	//Store used by controller
    private Store store = new Store();
    private UserValidator userValidator;

    public UserController() {

    }

    public UserController(UserValidator userValidator) {
        this.userValidator = userValidator;
    }
    
    //Create a new user
    public String createUser(String userJson) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        
        User user = mapper.readValue(userJson, User.class);

        boolean isUserValid = userValidator.isValidUser(user);

        if(!isUserValid) {
            return "ERROR";
        }

        store.store(user);
        
        return "SUCCESS";
    }

}