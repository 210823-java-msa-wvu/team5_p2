import { Pokemon } from "../pokemon/pokemon";

export interface Transaction{
    id: number;
    type: number;
    buyer: number;
    seller: number;
    complete_date: string;
    price: number;
    trader_for: number;
    pokeid: Pokemon;
    description: string;
    status: number;
}