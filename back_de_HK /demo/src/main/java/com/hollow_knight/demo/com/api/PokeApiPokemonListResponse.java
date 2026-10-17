package com.hollow_knight.demo.com.api;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class PokeApiPokemonListResponse {

    private Integer count;
    private String next;
    private String previous;
    private List<PokeApiPokemonResponse> results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public String getPrevious() {
        return previous;
    }

    public void setPrevious(String previous) {
        this.previous = previous;
    }

    public List<PokeApiPokemonResponse> getResults() {
        return results;
    }

    public void setResults(List<PokeApiPokemonResponse> results) {
        this.results = results;
    }
}
