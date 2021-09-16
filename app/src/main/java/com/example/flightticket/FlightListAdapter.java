package com.example.flightticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.flightticket.DataClasses.Flight;
import com.example.flightticket.DataClasses.User;

import java.util.List;

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.MyViewHolder> {

    private Context context;
    private List<Flight> flightList;

    public FlightListAdapter(Context context) {
        this.context = context;
    }

    public void setFlightList(List<Flight> flightList) {
        this.flightList = flightList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FlightListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycler_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FlightListAdapter.MyViewHolder holder, int position) {
        holder.flightId.setText(String.valueOf(this.flightList.get(position).getId()));
        holder.minPrice.setText(String.valueOf(this.flightList.get(position).getMinPrice()));
        holder.flightCarrier.setText(this.flightList.get(position).getCarrier());
        holder.placeDep.setText(String.valueOf(this.flightList.get(position).getPlaceDep().getAirPortName()));
        holder.placeDist.setText(String.valueOf(this.flightList.get(position).getPlaceDist().getAirPortName()));
        holder.currency.setText(this.flightList.get(position).getCurrency());
    }

    @Override
    public int getItemCount() {
        return this.flightList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView flightId;
        TextView minPrice;
        TextView flightCarrier;
        TextView placeDep;
        TextView placeDist;
        TextView currency;

        public MyViewHolder(View view) {
            super(view);
            flightId = view.findViewById(R.id.flightId);
            minPrice = view.findViewById(R.id.minPrice);
            flightCarrier = view.findViewById(R.id.flightCarrier);
            placeDep = view.findViewById(R.id.placeDep);
            placeDist = view.findViewById(R.id.placeDist);
            currency = view.findViewById(R.id.currency);
        }
    }
}