
package com.example.chris.memegenerator.util.pojo.bingsearch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PivotSuggestion {

    @SerializedName("pivot")
    @Expose
    private String pivot;
    @SerializedName("suggestions")
    @Expose
    private List<Suggestion> suggestions = null;

    public String getPivot() {
        return pivot;
    }

    public void setPivot(String pivot) {
        this.pivot = pivot;
    }

    public List<Suggestion> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<Suggestion> suggestions) {
        this.suggestions = suggestions;
    }

}
