import { Pokemon } from "../pokemon/pokemon";

export interface Deal{
    id:number;
    type:string;
    price:number;
    tradeFor:Pokemon;
    seller:User;
    expireDate:string;
    description:string;
    pokeId:Pokemon;
    highestBidder:User|null;
}

export interface User{
    id:number;
    username:string;
    password:string;
    balance:number;
}