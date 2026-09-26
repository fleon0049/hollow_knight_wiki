package com.example.pokemon.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;

/**
 * Mapea la respuesta de GET https://pokeapi.co/api/v2/pokemon/{nombre|id}
 * Solo se incluyen los campos que se van a usar; @JsonIgnoreProperties(ignoreUnknown = true)
 * hace que Jackson ignore el resto (sprites, moves, abilities, etc.) sin romper el parseo.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class PokeApiPokemonResponse {

    private Long id;
    private String name;
    private Integer height;
    private Integer weight;
    private List<PokeApiTypeSlot> types;
    private List<PokeApiStatSlot> stats;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public List<PokeApiTypeSlot> getTypes() {
        return types;
    }

    public void setTypes(List<PokeApiTypeSlot> types) {
        this.types = types;
    }

    public List<PokeApiStatSlot> getStats() {
        return stats;
    }

    public void setStats(List<PokeApiStatSlot> stats) {
        this.stats = stats;
    }

    // --- Clases anidadas para "types": [{ "slot": 1, "type": { "name": "normal" } }] ---
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PokeApiTypeSlot {
        private Integer slot;
        private PokeApiNamedResource type;

        public Integer getSlot() {
            return slot;
        }

        public void setSlot(Integer slot) {
            this.slot = slot;
        }

        public PokeApiNamedResource getType() {
            return type;
        }

        public void setType(PokeApiNamedResource type) {
            this.type = type;
        }
    }

    // --- Clases anidadas para "stats": [{ "base_stat": 48, "stat": { "name": "hp" } }] ---
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PokeApiStatSlot {
        private Integer baseStat;
        private PokeApiNamedResource stat;

        public Integer getBaseStat() {
            return baseStat;
        }

        // Jackson por defecto espera "baseStat" en camelCase, pero el JSON trae
        // "base_stat" (snake_case). Con este setter anotado, mapea igual.
        @com.fasterxml.jackson.annotation.JsonProperty("base_stat")
        public void setBaseStat(Integer baseStat) {
            this.baseStat = baseStat;
        }

        public PokeApiNamedResource getStat() {
            return stat;
        }

        public void setStat(PokeApiNamedResource stat) {
            this.stat = stat;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class PokeApiNamedResource {
        private String name;
        private String url;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}