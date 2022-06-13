package com.example.RunToU.profile;

public class reviewData {

    private int iv_Profile;
    private String tv_Id;
    private String tv_Work;
    private String tv_Star;
    private String tv_Review;

    public reviewData(int iv_Profile, String tv_Id, String tv_Work, String tv_Star, String tv_Review) {
        this.iv_Profile = iv_Profile;
        this.tv_Id = tv_Id;
        this.tv_Work = tv_Work;
        this.tv_Star = tv_Star;
        this.tv_Review = tv_Review;
    }

    public int getIv_Profile() {
        return iv_Profile;
    }

    public void setIv_Profile(int iv_Profile) {
        this.iv_Profile = iv_Profile;
    }

    public String getTv_Id() {
        return tv_Id;
    }

    public void setTv_Id(String tv_Id) {
        this.tv_Id = tv_Id;
    }

    public String getTv_Work() {
        return tv_Work;
    }

    public void setTv_Work(String tv_Work) {
        this.tv_Work = tv_Work;
    }

    public String getTv_Star() {
        return tv_Star;
    }

    public void setTv_Star(String tv_Star) {
        this.tv_Star = tv_Star;
    }

    public String getTv_Review() {
        return tv_Review;
    }

    public void setTv_Review(String tv_Review) {
        this.tv_Review = tv_Review;
    }
}
