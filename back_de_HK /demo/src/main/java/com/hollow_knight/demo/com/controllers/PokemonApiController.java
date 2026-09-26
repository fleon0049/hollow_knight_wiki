@RestController("/api")
public class PokemonApiController {

    private final PokemonApiService pokemonApiService;

    @Autowired
    public PokemonApiController(PokemonApiService pokemonApiService) {
        this.pokemonApiService = pokemonApiService;
    }

    @GetMapping("/pokemons")
    Public ResponseEntity<List<PokeApiPokemonResponse>> getAllPokemons() {
        List<PokeApiPokemonResponse> pokemons = pokemonApiService.getAllPokemons();
        Return ResponseEntity.ok(pokemons);
    }

    // @GetMapping("/pokemons/{id}")
    // Public ResponseEntity<PokemonDto> getPokemonById(@PathVariable Long id) {
    //     Pokemon pokemon = pokemonService.getPokemonById(id);
    //     If (pokemon == null) {
    //         Return ResponseEntity.notFound().build();
    //     }
    //     PokemonDto pokemonDto = pokemonMapper.toDto(pokemon);
    //     Return ResponseEntity.ok(pokemonDto);

    // }

    // @PostMapping("/pokemons")
    // Public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto) {
    //     Pokemon pokemon = pokemonMapper.toEntity(pokemonDto);
    //     Pokemon createdPokemon = pokemonService.createPokemon(pokemon);
    //     PokemonDto createdPokemonDto = pokemonMapper.toDto(createdPokemon);
    //     Return ResponseEntity.status(HttpStatus.CREATED).body(createdPokemonDto);
    // }

    // @PutMapping("/pokemons/{id}")
    // Public ResponseEntity<PokemonDto> updatePokemon(@PathVariable Long id, @RequestBody PokemonDto pokemonDto) {
    //     Pokemon existingPokemon = pokemonService.getPokemonById(id);
    //     If (existingPokemon == null) {
    //         Return ResponseEntity.notFound().build();
    //     }
    //     Pokemon updatedPokemon = pokemonMapper.toEntity(pokemonDto);
    //     updatedPokemon.setId(id);
    //     Pokemon savedPokemon = pokemonService.updatePokemon(updatedPokemon);
    //     PokemonDto savedPokemonDto = pokemonMapper.toDto(savedPokemon);
    //     Return ResponseEntity.ok(savedPokemonDto);
    // }
}