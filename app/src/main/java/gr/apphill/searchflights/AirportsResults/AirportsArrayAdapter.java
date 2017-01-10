package gr.apphill.searchflights.AirportsResults;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import gr.apphill.searchflights.AirportsResults.Airport;
import gr.apphill.searchflights.R;

/**
 * Created by Dimitrios on 29-Dec-16.
 */

public class AirportsArrayAdapter extends ArrayAdapter<Airport> {
    private final Context context;
    private final ArrayList<Airport> values;

    public AirportsArrayAdapter(Context context, ArrayList<Airport> values) {
        super(context, -1, values);
        this.context = context;
        this.values = values;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.airports_row, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.firstLine);

        textView.setText(values.get(position).getLabel());
        // change the icon for Windows and iPhone
        return rowView;
    }//getView



    public void setNewData(List<Airport> newData) {
        for (Airport temp : newData) {
            values.add(temp);
        }//for
        notifyDataSetChanged();
    }//setNewData


}//MySimpleArrayAdapter