package pokedex.b2w.com.br.pokedex.objects.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author celso
 */
public class Pokemon implements Serializable {

    @SerializedName("name")
    private String name;

    @SerializedName("height")
    private String height;

    @SerializedName("id")
    private Integer id;

    @SerializedName("weight")
    private String weight;

    @SerializedName("sprites")
    private Sprite sprite;

    @SerializedName("types")
    private List<PokemonType> pokemonTypes = new ArrayList<>();

    @SerializedName("stats")
    private List<PokemonStat> pokemonStats = new ArrayList<>();

    @SerializedName("abilities")
    private List<PokemonAbility> pokemonHabilities = new ArrayList<>();

    public Pokemon() {

    }

    public Pokemon(String name, String height, Integer id, String weight, Sprite sprite, List<PokemonType> pokemonTypes, List<PokemonStat> pokemonStats, List<PokemonAbility> pokemonHabilities) {
        this.name = name;
        this.height = height;
        this.id = id;
        this.weight = weight;
        this.sprite = sprite;
        this.pokemonTypes = pokemonTypes;
        this.pokemonStats = pokemonStats;
        this.pokemonHabilities = pokemonHabilities;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public Sprite getSprite() {
        return sprite;
    }

    public void setSprite(Sprite sprite) {
        this.sprite = sprite;
    }

    public List<PokemonType> getPokemonTypes() {
        return pokemonTypes;
    }

    public void setPokemonTypes(List<PokemonType> pokemonTypes) {
        this.pokemonTypes = pokemonTypes;
    }

    public List<PokemonStat> getPokemonStats() {
        return pokemonStats;
    }

    public void setPokemonStats(List<PokemonStat> pokemonStats) {
        this.pokemonStats = pokemonStats;
    }

    public List<PokemonAbility> getPokemonHabilities() {
        return pokemonHabilities;
    }

    public void setPokemonHabilities(List<PokemonAbility> pokemonHabilities) {
        this.pokemonHabilities = pokemonHabilities;
    }

    public String pokemonTypesToString() {
        String types = "";
        for (int i = 0; i < pokemonTypes.size(); i++) {
            if (i > 0)
                types += ", ";
            types += pokemonTypes.get(i).type.getName();
        }

        return types;
    }
}
