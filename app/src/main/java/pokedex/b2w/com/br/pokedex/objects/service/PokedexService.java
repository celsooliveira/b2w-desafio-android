package pokedex.b2w.com.br.pokedex.objects.service;

import android.util.Log;

import java.util.List;

import pokedex.b2w.com.br.pokedex.objects.adapter.Adapter;
import pokedex.b2w.com.br.pokedex.objects.api.ApiClientConfig;
import pokedex.b2w.com.br.pokedex.objects.api.IApi;
import pokedex.b2w.com.br.pokedex.objects.model.Pokedex;
import pokedex.b2w.com.br.pokedex.objects.model.PokedexResponse;
import pokedex.b2w.com.br.pokedex.objects.model.Pokemon;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author celso
 */
public class PokedexService {

    public List<Pokemon> pokemons;
    public Adapter adapter;
    public boolean isLoaded;
    private static final String TAG = "API POKEDEX";

    public PokedexService(List<Pokemon> pokemons, Adapter adapter, boolean isLoaded) {
        this.pokemons = pokemons;
        this.adapter = adapter;
        this.isLoaded = isLoaded;
    }

    public void doLoadPoquedex(int offset) throws Exception {
        IApi apiService = ApiClientConfig.getClient().create(IApi.class);
        Call<PokedexResponse> call = apiService.getPokedex(20, offset);

        call.enqueue(new Callback<PokedexResponse>() {

            @Override
            public void onResponse(Call<PokedexResponse> call, Response<PokedexResponse> response) {
                if (response.isSuccessful()) {
                    PokedexResponse pokedexResponse = response.body();

                    List<Pokedex> podexList = pokedexResponse.getPokedexList();
                    for (int i = 1; i < podexList.size(); i++) {
                        Pokedex pokedex = podexList.get(i);
                        try {
                            doLoadById(pokedex.getPokemonId());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }
            }

            @Override
            public void onFailure(Call<PokedexResponse> call, Throwable t) {
                isLoaded = true;
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
            }

        });

    }

    public void doLoadById(int id) throws Exception {
        IApi apiService = ApiClientConfig.getClient().create(IApi.class);

        Call<Pokemon> call = apiService.getPokemonById(id);

        call.enqueue(new Callback<Pokemon>() {

            @Override
            public void onResponse(Call<Pokemon> call, Response<Pokemon> response) {
                if (response.isSuccessful()) {
                    Pokemon pokemon = response.body();

                    adapter.insertList(pokemon);
                } else {
                     new Exception();
                }

            }

            @Override
            public void onFailure(Call<Pokemon> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
            }
        });

    }
}
