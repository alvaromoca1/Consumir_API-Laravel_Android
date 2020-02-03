package com.example.usuario.animalesapi.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.usuario.animalesapi.Models.AnimalesModelo;
import com.example.usuario.animalesapi.R;

import java.util.ArrayList;

public class AnimalesAdaptador extends RecyclerView.Adapter<AnimalesAdaptador.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<AnimalesModelo> dataModelArrayList;

    public AnimalesAdaptador(Context ctx, ArrayList<AnimalesModelo> dataModelArrayList){

        inflater=LayoutInflater.from(ctx);
        this.dataModelArrayList= dataModelArrayList;
    }

    @NonNull
    @Override
    public AnimalesAdaptador.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = inflater.inflate(R.layout.item_animales,viewGroup,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AnimalesAdaptador.MyViewHolder myViewHolder, int i) {
        myViewHolder.nombre.setText(dataModelArrayList.get(i).getNombre());
        myViewHolder.animal.setText(dataModelArrayList.get(i).getAnimal());
    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView nombre,animal;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            nombre=(TextView) itemView.findViewById(R.id.nombre);
            animal= (TextView)itemView.findViewById(R.id.clase);
        }
    }
}
