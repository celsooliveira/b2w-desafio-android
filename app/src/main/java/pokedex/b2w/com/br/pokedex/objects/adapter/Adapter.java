package pokedex.b2w.com.br.pokedex.objects.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import pokedex.b2w.com.br.pokedex.R;
import pokedex.b2w.com.br.pokedex.objects.model.Pokemon;

/**
 * @author celso
 */
public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private List<Pokemon> pokemons;

    public Adapter(List<Pokemon> pokemons) {
        this.pokemons = pokemons;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView name;
        public TextView type;
        public ImageView imagePokemon;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textNameTitle);
            type = itemView.findViewById(R.id.texType);
            imagePokemon = itemView.findViewById(R.id.imagePokemon);
        }
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemList = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_list, parent, false);

        return new MyViewHolder(itemList);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Pokemon pokemon = pokemons.get(position);
        holder.name.setText(pokemon.getName());
        holder.type.setText(pokemon.pokemonTypesToString());

        Picasso.with(holder.imagePokemon.getContext())
                .load(pokemon.getSprite().getFrontDefault())
                .resize(70, 70)
                .into(holder.imagePokemon);
    }

    @Override
    public int getItemCount() {
        return pokemons.size();
    }


    public void insertList(Pokemon pokemon) {
        pokemons.add(pokemon);
        notifyDataSetChanged();
    }

    public void updateList(List<Pokemon> newPokemons) {
        pokemons = new ArrayList<>();
        pokemons.addAll(newPokemons);
        notifyDataSetChanged();

    }
}
