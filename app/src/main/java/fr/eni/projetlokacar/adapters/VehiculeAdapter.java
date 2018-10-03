package fr.eni.projetlokacar.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.bo.Vehicule;

public class VehiculeAdapter extends RecyclerView.Adapter<VehiculeAdapter.ViewHolder> {

    private List<Vehicule> vehicules;
    private ItemClickListener listener;
    private View selectedItem;

    public VehiculeAdapter(ItemClickListener listener) {
        this.vehicules = new ArrayList<>();
        this.listener = listener;
    }

    public void addVehicules(List<Vehicule> vehicules){

        this.vehicules.clear();
        this.vehicules.addAll(vehicules);
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
        holder.tvVehicule.setText(String.format("%s %s (%s)", vehicule.getMarque(), vehicule.getModele(), vehicule.getTarifJournalier()));

    }

    @Override
    public int getItemCount() {
        return vehicules.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvVehicule;

        ViewHolder(View itemView) {
            super(itemView);

            tvVehicule = itemView.findViewById(R.id.tv_vehicule);
            itemView.setOnClickListener( e -> {
                if(selectedItem != itemView){
                    if(selectedItem != null){
                        selectedItem.setSelected(false);
                    }
                    selectedItem = itemView;
                    selectedItem.setSelected(true);
                } else {
                    selectedItem.setSelected(false);
                    selectedItem = null;
                }

                //itemView.setSelected(!itemView.isSelected());
                listener.onItemClick(vehicules.get(getAdapterPosition()));
            });

        }
    }

    public interface ItemClickListener {
        void onItemClick(Vehicule vehicule);
    }
}
