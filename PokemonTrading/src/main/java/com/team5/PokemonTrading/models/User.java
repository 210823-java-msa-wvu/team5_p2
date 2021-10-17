package com.team5.PokemonTrading.models;

import javax.persistence.*;
import java.io.Serializable;

@Entity

@Table(name="users")

public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    // @Column(nullable = false, updatable = false) //so it can't be updated in our database
    @Column(name="id")
    private Integer id;
    @Column(name="username")
    private String username;
    @Column(name="password")
    private String password;
    @Column(name="balance")
    private Float balance;

    public User() {
    }

    public User(Integer id) {
        this.id = id;
    }


    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }


    public User(Integer id, String username, String password, Float balance) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.balance = balance;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Float getBalance() {
        return balance;
    }

    public void setBalance(Float balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "Users{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", balance=" + balance +
                '}';
    }
}
