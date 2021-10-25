import { Pokemon } from "../pokemon/pokemon";

export interface Deal{
    id: number;
    type: number;
    price: number;
    seller: number;
    expire_date: string;
    trader_for: number;
    description: string;
    pokeId: Pokemon;
    highestBidder:User|null;

}