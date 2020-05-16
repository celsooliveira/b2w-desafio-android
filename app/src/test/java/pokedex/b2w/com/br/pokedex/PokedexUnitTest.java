package pokedex.b2w.com.br.pokedex;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import pokedex.b2w.com.br.pokedex.objects.adapter.Adapter;
import pokedex.b2w.com.br.pokedex.objects.model.Pokemon;
import pokedex.b2w.com.br.pokedex.objects.service.PokedexService;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class PokedexUnitTest {

    public List<Pokemon> pokemons = new ArrayList<>();
    public Adapter adapter;
    public boolean isLoaded;
    @Test
    public void addition_isCorrect() {
        adapter = new Adapter(pokemons);
        PokedexService pokedexService = new PokedexService(pokemons, adapter, isLoaded);
        try {
            pokedexService.doLoadPoquedex(0);
            assert(pokedexService.pokemons.size() < 19);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}