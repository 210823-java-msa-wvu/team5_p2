import { Pokemon } from "../pokemon/pokemon";
import { User } from "../main-menu/main-menu";

export interface Wishlist {
    id: number;
    userid: User;
    pokeid: Pokemon;
}