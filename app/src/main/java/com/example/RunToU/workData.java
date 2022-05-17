package com.example.RunToU;

public class workData {
    private int iv_Cate;
    private String tv_Money;
    private String tv_Far;
    private String tv_Res;
    private String txtNum;

    public workData(int iv_Cate, String tv_Money, String tv_Far, String tv_Res, String sheetId) {
        this.iv_Cate = iv_Cate;
        this.tv_Money = tv_Money;
        this.tv_Far = tv_Far;
        this.tv_Res = tv_Res;
        this.txtNum = sheetId;
    }

    public String getTxtNum() {
        return txtNum;
    }

    public void setTxtNum(String txtNum) {
        this.txtNum = txtNum;
    }

    public int getIv_Cate() {
        return iv_Cate;
    }

    public void setIv_Cate(int iv_Cate) {
        this.iv_Cate = iv_Cate;
    }

    public String getTv_Money() {
        return tv_Money;
    }

    public void setTv_Money(String tv_Money) {
        this.tv_Money = tv_Money;
    }

    public String getTv_Far() {
        return tv_Far;
    }

    public void setTv_Far(String tv_Far) {
        this.tv_Far = tv_Far;
    }

    public String getTv_Res() {
        return tv_Res;
    }

    public void setTv_Res(String tv_Res) {
        this.tv_Res = tv_Res;
    }
}