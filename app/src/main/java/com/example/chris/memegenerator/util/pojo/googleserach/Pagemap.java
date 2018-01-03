
package com.example.chris.memegenerator.util.pojo.googleserach;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pagemap {

    @SerializedName("cse_thumbnail")
    @Expose
    private List<CseThumbnail> cseThumbnail = null;
    @SerializedName("website")
    @Expose
    private List<Website> website = null;
    @SerializedName("metatags")
    @Expose
    private List<Metatag> metatags = null;
    @SerializedName("cse_image")
    @Expose
    private List<CseImage> cseImage = null;
    @SerializedName("book")
    @Expose
    private List<Book> book = null;

    public List<CseThumbnail> getCseThumbnail() {
        return cseThumbnail;
    }

    public void setCseThumbnail(List<CseThumbnail> cseThumbnail) {
        this.cseThumbnail = cseThumbnail;
    }

    public List<Website> getWebsite() {
        return website;
    }

    public void setWebsite(List<Website> website) {
        this.website = website;
    }

    public List<Metatag> getMetatags() {
        return metatags;
    }

    public void setMetatags(List<Metatag> metatags) {
        this.metatags = metatags;
    }

    public List<CseImage> getCseImage() {
        return cseImage;
    }

    public void setCseImage(List<CseImage> cseImage) {
        this.cseImage = cseImage;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

}
