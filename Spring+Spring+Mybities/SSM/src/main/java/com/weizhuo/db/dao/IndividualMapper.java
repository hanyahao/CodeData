package com.weizhuo.db.dao;

import com.weizhuo.db.model.Individual;

public interface IndividualMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Individual record);

    int insertSelective(Individual record);

    Individual selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Individual record);

    int updateByPrimaryKey(Individual record);
}