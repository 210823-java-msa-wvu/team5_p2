export interface Transaction{
    id: number;
    type: number;
    buyer: number;
    seller: number;
    complete_date: string;
    price: number;
    trader_for: number;
    pokeid: number;
    description: string;
    status: number;
}