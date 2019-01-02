package com.b2s.service.adminapp.service;

import com.b2s.service.adminapp.exception.BadRequestException;
import com.b2s.service.adminapp.model.User;
import org.springframework.stereotype.Component;

/**
 * Created by spattabiraman on 12/13/2018.
 */
@Component
public class UserValidator {

    public void validate(final User user){

        if (user.getUserId().isEmpty()){
            throw new BadRequestException("Id field is empty");
        }

        if (user.getUserPassword().isEmpty()){
            throw new BadRequestException("Password is empty");
        }

        if (user.getUserName().isEmpty()){
            throw new BadRequestException("Name is empty");
        }
    }
}
