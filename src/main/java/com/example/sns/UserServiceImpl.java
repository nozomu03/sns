package com.example.sns;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository ur;

    @Override
    public User getUser(String userId, String password) {
        List<User> userList = this.ur.findAll();
        for(User temp : userList){
            if(temp.getUserId().equals(userId) && temp.getPassword().equals(password)){
                return temp;
            }
        }
        return null;
    }

    @Override
    public boolean add(String userId, String password, String userName, String email) {
        try {
            this.ur.save(new User(userId, password, userName, email));
            return true;
        }catch(Exception e){
            return false;
        }
    }
}
