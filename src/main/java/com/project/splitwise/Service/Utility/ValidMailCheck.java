package com.project.splitwise.Service.Utility;

import org.springframework.stereotype.Service;

@Service
public class ValidMailCheck {

    public Boolean checkValidEmail(String email){
        if(email == null)
            return false;
        if(email.length() < 6)
            return false;
        if(email.contains("@") && email.contains("."))
            return true;
        return false;
    }
}
