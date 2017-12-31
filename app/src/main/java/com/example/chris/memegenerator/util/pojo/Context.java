
package com.example.ady.memesapp.pojo;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Context {

    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("facets")
    @Expose
    private List<List<Facet>> facets = null;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<List<Facet>> getFacets() {
        return facets;
    }

    public void setFacets(List<List<Facet>> facets) {
        this.facets = facets;
    }

}
