package pokedex.b2w.com.br.pokedex.objects.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author celso
 */
public class Pokedex {

    @SerializedName("name")
    private String pokemonName;

    private int pokemonId;

    @SerializedName("url")
    private String pokemonUrl;


    public String getPokemonName() {
        return pokemonName;
    }

    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    public int getPokemonId() {
        String[] urlSplit = pokemonUrl.split("/");
        return Integer.parseInt(urlSplit[urlSplit.length - 1]);
    }

    public void setPokemonId(int pokemonId) {
        this.pokemonId = pokemonId;
    }

    public String getPokemonUrl() {
        return pokemonUrl;
    }

    public void setPokemonUrl(String pokemonUrl) {
        this.pokemonUrl = pokemonUrl;
    }
}
