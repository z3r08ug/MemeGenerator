
package com.example.chris.memegenerator.util.pojo.bingsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class InsightsMetadata {

    @SerializedName("recipeSourcesCount")
    @Expose
    private Integer recipeSourcesCount;

    public Integer getRecipeSourcesCount() {
        return recipeSourcesCount;
    }

    public void setRecipeSourcesCount(Integer recipeSourcesCount) {
        this.recipeSourcesCount = recipeSourcesCount;
    }

}
