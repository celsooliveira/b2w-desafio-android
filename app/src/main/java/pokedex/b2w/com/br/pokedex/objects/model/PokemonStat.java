package pokedex.b2w.com.br.pokedex.objects.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author celso
 */
public class PokemonStat implements Serializable {

    @SerializedName("base_stat")
    private Integer baseStat;

    @SerializedName("effort")
    private Integer effort;

    public Integer getBaseStat() {
        return baseStat;
    }

    public void setBaseStat(Integer baseStat) {
        this.baseStat = baseStat;
    }

    public Integer getEffort() {
        return effort;
    }

    public void setEffort(Integer effort) {
        this.effort = effort;
    }

    public Stat getStat() {
        return stat;
    }

    public void setStat(Stat stat) {
        this.stat = stat;
    }

    Stat stat;

    public PokemonStat(Integer baseStat, Integer effort, Stat stat) {
        this.baseStat = baseStat;
        this.effort = effort;
        this.stat = stat;
    }

    public class Stat implements Serializable {
        @SerializedName("name")
        private String name;

        @SerializedName("url")
        private String url;

        public Stat(String name, String url) {
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
    }
}