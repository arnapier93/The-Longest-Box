package com.example.MyComicsApp;

import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity (tableName = "comics_library", primaryKeys = {"seriesTitle", "issueNum"})
public class Comic {

    public enum Publisher {
        MARVEL,
        DC,
        INDIE
    }
    Publisher publisher;
    String indiePublisher = null;
    @NonNull
    String seriesTitle;
    @NonNull
    int issueNum;
    byte[] bitmapAsBytes;
    String writer;
    String artist;
    String publishedDate;
    String purchasedDate;
    Float purchasedPrice;

    public byte[] getBitmapAsBytes() {
        return bitmapAsBytes;
    }

    public void setBitmapAsBytes(byte[] bitmapAsBytes) {
        this.bitmapAsBytes = bitmapAsBytes;
    }

    public Comic(Publisher publisher, String indiePublisher, byte[] bitmapAsBytes,
                 String seriesTitle, int issueNum, String writer, String artist) {

        this.publisher = publisher;
        this.indiePublisher = indiePublisher;
        this.bitmapAsBytes = bitmapAsBytes;
        this.seriesTitle = seriesTitle;
        this.issueNum = issueNum;
        this.writer = writer;
        this.artist = artist;
        this.publishedDate = null;
        this.purchasedDate = null;
        this.purchasedPrice = 0.0f;
    }

    public boolean matches(Comic comicToCompare){
        if(comicToCompare.seriesTitle.matches(seriesTitle) &&
            comicToCompare.issueNum == issueNum)
        {
            return true;
        }
        return false;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public String getPurchasedDate() {
        return purchasedDate;
    }

    public void setPurchasedDate(String purchasedDate) {
        this.purchasedDate = purchasedDate;
    }
    public Publisher getPublisher() {
        return publisher;
    }

    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    public String getIndiePublisher() { return indiePublisher; }

    public void setIndiePublisher(String indiePublisher) {
        this.indiePublisher = indiePublisher;
    }

    public String getSeriesTitle() {
        return seriesTitle;
    }

    public void setSeriesTitle(String seriesTitle) {
        this.seriesTitle = seriesTitle;
    }

    public String getIssueNum() {
        return "# " + Integer.toString(issueNum);
    }

    public void setIssueNum(int issueNum) {
        this.issueNum = issueNum;
    }

    public String getWriter() { return writer; }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getArtist() { return artist; }

    public void setArtist(String artist) {
        this.artist = artist;
    }


    public Float getPurchasedPrice() {
        return purchasedPrice;
    }

    public void setPurchasedPrice(Float purchasedPrice) {
        this.purchasedPrice = purchasedPrice;
    }

}
