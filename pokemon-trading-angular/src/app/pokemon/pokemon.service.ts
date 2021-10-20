import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pokemon } from './pokemon';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class PokemonService {
  private apiServerUrl = environment.apiBaseUrl;

  constructor(private http: HttpClient){}

  public getPokemons(): Observable<Pokemon[]> {
    return this.http.get<Pokemon[]>(`${this.apiServerUrl}/pokemon/all`);
  }

  public addPokemon(pokemon: Pokemon): Observable<Pokemon> {
    return this.http.post<Pokemon>(`${this.apiServerUrl}/pokemon/add`, pokemon);
  }

  public updatePokemon(pokemon: Pokemon): Observable<Pokemon> {
    return this.http.put<Pokemon>(`${this.apiServerUrl}/pokemon/update`, pokemon);
  }

  public deletePokemon(pokemonId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/pokemon/delete/${pokemonId}`);
  }
}