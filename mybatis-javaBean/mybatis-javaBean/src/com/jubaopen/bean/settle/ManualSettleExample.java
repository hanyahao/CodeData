package com.jubaopen.bean.settle;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManualSettleExample extends PageInfo {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public ManualSettleExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    protected ManualSettleExample(ManualSettleExample example) {
        this.orderByClause = example.orderByClause;
        this.oredCriteria = example.oredCriteria;
        this.distinct = example.distinct;
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<String> criteriaWithoutValue;

        protected List<Map<String, Object>> criteriaWithSingleValue;

        protected List<Map<String, Object>> criteriaWithListValue;

        protected List<Map<String, Object>> criteriaWithBetweenValue;

        protected GeneratedCriteria() {
            super();
            criteriaWithoutValue = new ArrayList<String>();
            criteriaWithSingleValue = new ArrayList<Map<String, Object>>();
            criteriaWithListValue = new ArrayList<Map<String, Object>>();
            criteriaWithBetweenValue = new ArrayList<Map<String, Object>>();
        }

        public boolean isValid() {
            return criteriaWithoutValue.size() > 0
                || criteriaWithSingleValue.size() > 0
                || criteriaWithListValue.size() > 0
                || criteriaWithBetweenValue.size() > 0;
        }

        public List<String> getCriteriaWithoutValue() {
            return criteriaWithoutValue;
        }

        public List<Map<String, Object>> getCriteriaWithSingleValue() {
            return criteriaWithSingleValue;
        }

        public List<Map<String, Object>> getCriteriaWithListValue() {
            return criteriaWithListValue;
        }

        public List<Map<String, Object>> getCriteriaWithBetweenValue() {
            return criteriaWithBetweenValue;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteriaWithoutValue.add(condition);
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("value", value);
            criteriaWithSingleValue.add(map);
        }

        protected void addCriterion(String condition, List<? extends Object> values, String property) {
            if (values == null || values.size() == 0) {
                throw new RuntimeException("Value list for " + property + " cannot be null or empty");
            }
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", values);
            criteriaWithListValue.add(map);
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            List<Object> list = new ArrayList<Object>();
            list.add(value1);
            list.add(value2);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("condition", condition);
            map.put("values", list);
            criteriaWithBetweenValue.add(map);
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andCashChannelIsNull() {
            addCriterion("cash_channel is null");
            return (Criteria) this;
        }

        public Criteria andCashChannelIsNotNull() {
            addCriterion("cash_channel is not null");
            return (Criteria) this;
        }

        public Criteria andCashChannelEqualTo(String value) {
            addCriterion("cash_channel =", value, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelNotEqualTo(String value) {
            addCriterion("cash_channel <>", value, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelGreaterThan(String value) {
            addCriterion("cash_channel >", value, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelGreaterThanOrEqualTo(String value) {
            addCriterion("cash_channel >=", value, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelLessThan(String value) {
            addCriterion("cash_channel <", value, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelLessThanOrEqualTo(String value) {
            addCriterion("cash_channel <=", value, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelLike(String value) {
            addCriterion("cash_channel like", value, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelNotLike(String value) {
            addCriterion("cash_channel not like", value, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelIn(List<String> values) {
            addCriterion("cash_channel in", values, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelNotIn(List<String> values) {
            addCriterion("cash_channel not in", values, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelBetween(String value1, String value2) {
            addCriterion("cash_channel between", value1, value2, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andCashChannelNotBetween(String value1, String value2) {
            addCriterion("cash_channel not between", value1, value2, "cashChannel");
            return (Criteria) this;
        }

        public Criteria andUpCompanyIsNull() {
            addCriterion("up_company is null");
            return (Criteria) this;
        }

        public Criteria andUpCompanyIsNotNull() {
            addCriterion("up_company is not null");
            return (Criteria) this;
        }

        public Criteria andUpCompanyEqualTo(String value) {
            addCriterion("up_company =", value, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyNotEqualTo(String value) {
            addCriterion("up_company <>", value, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyGreaterThan(String value) {
            addCriterion("up_company >", value, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyGreaterThanOrEqualTo(String value) {
            addCriterion("up_company >=", value, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyLessThan(String value) {
            addCriterion("up_company <", value, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyLessThanOrEqualTo(String value) {
            addCriterion("up_company <=", value, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyLike(String value) {
            addCriterion("up_company like", value, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyNotLike(String value) {
            addCriterion("up_company not like", value, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyIn(List<String> values) {
            addCriterion("up_company in", values, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyNotIn(List<String> values) {
            addCriterion("up_company not in", values, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyBetween(String value1, String value2) {
            addCriterion("up_company between", value1, value2, "upCompany");
            return (Criteria) this;
        }

        public Criteria andUpCompanyNotBetween(String value1, String value2) {
            addCriterion("up_company not between", value1, value2, "upCompany");
            return (Criteria) this;
        }

        public Criteria andTransNoIsNull() {
            addCriterion("trans_no is null");
            return (Criteria) this;
        }

        public Criteria andTransNoIsNotNull() {
            addCriterion("trans_no is not null");
            return (Criteria) this;
        }

        public Criteria andTransNoEqualTo(String value) {
            addCriterion("trans_no =", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotEqualTo(String value) {
            addCriterion("trans_no <>", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoGreaterThan(String value) {
            addCriterion("trans_no >", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoGreaterThanOrEqualTo(String value) {
            addCriterion("trans_no >=", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLessThan(String value) {
            addCriterion("trans_no <", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLessThanOrEqualTo(String value) {
            addCriterion("trans_no <=", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoLike(String value) {
            addCriterion("trans_no like", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotLike(String value) {
            addCriterion("trans_no not like", value, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoIn(List<String> values) {
            addCriterion("trans_no in", values, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotIn(List<String> values) {
            addCriterion("trans_no not in", values, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoBetween(String value1, String value2) {
            addCriterion("trans_no between", value1, value2, "transNo");
            return (Criteria) this;
        }

        public Criteria andTransNoNotBetween(String value1, String value2) {
            addCriterion("trans_no not between", value1, value2, "transNo");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNull() {
            addCriterion("account_name is null");
            return (Criteria) this;
        }

        public Criteria andAccountNameIsNotNull() {
            addCriterion("account_name is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNameEqualTo(String value) {
            addCriterion("account_name =", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotEqualTo(String value) {
            addCriterion("account_name <>", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThan(String value) {
            addCriterion("account_name >", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameGreaterThanOrEqualTo(String value) {
            addCriterion("account_name >=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThan(String value) {
            addCriterion("account_name <", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLessThanOrEqualTo(String value) {
            addCriterion("account_name <=", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameLike(String value) {
            addCriterion("account_name like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotLike(String value) {
            addCriterion("account_name not like", value, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameIn(List<String> values) {
            addCriterion("account_name in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotIn(List<String> values) {
            addCriterion("account_name not in", values, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameBetween(String value1, String value2) {
            addCriterion("account_name between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andAccountNameNotBetween(String value1, String value2) {
            addCriterion("account_name not between", value1, value2, "accountName");
            return (Criteria) this;
        }

        public Criteria andBankMobileIsNull() {
            addCriterion("bank_mobile is null");
            return (Criteria) this;
        }

        public Criteria andBankMobileIsNotNull() {
            addCriterion("bank_mobile is not null");
            return (Criteria) this;
        }

        public Criteria andBankMobileEqualTo(String value) {
            addCriterion("bank_mobile =", value, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileNotEqualTo(String value) {
            addCriterion("bank_mobile <>", value, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileGreaterThan(String value) {
            addCriterion("bank_mobile >", value, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileGreaterThanOrEqualTo(String value) {
            addCriterion("bank_mobile >=", value, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileLessThan(String value) {
            addCriterion("bank_mobile <", value, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileLessThanOrEqualTo(String value) {
            addCriterion("bank_mobile <=", value, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileLike(String value) {
            addCriterion("bank_mobile like", value, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileNotLike(String value) {
            addCriterion("bank_mobile not like", value, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileIn(List<String> values) {
            addCriterion("bank_mobile in", values, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileNotIn(List<String> values) {
            addCriterion("bank_mobile not in", values, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileBetween(String value1, String value2) {
            addCriterion("bank_mobile between", value1, value2, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andBankMobileNotBetween(String value1, String value2) {
            addCriterion("bank_mobile not between", value1, value2, "bankMobile");
            return (Criteria) this;
        }

        public Criteria andAccountNumIsNull() {
            addCriterion("account_num is null");
            return (Criteria) this;
        }

        public Criteria andAccountNumIsNotNull() {
            addCriterion("account_num is not null");
            return (Criteria) this;
        }

        public Criteria andAccountNumEqualTo(String value) {
            addCriterion("account_num =", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotEqualTo(String value) {
            addCriterion("account_num <>", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumGreaterThan(String value) {
            addCriterion("account_num >", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumGreaterThanOrEqualTo(String value) {
            addCriterion("account_num >=", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumLessThan(String value) {
            addCriterion("account_num <", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumLessThanOrEqualTo(String value) {
            addCriterion("account_num <=", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumLike(String value) {
            addCriterion("account_num like", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotLike(String value) {
            addCriterion("account_num not like", value, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumIn(List<String> values) {
            addCriterion("account_num in", values, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotIn(List<String> values) {
            addCriterion("account_num not in", values, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumBetween(String value1, String value2) {
            addCriterion("account_num between", value1, value2, "accountNum");
            return (Criteria) this;
        }

        public Criteria andAccountNumNotBetween(String value1, String value2) {
            addCriterion("account_num not between", value1, value2, "accountNum");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNull() {
            addCriterion("bank_name is null");
            return (Criteria) this;
        }

        public Criteria andBankNameIsNotNull() {
            addCriterion("bank_name is not null");
            return (Criteria) this;
        }

        public Criteria andBankNameEqualTo(String value) {
            addCriterion("bank_name =", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotEqualTo(String value) {
            addCriterion("bank_name <>", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThan(String value) {
            addCriterion("bank_name >", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameGreaterThanOrEqualTo(String value) {
            addCriterion("bank_name >=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThan(String value) {
            addCriterion("bank_name <", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLessThanOrEqualTo(String value) {
            addCriterion("bank_name <=", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameLike(String value) {
            addCriterion("bank_name like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotLike(String value) {
            addCriterion("bank_name not like", value, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameIn(List<String> values) {
            addCriterion("bank_name in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotIn(List<String> values) {
            addCriterion("bank_name not in", values, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameBetween(String value1, String value2) {
            addCriterion("bank_name between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankNameNotBetween(String value1, String value2) {
            addCriterion("bank_name not between", value1, value2, "bankName");
            return (Criteria) this;
        }

        public Criteria andBankProvinceIsNull() {
            addCriterion("bank_province is null");
            return (Criteria) this;
        }

        public Criteria andBankProvinceIsNotNull() {
            addCriterion("bank_province is not null");
            return (Criteria) this;
        }

        public Criteria andBankProvinceEqualTo(String value) {
            addCriterion("bank_province =", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotEqualTo(String value) {
            addCriterion("bank_province <>", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceGreaterThan(String value) {
            addCriterion("bank_province >", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceGreaterThanOrEqualTo(String value) {
            addCriterion("bank_province >=", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLessThan(String value) {
            addCriterion("bank_province <", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLessThanOrEqualTo(String value) {
            addCriterion("bank_province <=", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceLike(String value) {
            addCriterion("bank_province like", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotLike(String value) {
            addCriterion("bank_province not like", value, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceIn(List<String> values) {
            addCriterion("bank_province in", values, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotIn(List<String> values) {
            addCriterion("bank_province not in", values, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceBetween(String value1, String value2) {
            addCriterion("bank_province between", value1, value2, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankProvinceNotBetween(String value1, String value2) {
            addCriterion("bank_province not between", value1, value2, "bankProvince");
            return (Criteria) this;
        }

        public Criteria andBankCityIsNull() {
            addCriterion("bank_city is null");
            return (Criteria) this;
        }

        public Criteria andBankCityIsNotNull() {
            addCriterion("bank_city is not null");
            return (Criteria) this;
        }

        public Criteria andBankCityEqualTo(String value) {
            addCriterion("bank_city =", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotEqualTo(String value) {
            addCriterion("bank_city <>", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityGreaterThan(String value) {
            addCriterion("bank_city >", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityGreaterThanOrEqualTo(String value) {
            addCriterion("bank_city >=", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityLessThan(String value) {
            addCriterion("bank_city <", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityLessThanOrEqualTo(String value) {
            addCriterion("bank_city <=", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityLike(String value) {
            addCriterion("bank_city like", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotLike(String value) {
            addCriterion("bank_city not like", value, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityIn(List<String> values) {
            addCriterion("bank_city in", values, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotIn(List<String> values) {
            addCriterion("bank_city not in", values, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityBetween(String value1, String value2) {
            addCriterion("bank_city between", value1, value2, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankCityNotBetween(String value1, String value2) {
            addCriterion("bank_city not between", value1, value2, "bankCity");
            return (Criteria) this;
        }

        public Criteria andBankBranchIsNull() {
            addCriterion("bank_branch is null");
            return (Criteria) this;
        }

        public Criteria andBankBranchIsNotNull() {
            addCriterion("bank_branch is not null");
            return (Criteria) this;
        }

        public Criteria andBankBranchEqualTo(String value) {
            addCriterion("bank_branch =", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotEqualTo(String value) {
            addCriterion("bank_branch <>", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchGreaterThan(String value) {
            addCriterion("bank_branch >", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchGreaterThanOrEqualTo(String value) {
            addCriterion("bank_branch >=", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchLessThan(String value) {
            addCriterion("bank_branch <", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchLessThanOrEqualTo(String value) {
            addCriterion("bank_branch <=", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchLike(String value) {
            addCriterion("bank_branch like", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotLike(String value) {
            addCriterion("bank_branch not like", value, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchIn(List<String> values) {
            addCriterion("bank_branch in", values, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotIn(List<String> values) {
            addCriterion("bank_branch not in", values, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchBetween(String value1, String value2) {
            addCriterion("bank_branch between", value1, value2, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andBankBranchNotBetween(String value1, String value2) {
            addCriterion("bank_branch not between", value1, value2, "bankBranch");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNull() {
            addCriterion("account_type is null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIsNotNull() {
            addCriterion("account_type is not null");
            return (Criteria) this;
        }

        public Criteria andAccountTypeEqualTo(String value) {
            addCriterion("account_type =", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotEqualTo(String value) {
            addCriterion("account_type <>", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThan(String value) {
            addCriterion("account_type >", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeGreaterThanOrEqualTo(String value) {
            addCriterion("account_type >=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThan(String value) {
            addCriterion("account_type <", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLessThanOrEqualTo(String value) {
            addCriterion("account_type <=", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeLike(String value) {
            addCriterion("account_type like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotLike(String value) {
            addCriterion("account_type not like", value, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeIn(List<String> values) {
            addCriterion("account_type in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotIn(List<String> values) {
            addCriterion("account_type not in", values, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeBetween(String value1, String value2) {
            addCriterion("account_type between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andAccountTypeNotBetween(String value1, String value2) {
            addCriterion("account_type not between", value1, value2, "accountType");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNull() {
            addCriterion("settle_amount is null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIsNotNull() {
            addCriterion("settle_amount is not null");
            return (Criteria) this;
        }

        public Criteria andSettleAmountEqualTo(String value) {
            addCriterion("settle_amount =", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotEqualTo(String value) {
            addCriterion("settle_amount <>", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThan(String value) {
            addCriterion("settle_amount >", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountGreaterThanOrEqualTo(String value) {
            addCriterion("settle_amount >=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThan(String value) {
            addCriterion("settle_amount <", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLessThanOrEqualTo(String value) {
            addCriterion("settle_amount <=", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountLike(String value) {
            addCriterion("settle_amount like", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotLike(String value) {
            addCriterion("settle_amount not like", value, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountIn(List<String> values) {
            addCriterion("settle_amount in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotIn(List<String> values) {
            addCriterion("settle_amount not in", values, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountBetween(String value1, String value2) {
            addCriterion("settle_amount between", value1, value2, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andSettleAmountNotBetween(String value1, String value2) {
            addCriterion("settle_amount not between", value1, value2, "settleAmount");
            return (Criteria) this;
        }

        public Criteria andStateIsNull() {
            addCriterion("`state` is null");
            return (Criteria) this;
        }

        public Criteria andStateIsNotNull() {
            addCriterion("`state` is not null");
            return (Criteria) this;
        }

        public Criteria andStateEqualTo(Integer value) {
            addCriterion("`state` =", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotEqualTo(Integer value) {
            addCriterion("`state` <>", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThan(Integer value) {
            addCriterion("`state` >", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateGreaterThanOrEqualTo(Integer value) {
            addCriterion("`state` >=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThan(Integer value) {
            addCriterion("`state` <", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateLessThanOrEqualTo(Integer value) {
            addCriterion("`state` <=", value, "state");
            return (Criteria) this;
        }

        public Criteria andStateIn(List<Integer> values) {
            addCriterion("`state` in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotIn(List<Integer> values) {
            addCriterion("`state` not in", values, "state");
            return (Criteria) this;
        }

        public Criteria andStateBetween(Integer value1, Integer value2) {
            addCriterion("`state` between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andStateNotBetween(Integer value1, Integer value2) {
            addCriterion("`state` not between", value1, value2, "state");
            return (Criteria) this;
        }

        public Criteria andUpStateIsNull() {
            addCriterion("up_state is null");
            return (Criteria) this;
        }

        public Criteria andUpStateIsNotNull() {
            addCriterion("up_state is not null");
            return (Criteria) this;
        }

        public Criteria andUpStateEqualTo(String value) {
            addCriterion("up_state =", value, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateNotEqualTo(String value) {
            addCriterion("up_state <>", value, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateGreaterThan(String value) {
            addCriterion("up_state >", value, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateGreaterThanOrEqualTo(String value) {
            addCriterion("up_state >=", value, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateLessThan(String value) {
            addCriterion("up_state <", value, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateLessThanOrEqualTo(String value) {
            addCriterion("up_state <=", value, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateLike(String value) {
            addCriterion("up_state like", value, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateNotLike(String value) {
            addCriterion("up_state not like", value, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateIn(List<String> values) {
            addCriterion("up_state in", values, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateNotIn(List<String> values) {
            addCriterion("up_state not in", values, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateBetween(String value1, String value2) {
            addCriterion("up_state between", value1, value2, "upState");
            return (Criteria) this;
        }

        public Criteria andUpStateNotBetween(String value1, String value2) {
            addCriterion("up_state not between", value1, value2, "upState");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdIsNull() {
            addCriterion("admin_user_id is null");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdIsNotNull() {
            addCriterion("admin_user_id is not null");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdEqualTo(Integer value) {
            addCriterion("admin_user_id =", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdNotEqualTo(Integer value) {
            addCriterion("admin_user_id <>", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdGreaterThan(Integer value) {
            addCriterion("admin_user_id >", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("admin_user_id >=", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdLessThan(Integer value) {
            addCriterion("admin_user_id <", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdLessThanOrEqualTo(Integer value) {
            addCriterion("admin_user_id <=", value, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdIn(List<Integer> values) {
            addCriterion("admin_user_id in", values, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdNotIn(List<Integer> values) {
            addCriterion("admin_user_id not in", values, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdBetween(Integer value1, Integer value2) {
            addCriterion("admin_user_id between", value1, value2, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andAdminUserIdNotBetween(Integer value1, Integer value2) {
            addCriterion("admin_user_id not between", value1, value2, "adminUserId");
            return (Criteria) this;
        }

        public Criteria andResponseStrIsNull() {
            addCriterion("response_str is null");
            return (Criteria) this;
        }

        public Criteria andResponseStrIsNotNull() {
            addCriterion("response_str is not null");
            return (Criteria) this;
        }

        public Criteria andResponseStrEqualTo(String value) {
            addCriterion("response_str =", value, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrNotEqualTo(String value) {
            addCriterion("response_str <>", value, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrGreaterThan(String value) {
            addCriterion("response_str >", value, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrGreaterThanOrEqualTo(String value) {
            addCriterion("response_str >=", value, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrLessThan(String value) {
            addCriterion("response_str <", value, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrLessThanOrEqualTo(String value) {
            addCriterion("response_str <=", value, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrLike(String value) {
            addCriterion("response_str like", value, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrNotLike(String value) {
            addCriterion("response_str not like", value, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrIn(List<String> values) {
            addCriterion("response_str in", values, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrNotIn(List<String> values) {
            addCriterion("response_str not in", values, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrBetween(String value1, String value2) {
            addCriterion("response_str between", value1, value2, "responseStr");
            return (Criteria) this;
        }

        public Criteria andResponseStrNotBetween(String value1, String value2) {
            addCriterion("response_str not between", value1, value2, "responseStr");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNull() {
            addCriterion("subject is null");
            return (Criteria) this;
        }

        public Criteria andSubjectIsNotNull() {
            addCriterion("subject is not null");
            return (Criteria) this;
        }

        public Criteria andSubjectEqualTo(String value) {
            addCriterion("subject =", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotEqualTo(String value) {
            addCriterion("subject <>", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThan(String value) {
            addCriterion("subject >", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectGreaterThanOrEqualTo(String value) {
            addCriterion("subject >=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThan(String value) {
            addCriterion("subject <", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLessThanOrEqualTo(String value) {
            addCriterion("subject <=", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectLike(String value) {
            addCriterion("subject like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotLike(String value) {
            addCriterion("subject not like", value, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectIn(List<String> values) {
            addCriterion("subject in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotIn(List<String> values) {
            addCriterion("subject not in", values, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectBetween(String value1, String value2) {
            addCriterion("subject between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andSubjectNotBetween(String value1, String value2) {
            addCriterion("subject not between", value1, value2, "subject");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNull() {
            addCriterion("remark is null");
            return (Criteria) this;
        }

        public Criteria andRemarkIsNotNull() {
            addCriterion("remark is not null");
            return (Criteria) this;
        }

        public Criteria andRemarkEqualTo(String value) {
            addCriterion("remark =", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotEqualTo(String value) {
            addCriterion("remark <>", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThan(String value) {
            addCriterion("remark >", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("remark >=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThan(String value) {
            addCriterion("remark <", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLessThanOrEqualTo(String value) {
            addCriterion("remark <=", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkLike(String value) {
            addCriterion("remark like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotLike(String value) {
            addCriterion("remark not like", value, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkIn(List<String> values) {
            addCriterion("remark in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotIn(List<String> values) {
            addCriterion("remark not in", values, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkBetween(String value1, String value2) {
            addCriterion("remark between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andRemarkNotBetween(String value1, String value2) {
            addCriterion("remark not between", value1, value2, "remark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkIsNull() {
            addCriterion("settle_remark is null");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkIsNotNull() {
            addCriterion("settle_remark is not null");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkEqualTo(String value) {
            addCriterion("settle_remark =", value, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkNotEqualTo(String value) {
            addCriterion("settle_remark <>", value, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkGreaterThan(String value) {
            addCriterion("settle_remark >", value, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkGreaterThanOrEqualTo(String value) {
            addCriterion("settle_remark >=", value, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkLessThan(String value) {
            addCriterion("settle_remark <", value, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkLessThanOrEqualTo(String value) {
            addCriterion("settle_remark <=", value, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkLike(String value) {
            addCriterion("settle_remark like", value, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkNotLike(String value) {
            addCriterion("settle_remark not like", value, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkIn(List<String> values) {
            addCriterion("settle_remark in", values, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkNotIn(List<String> values) {
            addCriterion("settle_remark not in", values, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkBetween(String value1, String value2) {
            addCriterion("settle_remark between", value1, value2, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleRemarkNotBetween(String value1, String value2) {
            addCriterion("settle_remark not between", value1, value2, "settleRemark");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoIsNull() {
            addCriterion("settle_out_no is null");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoIsNotNull() {
            addCriterion("settle_out_no is not null");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoEqualTo(String value) {
            addCriterion("settle_out_no =", value, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoNotEqualTo(String value) {
            addCriterion("settle_out_no <>", value, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoGreaterThan(String value) {
            addCriterion("settle_out_no >", value, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoGreaterThanOrEqualTo(String value) {
            addCriterion("settle_out_no >=", value, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoLessThan(String value) {
            addCriterion("settle_out_no <", value, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoLessThanOrEqualTo(String value) {
            addCriterion("settle_out_no <=", value, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoLike(String value) {
            addCriterion("settle_out_no like", value, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoNotLike(String value) {
            addCriterion("settle_out_no not like", value, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoIn(List<String> values) {
            addCriterion("settle_out_no in", values, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoNotIn(List<String> values) {
            addCriterion("settle_out_no not in", values, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoBetween(String value1, String value2) {
            addCriterion("settle_out_no between", value1, value2, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andSettleOutNoNotBetween(String value1, String value2) {
            addCriterion("settle_out_no not between", value1, value2, "settleOutNo");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNull() {
            addCriterion("create_time is null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIsNotNull() {
            addCriterion("create_time is not null");
            return (Criteria) this;
        }

        public Criteria andCreateTimeEqualTo(Date value) {
            addCriterion("create_time =", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotEqualTo(Date value) {
            addCriterion("create_time <>", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThan(Date value) {
            addCriterion("create_time >", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("create_time >=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThan(Date value) {
            addCriterion("create_time <", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeLessThanOrEqualTo(Date value) {
            addCriterion("create_time <=", value, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeIn(List<Date> values) {
            addCriterion("create_time in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotIn(List<Date> values) {
            addCriterion("create_time not in", values, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeBetween(Date value1, Date value2) {
            addCriterion("create_time between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andCreateTimeNotBetween(Date value1, Date value2) {
            addCriterion("create_time not between", value1, value2, "createTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNull() {
            addCriterion("update_time is null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIsNotNull() {
            addCriterion("update_time is not null");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeEqualTo(Date value) {
            addCriterion("update_time =", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotEqualTo(Date value) {
            addCriterion("update_time <>", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThan(Date value) {
            addCriterion("update_time >", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeGreaterThanOrEqualTo(Date value) {
            addCriterion("update_time >=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThan(Date value) {
            addCriterion("update_time <", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeLessThanOrEqualTo(Date value) {
            addCriterion("update_time <=", value, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeIn(List<Date> values) {
            addCriterion("update_time in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotIn(List<Date> values) {
            addCriterion("update_time not in", values, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeBetween(Date value1, Date value2) {
            addCriterion("update_time between", value1, value2, "updateTime");
            return (Criteria) this;
        }

        public Criteria andUpdateTimeNotBetween(Date value1, Date value2) {
            addCriterion("update_time not between", value1, value2, "updateTime");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }
}