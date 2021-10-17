package com.team5.PokemonTrading.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="deals")
public class Deal implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="type")
    private Integer type;
    @Column(name="price")
    private Float price;
    @ManyToOne
    @JoinColumn(name="id",insertable = false)
    private User seller;
    @Column(name="expire_date",columnDefinition = "DATE")
    private LocalDate expireDate;
    @ManyToOne
    @JoinColumn(name="id",insertable = false)
    private Pokemon tradeFor;
    @Column
    private String description;
    @ManyToOne
    @JoinColumn(name="id",insertable = false)
    private Pokemon pokeId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        this.expireDate = expireDate;
    }

    public Pokemon getTradeFor() {
        return tradeFor;
    }

    public void setTradeFor(Pokemon tradeFor) {
        this.tradeFor = tradeFor;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pokemon getPokeId() {
        return pokeId;
    }

    public void setPokeId(Pokemon pokeId) {
        this.pokeId = pokeId;
    }

    @Override
    public String toString() {
        return "Deal{" +
                "id=" + id +
                ", type=" + type +
                ", price=" + price +
                ", seller=" + seller +
                ", expireDate=" + expireDate +
                ", tradeFor=" + tradeFor +
                ", description='" + description + '\'' +
                ", pokeId=" + pokeId +
                '}';
    }
}