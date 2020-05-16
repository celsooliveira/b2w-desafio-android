package pokedex.b2w.com.br.pokedex.objects.api;

import pokedex.b2w.com.br.pokedex.objects.model.AbilityDetail;
import pokedex.b2w.com.br.pokedex.objects.model.PokedexResponse;
import pokedex.b2w.com.br.pokedex.objects.model.Pokemon;
import pokedex.b2w.com.br.pokedex.objects.model.PokemonAbility;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * @author celso
 */
public interface IApi {

    @GET("api/v2/pokemon")
    Call<PokedexResponse> getPokedex(@Query("limit") int limit, @Query("offset") int ofsset);

    @GET("api/v2/pokemon/{id}")
    Call<Pokemon> getPokemonById(@Path("id") int id);

    @GET("api/v2/ability/{id}")
    Call<AbilityDetail> getAbilityDetail(@Path("id") int id);
    //http://pokeapi.co/api/v2/ability/34




}
