import com.hollow_knight.demo.com.client.PokeApiClient;
import com.hollow_knight.demo.com.model.PokeApiPokemonResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;  

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