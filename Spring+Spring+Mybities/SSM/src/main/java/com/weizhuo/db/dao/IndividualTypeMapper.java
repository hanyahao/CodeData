package com.weizhuo.db.dao;

import com.weizhuo.db.model.IndividualType;

public interface IndividualTypeMapper {
    int deleteByPrimaryKey(String typeCode);

    int insert(IndividualType record);

    int insertSelective(IndividualType record);

    IndividualType selectByPrimaryKey(String typeCode);

    int updateByPrimaryKeySelective(IndividualType record);

    int updateByPrimaryKey(IndividualType record);
}