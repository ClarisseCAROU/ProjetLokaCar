package fr.eni.projetlokacar.adapters;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.bo.Vehicule;

public class VehiculeAdapter extends RecyclerView.Adapter<VehiculeAdapter.ViewHolder> {

    List<Vehicule> vehicules;
    ItemClickListener listener;

    public VehiculeAdapter(ItemClickListener listener) {
        this.vehicules = new ArrayList<>();
        this.listener = listener;
    }

    public void addVehicules(List<Vehicule> vehicules){
        this.vehicules.addAll(vehicules);
        Log.i("DEV", vehicules.toString());
        notifyDataSetChanged();
    }

    @Override
    public VehiculeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.recycler_row, parent, false);

        return new VehiculeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Vehicule vehicule = vehicules.get(position);
        holder.tvVehicule.setText(vehicule.getModele() + " (" + vehicule.getImmatriculation() + ")");

    }

    @Override
    public int getItemCount() {
        return vehicules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvVehicule;

        public ViewHolder(View itemView) {
            super(itemView);

            tvVehicule = itemView.findViewById(R.id.tv_vehicule);
            itemView.setOnClickListener( e -> listener.onItemClick(vehicules.get(getAdapterPosition())));

        }
    }

    public interface ItemClickListener {
        void onItemClick(Vehicule vehicule);
    }
}
