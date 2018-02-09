
package com.example.chris.memegenerator.model.pojo.bingsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class QueryExpansion {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("displayText")
    @Expose
    private String displayText;
    @SerializedName("webSearchUrl")
    @Expose
    private String webSearchUrl;
    @SerializedName("searchLink")
    @Expose
    private String searchLink;
    @SerializedName("thumbnail1")
    @Expose
    private Object thumbnail1;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDisplayText() {
        return displayText;
    }

    public void setDisplayText(String displayText) {
        this.displayText = displayText;
    }

    public String getWebSearchUrl() {
        return webSearchUrl;
    }

    public void setWebSearchUrl(String webSearchUrl) {
        this.webSearchUrl = webSearchUrl;
    }

    public String getSearchLink() {
        return searchLink;
    }

    public void setSearchLink(String searchLink) {
        this.searchLink = searchLink;
    }

    public Object getThumbnail1() {
        return thumbnail1;
    }

    public void setThumbnail1(Object thumbnail1) {
        this.thumbnail1 = thumbnail1;
    }

}
