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
import mg.projet.reservation.model.Notification;

public class AlertAdapter extends RecyclerView.Adapter<AlertAdapter.MyViewHolder> {
    private List<Notification> alerts;

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
            heure_depart = (TextView) v.findViewById(R.id.alert_heure_depart);
            ville_depart = (TextView) v.findViewById(R.id.alert_ville_depart);
            heure_arrivee = (TextView) v.findViewById(R.id.alert_heure_arrivee);
            ville_arrivee = (TextView) v.findViewById(R.id.alert_ville_arrivee);
        }
    }

    // Provide a suitable constructor (depends on the kind of dataset)
    public AlertAdapter(List<Notification> _alerts) {
        alerts = _alerts;
    }

    // Create new views (invoked by the layout manager)
    @Override
    public AlertAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent,
                                                        int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        // create a new view
        View v = inflater.inflate(R.layout.activity_notification, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    /**
     * Ajout d'une alerte
     *
     * @param notification
     */
    public void addAlert(Notification notification) {
        alerts.add(notification);
        notifyDataSetChanged();
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        Notification notification = alerts.get(position);
        DateFormat df = new SimpleDateFormat("dd MMM y");
        DateFormat tf = new SimpleDateFormat("HH:mm");

        TextView heure_depart = holder.heure_depart;
        TextView ville_depart = holder.ville_depart;
        TextView heure_arrivee = holder.heure_arrivee;
        TextView ville_arrivee = holder.ville_arrivee;
        heure_depart.setText(df.format(notification.getDate()) + " " + tf.format(notification.getHeure_depart()));
        ville_depart.setText(notification.getDepart().toUpperCase());
        heure_arrivee.setText(df.format(notification.getDate()) + " " + tf.format(notification.getHeure_arrivee()));
        ville_arrivee.setText(notification.getArrivee().toUpperCase());
    }

    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return alerts.size();
    }
}
