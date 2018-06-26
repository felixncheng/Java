package com;

/**
 * @author cheng_mboy
 * @create 2017-11-28-22:50
 */
public class SkuInfo {

    private int rowNum;
    private String sku;
    private int review;
    private float rank;
    private String FbaListing;

    @Override
    public String toString() {
        return "SkuInfo{" +
                "rowNum=" + rowNum +
                ", sku='" + sku + '\'' +
                ", review=" + review +
                ", rank=" + rank +
                ", FbaListing='" + FbaListing + '\'' +
                '}';
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public int getReview() {
        return review;
    }

    public void setReview(int review) {
        this.review = review;
    }

    public float getRank() {
        return rank;
    }

    public void setRank(float rank) {
        this.rank = rank;
    }

    public String getFbaListing() {
        return FbaListing;
    }

    public void setFbaListing(String fbaListing) {
        FbaListing = fbaListing;
    }
}
