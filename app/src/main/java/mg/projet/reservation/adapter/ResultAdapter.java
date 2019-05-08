package mg.projet.reservation.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import mg.projet.reservation.R;
import mg.projet.reservation.model.Trajet;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {
    private List<Trajet> trajets;

    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView heure_depart;
        public TextView ville_depart;
        public TextView heure_arrivee;
        public TextView ville_arrivee;

        public MyViewHolder(View v) {
            super(v);
            heure_depart = (TextView) v.findViewById(R.id.result_heure_depart);
            ville_depart = (TextView) v.findViewById(R.id.result_ville_depart);
            heure_arrivee = (TextView) v.findViewById(R.id.result_heure_arrivee);
            ville_arrivee = (TextView) v.findViewById(R.id.result_ville_arrivee);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public ResultAdapter(List<Trajet> _trajets) {
        trajets = _trajets;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public ResultAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                         int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // create a new view
        View v = inflater.inflate(R.layout.activity_trajet_result, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Trajet trajet = trajets.get(position);
        DateFormat df = new SimpleDateFormat("dd MMM y");
        DateFormat tf = new SimpleDateFormat("HH:mm");

        TextView heure_depart = holder.heure_depart;
        TextView ville_depart = holder.ville_depart;
        TextView heure_arrivee = holder.heure_arrivee;
        TextView ville_arrivee = holder.ville_arrivee;
        heure_depart.setText(df.format(trajet.getDate()) + " " + tf.format(trajet.getHeure_depart()));
        ville_depart.setText(trajet.getDepart().getNom().toUpperCase());
        heure_arrivee.setText(df.format(trajet.getDate()) + " " + tf.format(trajet.getHeure_arrivee()));
        ville_arrivee.setText(trajet.getArrivee().getNom().toUpperCase());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return trajets.size();
    }
}
