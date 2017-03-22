package com.weizhuo.db.dao;

import com.weizhuo.db.model.SupportExceptionLog;

public interface SupportExceptionLogMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SupportExceptionLog record);

    int insertSelective(SupportExceptionLog record);

    SupportExceptionLog selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(SupportExceptionLog record);

    int updateByPrimaryKey(SupportExceptionLog record);
}