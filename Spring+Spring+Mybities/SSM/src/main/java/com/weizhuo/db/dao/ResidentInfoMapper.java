package com.weizhuo.db.dao;

import com.weizhuo.db.model.ResidentInfo;

public interface ResidentInfoMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ResidentInfo record);

    int insertSelective(ResidentInfo record);

    ResidentInfo selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ResidentInfo record);

    int updateByPrimaryKey(ResidentInfo record);
}