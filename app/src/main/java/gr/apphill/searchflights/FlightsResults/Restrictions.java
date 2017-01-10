package gr.apphill.searchflights.FlightsResults;

/**
 * Created by Dimitrios on 02-Jan-17.
 */

public class Restrictions {
    private boolean refundable;
    private boolean change_penalties;

    public Restrictions(boolean refundable, boolean change_penalties) {
        this.refundable = refundable;
        this.change_penalties = change_penalties;
    }


    public boolean isRefundable() {
        return refundable;
    }

    public void setRefundable(boolean refundable) {
        this.refundable = refundable;
    }

    public boolean isChange_penalties() {
        return change_penalties;
    }

    public void setChange_penalties(boolean change_penalties) {
        this.change_penalties = change_penalties;
    }
}//Restrictions
