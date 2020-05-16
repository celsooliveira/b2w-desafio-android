package pokedex.b2w.com.br.pokedex.objects.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import pokedex.b2w.com.br.pokedex.R;
import pokedex.b2w.com.br.pokedex.objects.adapter.Adapter;
import pokedex.b2w.com.br.pokedex.objects.model.Pokemon;
import pokedex.b2w.com.br.pokedex.objects.service.PokedexService;

/**
 * @author celso
 *
 */
public class MainActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private RecyclerView recyclerPokemon;
    private Adapter adapter;
    public List<Pokemon> pokemons = new ArrayList<>();
    private int ofsset;
    private boolean isLoaded = false;
    private ProgressBar progressBar;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        progressBar = findViewById(R.id.progressBarMain);

        recyclerPokemon = findViewById(R.id.recyclerPokemons);
        adapter = new Adapter(pokemons);

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerPokemon.setLayoutManager(layoutManager);
        recyclerPokemon.setItemAnimator(new DefaultItemAnimator());
        recyclerPokemon.setHasFixedSize(true);
        recyclerPokemon.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
        recyclerPokemon.setAdapter(adapter);

        recyclerPokemon.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                if (dy > 0) {

                    if (isLoaded) {
                        isLoaded = false;
                        ofsset += 20;
                        doLoadData(ofsset);
                    }
                }
            }
        });

        recyclerPokemon.addOnItemTouchListener(
                new ItemClickListener(
                        getApplicationContext(),
                        recyclerPokemon,
                        new ItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {

                                Intent intent = new Intent(getApplicationContext(), PokemonActivity.class);
                                intent.putExtra("pokemon", pokemons.get(position));
                                startActivity(intent);

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );


        ofsset = 0;
        this.doLoadData(ofsset);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        MenuItem menuItem = menu.findItem(R.id.search_icon);
        SearchView searchView = (SearchView) menuItem.getActionView();
        searchView.setQueryHint("Busque por nome ou id");
        searchView.setOnQueryTextListener(this);

        return true;
    }

    private void doLoadData(int ofsset) {
        progressBar.setVisibility(View.VISIBLE);
        isLoaded = true;
        PokedexService pokedexService = new PokedexService(pokemons, adapter, isLoaded);
        try {
            pokedexService.doLoadPoquedex(ofsset);
            progressBar.setVisibility(View.INVISIBLE);
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "Erro ao carregar dados. Verifique sua conexao com a internet", Toast.LENGTH_LONG);
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String inputSearch = newText;
        List<Pokemon> newPokemons = new ArrayList<>();

        for (Pokemon pokemon : pokemons) {
            if (pokemon.getName().contains(inputSearch) || pokemon.getId().toString().contains(inputSearch)) {
                newPokemons.add(pokemon);
            }
        }

        adapter.updateList(newPokemons);
        return true;
    }
}
