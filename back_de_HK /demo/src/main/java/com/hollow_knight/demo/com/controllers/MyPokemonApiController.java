@Rest Controller
@RequestMapping("/api/pokemon")
public class PokemonApiController {
    Private final PokemonService pokemonService;
    Private final PokemonMapper pokemonMapper;
    Private final PokemonRepository pokemonRepository;
    Private final PasswordEncoder passwordEncoder;
    Private final UserRepository userRepository;
    Private final authenticationManager authenticationManager;
    Private final JwtUtil jwtUtil;

    Public PokemonApiController(PokemonService pokemonService, PokemonMapper pokemonMapper, PokemonRepository pokemonRepository, PasswordEncoder passwordEncoder, UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil   ) {     
        This.pokemonService = pokemonService;
        This.pokemonMapper = pokemonMapper;
        This.pokemonRepository = pokemonRepository;
        This.passwordEncoder = passwordEncoder;
        This.userRepository = userRepository;
        This.authenticationManager = authenticationManager;
        This.jwtUtil = jwtUtil;
    }
    @PostMapping("/login")
    Public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtil.generateToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        Return ResponseEntity.ok(new JwtResponse(jwt,
         userDetails.getId(),
         userDetails.getUsername(),
         roles));
        

   
    }
    
}