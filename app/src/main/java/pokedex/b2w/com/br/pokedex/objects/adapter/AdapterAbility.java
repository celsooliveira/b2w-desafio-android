package pokedex.b2w.com.br.pokedex.objects.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pokedex.b2w.com.br.pokedex.R;
import pokedex.b2w.com.br.pokedex.objects.activity.PokemonActivity;
import pokedex.b2w.com.br.pokedex.objects.model.Pokemon;
import pokedex.b2w.com.br.pokedex.objects.model.PokemonAbility;

/**
 * @author celso
 */
public class AdapterAbility extends RecyclerView.Adapter<AdapterAbility.MyViewHolder> {

    private List<PokemonAbility> pokemonHabilities;
    private String descriptionAbility;

    public AdapterAbility(List<PokemonAbility> pokemonHabilities, String descriptionAbility) {
        this.pokemonHabilities = pokemonHabilities;
        this.descriptionAbility = descriptionAbility;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView abilityName;

        public MyViewHolder(View itemView) {
            super(itemView);
            abilityName = itemView.findViewById(R.id.textAbility);

        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_abilities, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.abilityName.setText(pokemonHabilities.get(position).getAbility().getName());

    }

    @Override
    public int getItemCount() {
        return pokemonHabilities.size();
    }

}
