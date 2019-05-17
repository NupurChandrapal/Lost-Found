package com.sazal.siddiqui.lost_people.Model;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LostFound {

    @SerializedName("error")
    @Expose
    private boolean error;
    @SerializedName("LostFound")
    @Expose
    private List<LostFoundData> lostFound = null;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<LostFoundData> getLostFound() {
        return lostFound;
    }

    public void setLostFound(List<LostFoundData> lostFound) {
        this.lostFound = lostFound;
    }
}
