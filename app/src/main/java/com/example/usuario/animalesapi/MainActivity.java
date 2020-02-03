package com.example.usuario.animalesapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.usuario.animalesapi.Adapters.AnimalesAdaptador;
import com.example.usuario.animalesapi.Interfaces.AnimalesInterface;
import com.example.usuario.animalesapi.Models.AnimalesModelo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class MainActivity extends AppCompatActivity {
    TextView jsonresultado;
    private AnimalesAdaptador animalesAdaptador;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        jsonresultado=(TextView)findViewById(R.id.jsonresultado);
        recyclerView = findViewById(R.id.recycler);
        fetchJSON();
    }
    private void fetchJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.0.107:8000/api/")
                .addConverterFactory(ScalarsConverterFactory.create())
                .build();
        AnimalesInterface api = retrofit.create(AnimalesInterface.class);

        Call<String> call = api.getString();

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {

                if(response.isSuccessful()){
                    if(response.body() !=null){
                        String jsonresponse = response.body().toString();
                        jsonresultado.setText(jsonresponse);
                        writeRecycler(jsonresponse);
                    }
                }

            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }

    private void writeRecycler(String jsonresponse) {
        try {

            JSONObject obj = new JSONObject(jsonresponse);
            ArrayList<AnimalesModelo> animalesModelosArrayList = new ArrayList<>();
            JSONArray dataArray = obj.getJSONArray("data");

            for (int i =0 ; i< dataArray.length();i++){
                AnimalesModelo animalesModelo = new AnimalesModelo();
                JSONObject dataobj = dataArray.getJSONObject(i);

                animalesModelo.setNombre(dataobj.getString("nombre"));
                animalesModelo.setAnimal(dataobj.getString("animal"));

                animalesModelosArrayList.add (animalesModelo);
            }
            animalesAdaptador = new AnimalesAdaptador(this,animalesModelosArrayList);
            recyclerView.setAdapter(animalesAdaptador);
            recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
