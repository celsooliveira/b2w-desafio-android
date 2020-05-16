package pokedex.b2w.com.br.pokedex.objects.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author celso
 */
public class PokemonAbility implements Serializable {

    private Ability ability;

    public Ability getAbility() {
        return ability;
    }

    public PokemonAbility(Ability ability) {
        this.ability = ability;

    }

    public class Ability implements Serializable {

        @SerializedName("name")
        private String name;

        @SerializedName("url")
        private String url;

        private int id;

        public Ability(String name, String url) {
            this.name = name;
            this.url = url;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public int getId() {
            String[] urlSplit = url.split("/");
            return Integer.parseInt(urlSplit[urlSplit.length - 1]);
        }
    }

}