package pokedex.b2w.com.br.pokedex.objects.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.synnapps.carouselview.CarouselView;
import com.synnapps.carouselview.ImageListener;

import java.util.ArrayList;
import java.util.List;

import pokedex.b2w.com.br.pokedex.R;
import pokedex.b2w.com.br.pokedex.objects.activity.dialog.Dialog;
import pokedex.b2w.com.br.pokedex.objects.adapter.AdapterAbility;
import pokedex.b2w.com.br.pokedex.objects.api.ApiClientConfig;
import pokedex.b2w.com.br.pokedex.objects.api.IApi;
import pokedex.b2w.com.br.pokedex.objects.model.AbilityDetail;
import pokedex.b2w.com.br.pokedex.objects.model.Pokemon;
import pokedex.b2w.com.br.pokedex.objects.model.PokemonStat;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * @author celso
 */
public class PokemonActivity extends AppCompatActivity {

    private CarouselView carrCarouselView;
    private TextView texName;
    private TextView textId;
    private TextView textType;
    private List<String> urlImage = new ArrayList<>();
    private TextView textHp;
    private TextView textAttack;
    private TextView textDeffense;
    private TextView textSpecialAttack;
    private TextView textSpecialDeffense;
    private TextView textSpeed;
    private AdapterAbility adapterAbility;
    private RecyclerView recyclerAbilities;
    private ProgressBar progressBar;
    private final String TAG = "API POKEMON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokemon);

        Intent intent = getIntent();
        final Pokemon pokemon = (Pokemon) intent.getSerializableExtra("pokemon");

        this.doInitializeAtributtes();
        texName.setText(pokemon.getName());
        textId.setText(pokemon.getId().toString());
        textType.setText(pokemon.pokemonTypesToString());
        urlImage.add(pokemon.getSprite().getFrontDefault());
        urlImage.add(pokemon.getSprite().getBackDefault());
        urlImage.add(pokemon.getSprite().getFrontShiny());

        this.doStatsLoad(pokemon);

        ImageListener imageListener = new ImageListener() {
            @Override
            public void setImageForPosition(int position, ImageView imageView) {

                Picasso.with(getApplicationContext()).load(urlImage.get(position)).into(imageView);

            }
        };
        carrCarouselView.setPageCount(urlImage.size());
        carrCarouselView.setImageListener(imageListener);

        recyclerAbilities = findViewById(R.id.reciclrerViewAbilities);
        adapterAbility = new AdapterAbility(pokemon.getPokemonHabilities(), "");

        final RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerAbilities.setLayoutManager(layoutManager);
        recyclerAbilities.setItemAnimator(new DefaultItemAnimator());
        recyclerAbilities.setHasFixedSize(true);
        recyclerAbilities.addItemDecoration(new DividerItemDecoration(getApplicationContext(), 1));
        recyclerAbilities.setAdapter(adapterAbility);

        recyclerAbilities.addOnItemTouchListener(
                new ItemClickListener(
                        getApplicationContext(),
                        recyclerAbilities,
                        new ItemClickListener.OnItemClickListener() {
                            @Override
                            public void onItemClick(View view, int position) {
                                int id = pokemon.getPokemonHabilities().get(position).getAbility().getId();
                                try {
                                    doLoadAbility(id);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }

                            }

                            @Override
                            public void onLongItemClick(View view, int position) {

                            }

                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                            }
                        }
                )
        );


    }

    private void doInitializeAtributtes() {
        carrCarouselView = findViewById(R.id.carouselView);
        texName = findViewById(R.id.textName);
        textId = findViewById(R.id.textId);
        textType = findViewById(R.id.textDetailType);
        textHp = findViewById(R.id.textHp);
        textAttack = findViewById(R.id.textAttack);
        textDeffense = findViewById(R.id.textDeffense);
        textSpecialAttack = findViewById(R.id.textSpecialAttack);
        textSpecialDeffense = findViewById(R.id.textSpecialDeffense);
        textSpeed = findViewById(R.id.textSpeed);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    private void doStatsLoad(Pokemon pokemon) {
        for (PokemonStat stats : pokemon.getPokemonStats()) {

            if (stats.getStat().getName().contentEquals("hp")) {
                textHp.setText(stats.getBaseStat().toString());
                //Toast.makeText(getApplicationContext(), stats.getStat().getName(), Toast.LENGTH_LONG).show();
            } else if (stats.getStat().getName().contentEquals("attack")) {
                textAttack.setText(stats.getBaseStat().toString());

            } else if (stats.getStat().getName().contentEquals("deffense")) {
                textDeffense.setText(stats.getBaseStat().toString());

            } else if (stats.getStat().getName().contentEquals("special-attack")) {
                textSpecialAttack.setText(stats.getBaseStat().toString());

            } else if (stats.getStat().getName().contentEquals("special-defense")) {
                textSpecialDeffense.setText(stats.getBaseStat().toString());

            } else if (stats.getStat().getName().contentEquals("speed")) {
                textSpeed.setText(stats.getBaseStat().toString());

            }

        }
    }


    public void doLoadAbility(int id) throws Exception {
        progressBar.setVisibility(View.VISIBLE);
        IApi apiService = ApiClientConfig.getClient().create(IApi.class);
        Call<AbilityDetail> call = apiService.getAbilityDetail(id);

        call.enqueue(new Callback<AbilityDetail>() {

            @Override
            public void onResponse(Call<AbilityDetail> call, Response<AbilityDetail> response) {
                if (response.isSuccessful()) {
                    AbilityDetail abilityDetail = response.body();

                    Dialog dialog = new Dialog();
                    dialog.setTitle("Ability Description");
                    dialog.setMessage(abilityDetail.getDescriptionAbilities().get(0).getEffect());
                    dialog.show(getSupportFragmentManager(),"Ability Description");
                    progressBar.setVisibility(View.INVISIBLE);

                } else {
                    new Exception();
                    progressBar.setVisibility(View.INVISIBLE);
                }

            }

            @Override
            public void onFailure(Call<AbilityDetail> call, Throwable t) {
                Log.e(TAG, t.getMessage());
                t.printStackTrace();
                progressBar.setVisibility(View.INVISIBLE);
            }


        });
    }


}
