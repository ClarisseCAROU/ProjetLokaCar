package fr.eni.projetlokacar.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import fr.eni.projetlokacar.R;
import fr.eni.projetlokacar.bo.Client;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {

    private List<Client> clients;
    private ClickClientListener clickClientListener;

    public ClientAdapter(ClickClientListener clickClientListener) {
        this.clients = new ArrayList<>();
        this.clickClientListener = clickClientListener;
    }

    public void addListeClients(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.client_dans_recycler, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvPrenomCliRecycler.setText(this.clients.get(position).getPrenom());
        holder.tvNomCliRecycler.setText(this.clients.get(position).getNom());
    }

    @Override
    public int getItemCount() {
        return clients != null ? clients.size() : 0;
    }

    //Cette classe est une référence à la vue permet d'afficher un element (client)
    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView tvPrenomCliRecycler;
        private TextView tvNomCliRecycler;

        public ViewHolder(View itemView) {
            super(itemView);
            this.tvPrenomCliRecycler = itemView.findViewById(R.id.tvPrenomCliRecycler);
            this.tvNomCliRecycler = itemView.findViewById(R.id.tvNomCliRecycler);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (clickClientListener != null) {
                        clickClientListener.onClickClient(clients.get(ViewHolder.this.getAdapterPosition()));
                    }
                }
            });
        }
    }


    public interface ClickClientListener {
        void onClickClient(Client client);
    }
}
