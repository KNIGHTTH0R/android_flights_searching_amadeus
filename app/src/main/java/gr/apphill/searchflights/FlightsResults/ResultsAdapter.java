package gr.apphill.searchflights.FlightsResults;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.ParseException;
import java.util.List;

import gr.apphill.searchflights.R;
import gr.apphill.searchflights.utilities.Utils;

import static gr.apphill.searchflights.R.id.destinationInboundTime;
import static gr.apphill.searchflights.R.id.inboundItinerariesLayout;
import static gr.apphill.searchflights.R.id.itineraryInboundDurationTextView;
import static gr.apphill.searchflights.R.id.itineraryOutboundDurationTextView;
import static gr.apphill.searchflights.R.id.originOutboundTime;

/**
 * Created by Dimitrios on 02-Jan-17.
 */

public class ResultsAdapter extends RecyclerView.Adapter<ResultsAdapter.MyViewHolder> {
    private List<Results> resultsList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //layout views
        public TextView priceTextView, outboundAirline, originOutboundAirport, originOutboundTime, itineraryOutboundDurationTextView, destinationInboundAirport, destinationInboundTime, inboundAirline, originInboundAirport, originInboundTime, itineraryInboundDurationTextView, destinationOutboundAirport, destinationOutboundTime;
        RelativeLayout inboundItinerariesLayout;

        public MyViewHolder(View view) {
            super(view);
            priceTextView = (TextView) view.findViewById(R.id.priceTextView);
            originOutboundAirport = (TextView) view.findViewById(R.id.originOutboundAirport);
            originOutboundTime = (TextView) view.findViewById(R.id.originOutboundTime);
            itineraryOutboundDurationTextView = (TextView) view.findViewById(R.id.itineraryOutboundDurationTextView);
            destinationInboundAirport = (TextView) view.findViewById(R.id.destinationInboundAirport);
            destinationInboundTime = (TextView) view.findViewById(R.id.destinationInboundTime);
            inboundAirline = (TextView) view.findViewById(R.id.inboundAirline);
            originInboundAirport = (TextView) view.findViewById(R.id.originInboundAirport);
            originInboundTime = (TextView) view.findViewById(R.id.originInboundTime);
            itineraryInboundDurationTextView = (TextView) view.findViewById(R.id.itineraryInboundDurationTextView);
            destinationOutboundAirport = (TextView) view.findViewById(R.id.destinationOutboundAirport);
            destinationOutboundTime = (TextView) view.findViewById(R.id.destinationOutboundTime);
            inboundItinerariesLayout = (RelativeLayout) view.findViewById(R.id.inboundItinerariesLayout);
            outboundAirline = (TextView) view.findViewById(R.id.outboundAirline);
            inboundAirline = (TextView) view.findViewById(R.id.inboundAirline);
        }//MyViewHolder
    }//MyViewHolder


    public ResultsAdapter(List<Results> resultsList) {
        this.resultsList = resultsList;
    }//ResultsAdapter

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.results_row, parent, false);

        return new MyViewHolder(itemView);
    }//onCreateViewHolder

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Results results = resultsList.get(position);
        //left column layout
        holder.priceTextView.setText(results.getFare().getTotal_price());
        //right column layout
        //outbound
        holder.originOutboundAirport.setText(results.getItineraries().get(0).getOutbound().getFlights().get(0).getOrigin().getAirport());
        holder.destinationOutboundAirport.setText(results.getItineraries().get(0).getOutbound().getFlights().get(results.getItineraries().get(0).getOutbound().getFlights().size() - 1).getDestination().getAirport());
        holder.originOutboundTime.setText(results.getItineraries().get(0).getOutbound().getFlights().get(0).getDeparts_at().substring(11, 16));
        holder.destinationOutboundTime.setText(results.getItineraries().get(0).getOutbound().getFlights().get(results.getItineraries().get(0).getOutbound().getFlights().size() - 1).getArrives_at().substring(11, 16));
        if(results.getItineraries().get(0).getOutbound().getAirlineName().size()>1){
            holder.outboundAirline.setText("Multiple Airlines");
        } else {
            holder.outboundAirline.setText(results.getItineraries().get(0).getOutbound().getAirlineName().get(0));
        }
        try {
            holder.itineraryOutboundDurationTextView.setText(Utils.calcualteTimeDifference(holder.originOutboundTime.getText() + "", holder.destinationOutboundTime.getText() + "") + "");
        } catch (ParseException e) {
            e.printStackTrace();
        }

        //inbound
        if (!(results.getItineraries().get(0).getInbound() == null)) {
            holder.originInboundAirport.setText(results.getItineraries().get(0).getInbound().getFlights().get(0).getOrigin().getAirport());
            holder.destinationInboundAirport.setText(results.getItineraries().get(0).getInbound().getFlights().get(results.getItineraries().get(0).getInbound().getFlights().size() - 1).getDestination().getAirport());
            holder.originInboundTime.setText(results.getItineraries().get(0).getInbound().getFlights().get(0).getDeparts_at().substring(11, 16));
            holder.destinationInboundTime.setText(results.getItineraries().get(0).getInbound().getFlights().get(results.getItineraries().get(0).getInbound().getFlights().size() - 1).getArrives_at().substring(11, 16));
            if(results.getItineraries().get(0).getInbound().getAirlineName().size()>1){
                holder.inboundAirline.setText("Multiple Airlines");
            } else {
                holder.inboundAirline.setText(results.getItineraries().get(0).getInbound().getFlights().get(0).getMarketing_airline());
            }
            try {
                holder.itineraryInboundDurationTextView.setText(Utils.calcualteTimeDifference(holder.originInboundTime.getText() + "", holder.destinationInboundTime.getText() + "") + "");
            } catch (ParseException e) {
                e.printStackTrace();
            }
        } else {
            holder.inboundItinerariesLayout.setVisibility(View.INVISIBLE);
        }

    }//onBindViewHolder

    @Override
    public int getItemCount() {
        return resultsList.size();
    }//getItemCount

    public void setNewData(List<Results> newData) {
        resultsList.clear();
        for (Results temp : newData) {
            resultsList.add(temp);
        }//for
        notifyDataSetChanged();
    }//setNewData


}//ResultsAdapter
