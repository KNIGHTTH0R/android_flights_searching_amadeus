package gr.apphill.searchflights.FlightsResults;

/**
 * Created by Dimitrios on 02-Jan-17.
 */

public class Itineraries {
    private OutboundInbound outbound;
    private OutboundInbound inbound;

    public Itineraries(OutboundInbound outbound, OutboundInbound inbound) {
        this.outbound = outbound;
        this.inbound = inbound;
    }

    public OutboundInbound getOutbound() {
        return outbound;
    }

    public void setOutbound(OutboundInbound outbound) {
        this.outbound = outbound;
    }

    public OutboundInbound getInbound() {
        return inbound;
    }

    public void setInbound(OutboundInbound inbound) {
        this.inbound = inbound;
    }

}//Itineraries Class