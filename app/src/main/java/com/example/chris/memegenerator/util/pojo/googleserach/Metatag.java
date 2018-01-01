
package com.example.chris.memegenerator.util.pojo.googleserach;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Metatag {

    @SerializedName("viewport")
    @Expose
    private String viewport;
    @SerializedName("og:type")
    @Expose
    private String ogType;
    @SerializedName("og:title")
    @Expose
    private String ogTitle;
    @SerializedName("og:description")
    @Expose
    private String ogDescription;
    @SerializedName("og:url")
    @Expose
    private String ogUrl;
    @SerializedName("og:image")
    @Expose
    private String ogImage;
    @SerializedName("twitter:card")
    @Expose
    private String twitterCard;
    @SerializedName("twitter:title")
    @Expose
    private String twitterTitle;
    @SerializedName("twitter:description")
    @Expose
    private String twitterDescription;
    @SerializedName("twitter:image:src")
    @Expose
    private String twitterImageSrc;
    @SerializedName("dc.type")
    @Expose
    private String dcType;
    @SerializedName("dc.title")
    @Expose
    private String dcTitle;
    @SerializedName("dc.contributor")
    @Expose
    private String dcContributor;
    @SerializedName("dc.date")
    @Expose
    private String dcDate;
    @SerializedName("dc.description")
    @Expose
    private String dcDescription;
    @SerializedName("dc.relation")
    @Expose
    private String dcRelation;
    @SerializedName("citation_patent_publication_number")
    @Expose
    private String citationPatentPublicationNumber;
    @SerializedName("citation_patent_application_number")
    @Expose
    private String citationPatentApplicationNumber;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("og:site_name")
    @Expose
    private String ogSiteName;
    @SerializedName("citation_reference")
    @Expose
    private String citationReference;

    public String getViewport() {
        return viewport;
    }

    public void setViewport(String viewport) {
        this.viewport = viewport;
    }

    public String getOgType() {
        return ogType;
    }

    public void setOgType(String ogType) {
        this.ogType = ogType;
    }

    public String getOgTitle() {
        return ogTitle;
    }

    public void setOgTitle(String ogTitle) {
        this.ogTitle = ogTitle;
    }

    public String getOgDescription() {
        return ogDescription;
    }

    public void setOgDescription(String ogDescription) {
        this.ogDescription = ogDescription;
    }

    public String getOgUrl() {
        return ogUrl;
    }

    public void setOgUrl(String ogUrl) {
        this.ogUrl = ogUrl;
    }

    public String getOgImage() {
        return ogImage;
    }

    public void setOgImage(String ogImage) {
        this.ogImage = ogImage;
    }

    public String getTwitterCard() {
        return twitterCard;
    }

    public void setTwitterCard(String twitterCard) {
        this.twitterCard = twitterCard;
    }

    public String getTwitterTitle() {
        return twitterTitle;
    }

    public void setTwitterTitle(String twitterTitle) {
        this.twitterTitle = twitterTitle;
    }

    public String getTwitterDescription() {
        return twitterDescription;
    }

    public void setTwitterDescription(String twitterDescription) {
        this.twitterDescription = twitterDescription;
    }

    public String getTwitterImageSrc() {
        return twitterImageSrc;
    }

    public void setTwitterImageSrc(String twitterImageSrc) {
        this.twitterImageSrc = twitterImageSrc;
    }

    public String getDcType() {
        return dcType;
    }

    public void setDcType(String dcType) {
        this.dcType = dcType;
    }

    public String getDcTitle() {
        return dcTitle;
    }

    public void setDcTitle(String dcTitle) {
        this.dcTitle = dcTitle;
    }

    public String getDcContributor() {
        return dcContributor;
    }

    public void setDcContributor(String dcContributor) {
        this.dcContributor = dcContributor;
    }

    public String getDcDate() {
        return dcDate;
    }

    public void setDcDate(String dcDate) {
        this.dcDate = dcDate;
    }

    public String getDcDescription() {
        return dcDescription;
    }

    public void setDcDescription(String dcDescription) {
        this.dcDescription = dcDescription;
    }

    public String getDcRelation() {
        return dcRelation;
    }

    public void setDcRelation(String dcRelation) {
        this.dcRelation = dcRelation;
    }

    public String getCitationPatentPublicationNumber() {
        return citationPatentPublicationNumber;
    }

    public void setCitationPatentPublicationNumber(String citationPatentPublicationNumber) {
        this.citationPatentPublicationNumber = citationPatentPublicationNumber;
    }

    public String getCitationPatentApplicationNumber() {
        return citationPatentApplicationNumber;
    }

    public void setCitationPatentApplicationNumber(String citationPatentApplicationNumber) {
        this.citationPatentApplicationNumber = citationPatentApplicationNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOgSiteName() {
        return ogSiteName;
    }

    public void setOgSiteName(String ogSiteName) {
        this.ogSiteName = ogSiteName;
    }

    public String getCitationReference() {
        return citationReference;
    }

    public void setCitationReference(String citationReference) {
        this.citationReference = citationReference;
    }

}
