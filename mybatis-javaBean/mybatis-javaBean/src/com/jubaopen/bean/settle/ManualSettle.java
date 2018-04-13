package com.jubaopen.bean.settle;

import java.io.Serializable;
import java.util.Date;

public class ManualSettle implements Serializable {
    private Integer id;

    private String cashChannel;

    private String upCompany;

    private String transNo;

    private String accountName;

    private String bankMobile;

    private String accountNum;

    private String bankName;

    private String bankProvince;

    private String bankCity;

    private String bankBranch;

    private String accountType;

    private String settleAmount;

    private Integer state;

    private String upState;

    private Integer adminUserId;

    private String responseStr;

    private String subject;

    private String remark;

    private String settleRemark;

    private String settleOutNo;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCashChannel() {
        return cashChannel;
    }

    public void setCashChannel(String cashChannel) {
        this.cashChannel = cashChannel;
    }

    public String getUpCompany() {
        return upCompany;
    }

    public void setUpCompany(String upCompany) {
        this.upCompany = upCompany;
    }

    public String getTransNo() {
        return transNo;
    }

    public void setTransNo(String transNo) {
        this.transNo = transNo;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getBankMobile() {
        return bankMobile;
    }

    public void setBankMobile(String bankMobile) {
        this.bankMobile = bankMobile;
    }

    public String getAccountNum() {
        return accountNum;
    }

    public void setAccountNum(String accountNum) {
        this.accountNum = accountNum;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankProvince() {
        return bankProvince;
    }

    public void setBankProvince(String bankProvince) {
        this.bankProvince = bankProvince;
    }

    public String getBankCity() {
        return bankCity;
    }

    public void setBankCity(String bankCity) {
        this.bankCity = bankCity;
    }

    public String getBankBranch() {
        return bankBranch;
    }

    public void setBankBranch(String bankBranch) {
        this.bankBranch = bankBranch;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getSettleAmount() {
        return settleAmount;
    }

    public void setSettleAmount(String settleAmount) {
        this.settleAmount = settleAmount;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUpState() {
        return upState;
    }

    public void setUpState(String upState) {
        this.upState = upState;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getResponseStr() {
        return responseStr;
    }

    public void setResponseStr(String responseStr) {
        this.responseStr = responseStr;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSettleRemark() {
        return settleRemark;
    }

    public void setSettleRemark(String settleRemark) {
        this.settleRemark = settleRemark;
    }

    public String getSettleOutNo() {
        return settleOutNo;
    }

    public void setSettleOutNo(String settleOutNo) {
        this.settleOutNo = settleOutNo;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}