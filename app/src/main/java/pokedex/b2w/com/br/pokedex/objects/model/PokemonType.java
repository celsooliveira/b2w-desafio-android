package pokedex.b2w.com.br.pokedex.objects.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author celso
 */
public class PokemonType implements Serializable {
    Type type;

    public PokemonType(Type type) {
        this.type = type;
    }

    public class Type implements Serializable {

        @SerializedName("name")
        private String name;

        public Type(String name) {
            this.name = name;
        }

        public String getName() {

            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

    }
}