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
    return this.http.post<Pokemon>(`${this.apiServerUrl}/pokemon/put/update`, pokemon);
  }

  public deletePokemon(pokemonId: number): Observable<void> {
    return this.http.delete<void>(`${this.apiServerUrl}/pokemon/delete/delete/${pokemonId}`);
  }

  public addWishList(pokemon:Pokemon): Observable<void>{
    const headers = {'content-type':'application/json'};
    let cookie = this.getCookie("userinfo");
    let user = JSON.parse(JSON.parse(cookie));
    let body = `{
                  "userid":{"id":${user.id}},
                  "pokeid":{"id":${pokemon.id}}
                }`
    return this.http.post<any>(`${this.apiServerUrl}/wishlist/add`,body,{'headers':headers});
  }

  private getCookie(name: string): string|null {
    const nameLenPlus = (name.length + 1);
    return document.cookie
      .split(';')
      .map(c => c.trim())
      .filter(cookie => {
        return cookie.substring(0, nameLenPlus) === `${name}=`;
      })
      .map(cookie => {
        return decodeURIComponent(cookie.substring(nameLenPlus));
      })[0] || null;
  }
}