
package com.example.chris.memegenerator.util.pojo.bingsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RelatedSearch {

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
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail____ thumbnail;

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

    public Thumbnail____ getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail____ thumbnail) {
        this.thumbnail = thumbnail;
    }

}
