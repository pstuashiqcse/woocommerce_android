package com.mcc.madokdrobyo.objects;

/**
 * Created by nitul on 1/30/17.
 */

public class RehabInfoModel {

    private String rehabName;
    private String rehabAdderss;
    private String rehabContactInfo;

    public RehabInfoModel(String rehabName, String rehabAdderss, String rehabContactInfo){
        this.rehabName = rehabName;
        this.rehabAdderss = rehabAdderss;
        this.rehabContactInfo = rehabContactInfo;
    }

    public String getRehabAdderss() {
        return rehabAdderss;
    }
    public String getRehabContactInfo() {
        return rehabContactInfo;
    }
    public String getRehabName() {
        return rehabName;
    }
}
