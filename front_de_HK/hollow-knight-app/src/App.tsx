import { useEffect, useMemo, useState } from 'react'
import './App.css'

type Pokemon = {
  id: number
  name: string
  height?: number
  weight?: number
  types?: Array<{ type?: { name?: string } }>
  stats?: Array<{ baseStat?: number; stat?: { name?: string } }>
}

const API_URL = 'http://localhost:8080/api/pokemon'

function App() {
  const [query, setQuery] = useState('')
  const [pokemons, setPokemons] = useState<Pokemon[]>([])
  const [loading, setLoading] = useState(false)
  const [error, setError] = useState('')

  useEffect(() => {
    const fetchPokemons = async () => {
      setLoading(true)
      setError('')

      try {
        const response = await fetch(API_URL)

        if (!response.ok) {
          throw new Error('No se pudo cargar la lista de Pokémon.')
        }

        const data = (await response.json()) as Pokemon[]
        setPokemons(data)
      } catch (err) {
        setError(err instanceof Error ? err.message : 'Error al cargar los Pokémon.')
      } finally {
        setLoading(false)
      }
    }

    void fetchPokemons()
  }, [])

  const filteredPokemons = useMemo(() => {
    const value = query.trim().toLowerCase()

    if (!value) {
      return pokemons
    }

    return pokemons.filter((pokemon) => {
      const name = pokemon.name?.toLowerCase() ?? ''
      const id = String(pokemon.id)
      return name.includes(value) || id.includes(value)
    })
  }, [pokemons, query])

  return (
    <main className="pokemon-app">
      <section className="search-panel">
        <p className="eyebrow">Pokédex</p>
        <h1>Buscar Pokémon</h1>
        <input
          type="text"
          value={query}
          onChange={(event) => setQuery(event.target.value)}
          placeholder="Escribe el nombre o ID del Pokémon"
          aria-label="Buscar Pokémon"
        />
      </section>

      {loading && <p className="status">Cargando Pokémon...</p>}
      {error && <p className="status error">{error}</p>}

      {!loading && !error && filteredPokemons.length === 0 && (
        <p className="status">No se encontraron resultados.</p>
      )}

      <section className="pokemon-grid">
        {filteredPokemons.map((pokemon) => (
          <article key={pokemon.id} className="pokemon-card">
            <div className="pokemon-id">#{pokemon.id}</div>
            <h2>{pokemon.name}</h2>
            <div className="meta">
              <span>Altura: {pokemon.height ?? 0}</span>
              <span>Peso: {pokemon.weight ?? 0}</span>
            </div>
            <div className="types">
              {(pokemon.types ?? []).map((typeSlot, index) => (
                <span key={`${pokemon.id}-${typeSlot.type?.name ?? index}`} className="type-pill">
                  {typeSlot.type?.name ?? 'unknown'}
                </span>
              ))}
            </div>
            <div className="stats">
              {(pokemon.stats ?? []).slice(0, 3).map((stat, index) => (
                <div key={`${pokemon.id}-stat-${index}`} className="stat-row">
                  <span>{stat.stat?.name ?? 'stat'}</span>
                  <strong>{stat.baseStat ?? 0}</strong>
                </div>
              ))}
            </div>
          </article>
        ))}
      </section>
    </main>
  )
}

export default App
