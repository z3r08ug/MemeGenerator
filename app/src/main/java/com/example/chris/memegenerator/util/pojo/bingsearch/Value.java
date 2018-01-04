
package com.example.chris.memegenerator.util.pojo.bingsearch;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Value {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("datePublished")
    @Expose
    private String datePublished;
    @SerializedName("homePageUrl")
    @Expose
    private Object homePageUrl;
    @SerializedName("contentSize")
    @Expose
    private String contentSize;
    @SerializedName("hostPageDisplayUrl")
    @Expose
    private String hostPageDisplayUrl;
    @SerializedName("width")
    @Expose
    private Integer width;
    @SerializedName("height")
    @Expose
    private Integer height;
    @SerializedName("thumbnail")
    @Expose
    private Thumbnail thumbnail;
    @SerializedName("imageInsightsToken")
    @Expose
    private String imageInsightsToken;
    @SerializedName("insightsSourcesSummary")
    @Expose
    private Object insightsSourcesSummary;
    @SerializedName("imageId")
    @Expose
    private String imageId;
    @SerializedName("accentColor")
    @Expose
    private String accentColor;
    @SerializedName("webSearchUrl")
    @Expose
    private String webSearchUrl;
    @SerializedName("thumbnailUrl")
    @Expose
    private String thumbnailUrl;
    @SerializedName("encodingFormat")
    @Expose
    private String encodingFormat;
    @SerializedName("contentUrl")
    @Expose
    private String contentUrl;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDatePublished() {
        return datePublished;
    }

    public void setDatePublished(String datePublished) {
        this.datePublished = datePublished;
    }

    public Object getHomePageUrl() {
        return homePageUrl;
    }

    public void setHomePageUrl(Object homePageUrl) {
        this.homePageUrl = homePageUrl;
    }

    public String getContentSize() {
        return contentSize;
    }

    public void setContentSize(String contentSize) {
        this.contentSize = contentSize;
    }

    public String getHostPageDisplayUrl() {
        return hostPageDisplayUrl;
    }

    public void setHostPageDisplayUrl(String hostPageDisplayUrl) {
        this.hostPageDisplayUrl = hostPageDisplayUrl;
    }

    public Integer getWidth() {
        return width;
    }

    public void setWidth(Integer width) {
        this.width = width;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getImageInsightsToken() {
        return imageInsightsToken;
    }

    public void setImageInsightsToken(String imageInsightsToken) {
        this.imageInsightsToken = imageInsightsToken;
    }

    public Object getInsightsSourcesSummary() {
        return insightsSourcesSummary;
    }

    public void setInsightsSourcesSummary(Object insightsSourcesSummary) {
        this.insightsSourcesSummary = insightsSourcesSummary;
    }

    public String getImageId() {
        return imageId;
    }

    public void setImageId(String imageId) {
        this.imageId = imageId;
    }

    public String getAccentColor() {
        return accentColor;
    }

    public void setAccentColor(String accentColor) {
        this.accentColor = accentColor;
    }

    public String getWebSearchUrl() {
        return webSearchUrl;
    }

    public void setWebSearchUrl(String webSearchUrl) {
        this.webSearchUrl = webSearchUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getEncodingFormat() {
        return encodingFormat;
    }

    public void setEncodingFormat(String encodingFormat) {
        this.encodingFormat = encodingFormat;
    }

    public String getContentUrl() {
        return contentUrl;
    }

    public void setContentUrl(String contentUrl) {
        this.contentUrl = contentUrl;
    }

}
