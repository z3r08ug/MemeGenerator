
package com.example.chris.memegenerator.util.pojo.keywordfinder;

import java.util.List;

import com.example.Text;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Keywords {

    @SerializedName("text")
    @Expose
    private List<List<List<Text>>> text = null;
    @SerializedName("confidence")
    @Expose
    private Double confidence;

    public List<List<List<Text>>> getText() {
        return text;
    }

    public void setText(List<List<List<Text>>> text) {
        this.text = text;
    }

    public Double getConfidence() {
        return confidence;
    }

    public void setConfidence(Double confidence) {
        this.confidence = confidence;
    }

}
