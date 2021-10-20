package com.team5.PokemonTrading.models;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name="transactions")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Integer id;

    @Column(name = "type")
    private Integer type;

    @ManyToOne
    @JoinColumn (name = "buyer")
    private User buyer;

    @ManyToOne
    @JoinColumn (name = "seller")
    private User seller;

    @Column(name = "complete_date",columnDefinition = "DATE")
    private LocalDate completeDate;

    @Column(name = "price")
    private Float price;

    @ManyToOne
    @JoinColumn(name = "trade_for")
    private Pokemon tradeFor;

    @ManyToOne
    @JoinColumn(name = "pokeid")
    private Pokemon pokeId;

    @Column(name = "description")
    private String description;

    @Column(name = "status")
    private Integer status;

    public Transaction() {
    }

    public Transaction(Integer type, User buyer, User seller, LocalDate completeDate, Float price, Pokemon tradeFor, Pokemon pokeId, String description, Integer status) {
        this.type = type;
        this.buyer = buyer;
        this.seller = seller;
        this.completeDate = completeDate;
        this.price = price;
        this.tradeFor = tradeFor;
        this.pokeId = pokeId;
        this.description = description;
        this.status = status;
    }

    public Transaction(Integer id, Integer type, User buyer, User seller, LocalDate completeDate, Float price, Pokemon tradeFor, Pokemon pokeId, String description, Integer status) {
        this.id = id;
        this.type = type;
        this.buyer = buyer;
        this.seller = seller;
        this.completeDate = completeDate;
        this.price = price;
        this.tradeFor = tradeFor;
        this.pokeId = pokeId;
        this.description = description;
        this.status = status;
    }

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

    public User getBuyer() {
        return buyer;
    }

    public void setBuyer(User buyer) {
        this.buyer = buyer;
    }

    public User getSeller() {
        return seller;
    }

    public void setSeller(User seller) {
        this.seller = seller;
    }

    public LocalDate getCompleteDate() {
        return completeDate;
    }

    public void setCompleteDate(LocalDate completeDate) {
        this.completeDate = completeDate;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Pokemon getTradeFor() {
        return tradeFor;
    }

    public void setTradeFor(Pokemon tradeFor) {
        this.tradeFor = tradeFor;
    }

    public Pokemon getPokeId() {
        return pokeId;
    }

    public void setPokeId(Pokemon pokeId) {
        this.pokeId = pokeId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type=" + type +
                ", buyer=" + buyer +
                ", seller=" + seller +
                ", completeDate=" + completeDate +
                ", price=" + price +
                ", tradeFor=" + tradeFor +
                ", pokeId=" + pokeId +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
