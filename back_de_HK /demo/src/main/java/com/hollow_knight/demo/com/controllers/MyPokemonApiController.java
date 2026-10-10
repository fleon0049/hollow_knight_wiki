import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;  


@RestController
@RequestMapping("/api/pokemon")
public class PokemonApiController {
    private final PokemonService pokemonService;
    private final PokemonMapper pokemonMapper;
    private final PokemonRepository pokemonRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;

    public PokemonApiController(PokemonService pokemonService, PokemonMapper pokemonMapper, PokemonRepository pokemonRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil   ) {     
        This.pokemonService = pokemonService;
        This.pokemonMapper = pokemonMapper;
        This.pokemonRepository = pokemonRepository;
        This.passwordEncoder = passwordEncoder;
        This.userRepository = userRepository;
        This.authenticationManager = authenticationManager;
        This.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
         userDetails.getId(),
         userDetails.getUsername(),
         roles));
        

   
    }
    
}