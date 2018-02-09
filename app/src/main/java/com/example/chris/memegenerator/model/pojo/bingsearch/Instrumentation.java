
package com.example.chris.memegenerator.model.pojo.bingsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Instrumentation {

    @SerializedName("pageLoadPingUrl")
    @Expose
    private Object pageLoadPingUrl;

    public Object getPageLoadPingUrl() {
        return pageLoadPingUrl;
    }

    public void setPageLoadPingUrl(Object pageLoadPingUrl) {
        this.pageLoadPingUrl = pageLoadPingUrl;
    }

}
