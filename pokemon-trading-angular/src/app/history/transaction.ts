import { Pokemon } from "../pokemon/pokemon";
import { User } from "../main-menu/main-menu";

export interface Transaction{
    id: number;
    type: number;
    buyer: User;
    seller: User;
    completeDate: string;
    price: number;
    trader_for: number;
    pokeName: Pokemon;
    description: string;
    status: number;
}