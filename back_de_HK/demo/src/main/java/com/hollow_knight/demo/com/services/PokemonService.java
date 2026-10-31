package com.hollow_knight.demo.com.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.hollow_knight.demo.com.models.Pokemon;
import com.hollow_knight.demo.com.repositories.PokemonRepository;

@Service
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public List<Pokemon> getAllPokemons() {
        return pokemonRepository.findAll();
    }

    public Optional<Pokemon> getPokemonById(Long id) {
        return pokemonRepository.findById(id);
    }

    public Pokemon createPokemon(Pokemon pokemon) {
        return pokemonRepository.save(pokemon);
    }

    public Pokemon updatePokemon(Long id, Pokemon pokemonData) {
        Pokemon pokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pokemon no encontrado"));

        pokemon.setName(pokemonData.getName());
        pokemon.setLevel(pokemonData.getLevel());

        return pokemonRepository.save(pokemon);
    }

    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }
}
