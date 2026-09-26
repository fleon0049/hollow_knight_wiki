@RestController("/api")
public class PokemonApiController {
    @GetMapping("/pokemons")
    Public ResponseEntity<List<PokemonDto>> getAllPokemons() {
        List<Pokemon> pokemons = pokemonService.getAllPokemons();
        List<PokemonDto> pokemonDtos = pokemons.stream()
                .map(pokemonMapper::toDto)
                .collect(Collectors.toList());
        Return ResponseEntity.ok(pokemonDtos);
    }

    @GetMapping("/pokemons/{id}")
    Public ResponseEntity<PokemonDto> getPokemonById(@PathVariable Long id) {
        Pokemon pokemon = pokemonService.getPokemonById(id);
        If (pokemon == null) {
            Return ResponseEntity.notFound().build();
        }
        PokemonDto pokemonDto = pokemonMapper.toDto(pokemon);
        Return ResponseEntity.ok(pokemonDto);

    }
    
}
