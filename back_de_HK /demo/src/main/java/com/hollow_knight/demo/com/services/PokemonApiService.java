

@Service
public class PokemonApiService {
    private final PokeApiClient pokeApiClient;

    @Autowired
    public PokemonApiService(PokeApiClient pokeApiClient) {
        this.pokeApiClient = pokeApiClient;
    }

    public List<PokeApiPokemonResponse> getAllPokemons() {
        return pokeApiClient.getAllPokemons();
    }
}