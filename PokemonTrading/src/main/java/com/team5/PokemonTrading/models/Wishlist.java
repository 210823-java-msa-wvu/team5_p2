package com.team5.PokemonTrading.models;


import javax.persistence.*;

@Entity
@Table(name="wishlist")
public class Wishlist {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userid",insertable = false)
    private User userid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pokeid",insertable = false)
    private Pokemon pokeid;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Integer id;

    public Wishlist() {
    }

    public Wishlist(User userid, Pokemon pokeid) {
        this.userid = userid;
        this.pokeid = pokeid;
    }

    public Wishlist(User userid, Pokemon pokeid, Integer id) {
        this.userid = userid;
        this.pokeid = pokeid;
        this.id = id;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "userid=" + userid +
                ", pokeid=" + pokeid +
                ", id=" + id +
                '}';
    }
}
