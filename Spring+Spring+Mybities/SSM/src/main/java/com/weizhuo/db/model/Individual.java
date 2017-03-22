package com.weizhuo.db.model;

import java.util.Date;

public class Individual {
    private Long id;

    private String individualTypeCode;

    private String fullName;

    private Boolean gender;

    private String cellPhone;

    private String email;

    private String idNo;

    private String contactAddress;

    private String emgencyContactor;

    private String contactorRelationship;

    private String emgencyCellPhone;

    private String emgencyContactAddr;

    private Long departmentId;

    private Long companyId;

    private Date createDatetime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIndividualTypeCode() {
        return individualTypeCode;
    }

    public void setIndividualTypeCode(String individualTypeCode) {
        this.individualTypeCode = individualTypeCode == null ? null : individualTypeCode.trim();
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName == null ? null : fullName.trim();
    }

    public Boolean getGender() {
        return gender;
    }

    public void setGender(Boolean gender) {
        this.gender = gender;
    }

    public String getCellPhone() {
        return cellPhone;
    }

    public void setCellPhone(String cellPhone) {
        this.cellPhone = cellPhone == null ? null : cellPhone.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo == null ? null : idNo.trim();
    }

    public String getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(String contactAddress) {
        this.contactAddress = contactAddress == null ? null : contactAddress.trim();
    }

    public String getEmgencyContactor() {
        return emgencyContactor;
    }

    public void setEmgencyContactor(String emgencyContactor) {
        this.emgencyContactor = emgencyContactor == null ? null : emgencyContactor.trim();
    }

    public String getContactorRelationship() {
        return contactorRelationship;
    }

    public void setContactorRelationship(String contactorRelationship) {
        this.contactorRelationship = contactorRelationship == null ? null : contactorRelationship.trim();
    }

    public String getEmgencyCellPhone() {
        return emgencyCellPhone;
    }

    public void setEmgencyCellPhone(String emgencyCellPhone) {
        this.emgencyCellPhone = emgencyCellPhone == null ? null : emgencyCellPhone.trim();
    }

    public String getEmgencyContactAddr() {
        return emgencyContactAddr;
    }

    public void setEmgencyContactAddr(String emgencyContactAddr) {
        this.emgencyContactAddr = emgencyContactAddr == null ? null : emgencyContactAddr.trim();
    }

    public Long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(Long departmentId) {
        this.departmentId = departmentId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Date getCreateDatetime() {
        return createDatetime;
    }

    public void setCreateDatetime(Date createDatetime) {
        this.createDatetime = createDatetime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Individual other = (Individual) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getIndividualTypeCode() == null ? other.getIndividualTypeCode() == null : this.getIndividualTypeCode().equals(other.getIndividualTypeCode()))
            && (this.getFullName() == null ? other.getFullName() == null : this.getFullName().equals(other.getFullName()))
            && (this.getGender() == null ? other.getGender() == null : this.getGender().equals(other.getGender()))
            && (this.getCellPhone() == null ? other.getCellPhone() == null : this.getCellPhone().equals(other.getCellPhone()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getIdNo() == null ? other.getIdNo() == null : this.getIdNo().equals(other.getIdNo()))
            && (this.getContactAddress() == null ? other.getContactAddress() == null : this.getContactAddress().equals(other.getContactAddress()))
            && (this.getEmgencyContactor() == null ? other.getEmgencyContactor() == null : this.getEmgencyContactor().equals(other.getEmgencyContactor()))
            && (this.getContactorRelationship() == null ? other.getContactorRelationship() == null : this.getContactorRelationship().equals(other.getContactorRelationship()))
            && (this.getEmgencyCellPhone() == null ? other.getEmgencyCellPhone() == null : this.getEmgencyCellPhone().equals(other.getEmgencyCellPhone()))
            && (this.getEmgencyContactAddr() == null ? other.getEmgencyContactAddr() == null : this.getEmgencyContactAddr().equals(other.getEmgencyContactAddr()))
            && (this.getDepartmentId() == null ? other.getDepartmentId() == null : this.getDepartmentId().equals(other.getDepartmentId()))
            && (this.getCompanyId() == null ? other.getCompanyId() == null : this.getCompanyId().equals(other.getCompanyId()))
            && (this.getCreateDatetime() == null ? other.getCreateDatetime() == null : this.getCreateDatetime().equals(other.getCreateDatetime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getIndividualTypeCode() == null) ? 0 : getIndividualTypeCode().hashCode());
        result = prime * result + ((getFullName() == null) ? 0 : getFullName().hashCode());
        result = prime * result + ((getGender() == null) ? 0 : getGender().hashCode());
        result = prime * result + ((getCellPhone() == null) ? 0 : getCellPhone().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getIdNo() == null) ? 0 : getIdNo().hashCode());
        result = prime * result + ((getContactAddress() == null) ? 0 : getContactAddress().hashCode());
        result = prime * result + ((getEmgencyContactor() == null) ? 0 : getEmgencyContactor().hashCode());
        result = prime * result + ((getContactorRelationship() == null) ? 0 : getContactorRelationship().hashCode());
        result = prime * result + ((getEmgencyCellPhone() == null) ? 0 : getEmgencyCellPhone().hashCode());
        result = prime * result + ((getEmgencyContactAddr() == null) ? 0 : getEmgencyContactAddr().hashCode());
        result = prime * result + ((getDepartmentId() == null) ? 0 : getDepartmentId().hashCode());
        result = prime * result + ((getCompanyId() == null) ? 0 : getCompanyId().hashCode());
        result = prime * result + ((getCreateDatetime() == null) ? 0 : getCreateDatetime().hashCode());
        return result;
    }
}