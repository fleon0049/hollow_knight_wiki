package com.hollow_knight.demo.com.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hollow_knight.demo.com.api.PokeApiPokemonResponse;
import com.hollow_knight.demo.com.services.PokemonApiService;

@RestController
@RequestMapping("/api")
public class PokemonApiController {

    private final PokemonApiService pokemonApiService;

    public PokemonApiController(PokemonApiService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }

    @GetMapping("/pokemon")
    public ResponseEntity<List<PokeApiPokemonResponse>> getAllPokemons() {
        return ResponseEntity.ok(pokemonApiService.getAllPokemons());
    }
}