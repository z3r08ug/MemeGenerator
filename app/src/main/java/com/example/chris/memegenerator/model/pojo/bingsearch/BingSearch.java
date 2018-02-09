
package com.example.chris.memegenerator.model.pojo.bingsearch;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class BingSearch {

    @SerializedName("_type")
    @Expose
    private String type;
    @SerializedName("instrumentation")
    @Expose
    private Instrumentation instrumentation;
    @SerializedName("webSearchUrl")
    @Expose
    private String webSearchUrl;
    @SerializedName("totalEstimatedMatches")
    @Expose
    private Integer totalEstimatedMatches;
    @SerializedName("value")
    @Expose
    private List<Value> value = null;
    @SerializedName("queryExpansions")
    @Expose
    private List<QueryExpansion> queryExpansions = null;
    @SerializedName("nextOffsetAddCount")
    @Expose
    private Integer nextOffsetAddCount;
    @SerializedName("pivotSuggestions")
    @Expose
    private List<PivotSuggestion> pivotSuggestions = null;
    @SerializedName("displayShoppingSourcesBadges")
    @Expose
    private Boolean displayShoppingSourcesBadges;
    @SerializedName("displayRecipeSourcesBadges")
    @Expose
    private Boolean displayRecipeSourcesBadges;
    @SerializedName("similarTerms")
    @Expose
    private Object similarTerms;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Instrumentation getInstrumentation() {
        return instrumentation;
    }

    public void setInstrumentation(Instrumentation instrumentation) {
        this.instrumentation = instrumentation;
    }

    public String getWebSearchUrl() {
        return webSearchUrl;
    }

    public void setWebSearchUrl(String webSearchUrl) {
        this.webSearchUrl = webSearchUrl;
    }

    public Integer getTotalEstimatedMatches() {
        return totalEstimatedMatches;
    }

    public void setTotalEstimatedMatches(Integer totalEstimatedMatches) {
        this.totalEstimatedMatches = totalEstimatedMatches;
    }

    public List<Value> getValue() {
        return value;
    }

    public void setValue(List<Value> value) {
        this.value = value;
    }

    public List<QueryExpansion> getQueryExpansions() {
        return queryExpansions;
    }

    public void setQueryExpansions(List<QueryExpansion> queryExpansions) {
        this.queryExpansions = queryExpansions;
    }

    public Integer getNextOffsetAddCount() {
        return nextOffsetAddCount;
    }

    public void setNextOffsetAddCount(Integer nextOffsetAddCount) {
        this.nextOffsetAddCount = nextOffsetAddCount;
    }

    public List<PivotSuggestion> getPivotSuggestions() {
        return pivotSuggestions;
    }

    public void setPivotSuggestions(List<PivotSuggestion> pivotSuggestions) {
        this.pivotSuggestions = pivotSuggestions;
    }

    public Boolean getDisplayShoppingSourcesBadges() {
        return displayShoppingSourcesBadges;
    }

    public void setDisplayShoppingSourcesBadges(Boolean displayShoppingSourcesBadges) {
        this.displayShoppingSourcesBadges = displayShoppingSourcesBadges;
    }

    public Boolean getDisplayRecipeSourcesBadges() {
        return displayRecipeSourcesBadges;
    }

    public void setDisplayRecipeSourcesBadges(Boolean displayRecipeSourcesBadges) {
        this.displayRecipeSourcesBadges = displayRecipeSourcesBadges;
    }

    public Object getSimilarTerms() {
        return similarTerms;
    }

    public void setSimilarTerms(Object similarTerms) {
        this.similarTerms = similarTerms;
    }

}
