package com.company.project.kkantireptile.cn.keking.anti_reptile.module;

import java.io.Serializable;

/**
 * @author chenjh
 * @since 2019/7/16 14:58
 */
public class VerifyImageVO implements Serializable {

    private static final long serialVersionUID = 345634706484343777L;

    private String verifyId;
    private String verifyType;
    private String verifyImgStr;

    public String getVerifyId() {
        return verifyId;
    }

    public void setVerifyId(String verifyId) {
        this.verifyId = verifyId;
    }

    public String getVerifyType() {
        return verifyType;
    }

    public void setVerifyType(String verifyType) {
        this.verifyType = verifyType;
    }

    public String getVerifyImgStr() {
        return verifyImgStr;
    }

    public void setVerifyImgStr(String verifyImgStr) {
        this.verifyImgStr = verifyImgStr;
    }

    @Override
    public String toString() {
        return "VerifyImageVO{" +
                "verifyId='" + verifyId + '\'' +
                ", verifyType='" + verifyType + '\'' +
                ", verifyImgStr='" + verifyImgStr + '\'' +
                '}';
    }
}
