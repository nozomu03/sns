package com.example.sns;

public interface UserService {
    User getUser(String userId, String password);
    boolean add(String userId, String password, String userName, String email);
}
