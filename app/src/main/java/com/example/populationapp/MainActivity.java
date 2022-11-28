package com.example.populationapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;
import com.example.pokemonapp.R;
import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    ListView populationListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populationListView = findViewById(R.id.populationListView1);
        getPopulations();
    }

    private void getPopulations() {
        Call<PopulationPojo> call = RetrofitClient.getInstance().getMyApi().getPopulations();
        call.enqueue(new Callback<PopulationPojo>() {
            @Override
            public void onResponse(Call<PopulationPojo> call, Response<PopulationPojo> response) {
                PopulationPojo populationData = response.body();
                ArrayList<ResultsItem> data = populationData.getResults();
                String[] onePopulation = new String[data.size()];

                for (int i = 0; i < data.size(); i++) {
                    onePopulation[i] = data.get(i).getName();
                }

                populationListView.setAdapter(new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, onePopulation));
            }

            @Override
            public void onFailure(Call<PopulationPojo> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "An error has occured", Toast.LENGTH_LONG).show();
            }

        });
    }

}