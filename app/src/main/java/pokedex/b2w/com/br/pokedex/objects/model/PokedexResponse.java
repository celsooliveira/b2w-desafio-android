package pokedex.b2w.com.br.pokedex.objects.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author celso
 */
public class PokedexResponse {

    @SerializedName("results")
    private List<Pokedex> pokedexList;

    public List<Pokedex> getPokedexList() {
        return pokedexList;
    }

    public void setPokedexList(List<Pokedex> pokedexList) {
        this.pokedexList = pokedexList;
    }
}
