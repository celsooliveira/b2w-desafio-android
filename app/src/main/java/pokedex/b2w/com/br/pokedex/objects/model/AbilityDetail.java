package pokedex.b2w.com.br.pokedex.objects.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author celso
 */
public class AbilityDetail {

    @SerializedName("effect_entries")
    List<DescriptionAbility> descriptionAbilities;

    public List<DescriptionAbility> getDescriptionAbilities() {
        return descriptionAbilities;
    }

    public void setDescriptionAbilities(List<DescriptionAbility> descriptionAbilities) {
        this.descriptionAbilities = descriptionAbilities;
    }

    public class DescriptionAbility {

        @SerializedName("effect")
        private String effect;

        public void setEffect(String effect) {
            this.effect = effect;
        }

        public String getEffect() {

            return effect;
        }
    }
}