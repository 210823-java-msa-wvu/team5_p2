package com.team5.PokemonTrading.models;

import javax.persistence.*;

@Entity
@Table(name="wishlist")
public class Wishlist {

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",insertable = false)
    private User userid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokeid",insertable = false)
    private Pokemon pokeid;

    public Wishlist(User userid, Pokemon pokeid) {
        this.userid = userid;
        this.pokeid = pokeid;
    }

    public Wishlist() {
    }

    public User getUserid() {
        return userid;
    }

    public void setUserid(User userid) {
        this.userid = userid;
    }

    public Pokemon getPokeid() {
        return pokeid;
    }

    public void setPokeid(Pokemon pokeid) {
        this.pokeid = pokeid;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "userid=" + userid +
                ", pokeid=" + pokeid +
                '}';
    }
}
