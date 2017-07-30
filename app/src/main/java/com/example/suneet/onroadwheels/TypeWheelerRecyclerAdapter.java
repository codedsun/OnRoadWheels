package com.example.suneet.onroadwheels;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ArrayList;

/**
 * Created by suneet on 30/7/17.
 */

public class TypeWheelerRecyclerAdapter extends RecyclerView.Adapter<TypeWheelerRecyclerAdapter.ViewHolder> {
    private ArrayList<Vehicle> vehicleArrayList;
    Context c;

    public TypeWheelerRecyclerAdapter(ArrayList<Vehicle> vehicle, Context c) {
        this.vehicleArrayList = vehicle;
        this.c = c;
    }

    @Override
    public TypeWheelerRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(c).inflate(R.layout.type_wheeler_recycler_view,parent,false));
    }

    @Override
    public void onBindViewHolder(TypeWheelerRecyclerAdapter.ViewHolder holder, int position) {
        Vehicle vehicle=vehicleArrayList.get(position);
        holder.typeWheelerVehicleNo.setText(vehicle.getVehicleNo());
        if(vehicle.getVechileType()=="4")
        {
            holder.typeWheelerVehicleType.setImageResource(R.drawable._car);
        }
        else
        {
            holder.typeWheelerVehicleType.setImageResource(R.drawable.motor);
        }

    }

    @Override
    public int getItemCount() {
        return vehicleArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private EditText typeWheelerVehicleNo;
        private ImageView typeWheelerVehicleType;
        public ViewHolder(View itemView) {
            super(itemView);
            typeWheelerVehicleNo= (EditText) itemView.findViewById(R.id.typeWheelerVehicleNO);
            typeWheelerVehicleType= (ImageView) itemView.findViewById(R.id.typeWheelerVehicleType);

        }
    }
}
