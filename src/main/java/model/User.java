package model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    private int id;
    private String username;
    private String hashPassword;

    public User(String username, String hashPassword) {
        this.hashPassword = hashPassword;
        this.username = username;
    }
}
