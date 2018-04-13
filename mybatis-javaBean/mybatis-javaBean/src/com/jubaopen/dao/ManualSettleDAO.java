package com.jubaopen.dao;

import com.jubaopen.bean.settle.ManualSettle;
import com.jubaopen.bean.settle.ManualSettleExample;
import java.util.List;

public interface ManualSettleDAO {
    int countByExample(ManualSettleExample example);

    int deleteByExample(ManualSettleExample example);

    int deleteByPrimaryKey(Integer id);

    Integer insert(ManualSettle record);

    Integer insertSelective(ManualSettle record);

    List<ManualSettle> selectByExample(ManualSettleExample example);

    ManualSettle selectByPrimaryKey(Integer id);

    int updateByExampleSelective(ManualSettle record, ManualSettleExample example);

    int updateByExample(ManualSettle record, ManualSettleExample example);

    int updateByPrimaryKeySelective(ManualSettle record);

    int updateByPrimaryKey(ManualSettle record);
}