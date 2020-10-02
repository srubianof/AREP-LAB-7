package edu.eci.arep.model;

public class AuthenticationDetails {
    public String username = null;
    public String password = null;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public AuthenticationDetails(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
