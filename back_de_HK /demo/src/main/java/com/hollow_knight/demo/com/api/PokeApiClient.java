package com.example.pokemon.client;

import com.example.pokemon.dto.PokeApiPokemonResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Component
public class PokeApiClient {

    private final RestTemplate restTemplate;
    private final String baseUrl;

    public PokeApiClient(RestTemplate restTemplate,
                          @Value("${pokeapi.base-url:https://pokeapi.co/api/v2}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    /**
     * Trae un Pokemon de la PokeAPI por nombre (en minuscula, ej "ditto") o por id.
     * Devuelve null si no existe (404).
     */
    public PokeApiPokemonResponse buscarPokemon(String nombreOId) {
        String url = baseUrl + "/pokemon/" + nombreOId.toLowerCase();
        try {
            return restTemplate.getForObject(url, PokeApiPokemonResponse.class);
        } catch (HttpClientErrorException.NotFound ex) {
            return null;
        } catch (RestClientException ex) {
            // Timeout, DNS, 5xx, JSON invalido, etc.
            throw new PokeApiException("Error al consultar la PokeAPI para '" + nombreOId + "'", ex);
        }
    }

    public static class PokeApiException extends RuntimeException {
        public PokeApiException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}