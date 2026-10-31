package com.hollow_knight.demo.com.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.hollow_knight.demo.com.api.PokeApiClient;
import com.hollow_knight.demo.com.api.PokeApiPokemonResponse;

@Service
public class PokemonApiService {
    private final PokeApiClient pokeApiClient;

    public PokemonApiService(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    public List<PokeApiPokemonResponse> getAllPokemons() {
        return pokeApiClient.getAllPokemons();
    }
}