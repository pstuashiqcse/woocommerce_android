package com.mcc.madokdrobyo.rehabCenter;

import android.content.Context;

import com.mcc.madokdrobyo.objects.RehabInfoModel;

/**
 * Created by nitul on 1/29/17.
 */

public class RehabInformation {

    private String strRehabInfo;
    private RehabInfoModel rehab;

    public RehabInformation(String strRehabInfo){
        this.strRehabInfo = strRehabInfo;
    }

    public void transformIntoRehabModel(){

        String[] arrRehabInfo = strRehabInfo.split("\n");
        arrRehabInfo = modifyData(arrRehabInfo);
        rehab = new RehabInfoModel(arrRehabInfo[0], arrRehabInfo[1], arrRehabInfo[2]);
    }

    private String[] modifyData(String[] arrRehabInfo) {

        for (int index = 0; index<arrRehabInfo.length; index++){

            String [] str = arrRehabInfo[index].split(" ");
            StringBuilder builder = new StringBuilder();

            int currentIndex = 0;
            if (index > 0)
                currentIndex = 1;

            for (int nestedIndex = currentIndex; nestedIndex < str.length; nestedIndex++){
                builder.append(str[nestedIndex]);
                builder.append(" ");
            }

            arrRehabInfo[index] = builder.toString();
        }
        return arrRehabInfo;
    }

    public RehabInfoModel getRehabInfo(){
        return rehab;
    }

}
