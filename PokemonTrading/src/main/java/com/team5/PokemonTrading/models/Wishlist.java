package com.team5.PokemonTrading.models;


import javax.persistence.*;

@Entity
@Table(name="wishlist")
public class Wishlist {

    @Id
//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "userid",insertable = false)
    @Column(name = "userid")
    private Integer userid;

//     @ManyToOne(fetch = FetchType.LAZY)
//     @JoinColumn(name = "pokeid",insertable = false)
    @Column(name = "pokeid")
    private Integer pokeid;

    public Wishlist(Integer userid, Integer pokeid) {
        this.userid = userid;
        this.pokeid = pokeid;
    }

    public Wishlist() {
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Integer getPokeid() {
        return pokeid;
    }

    public void setPokeid(Integer pokeid) {
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
