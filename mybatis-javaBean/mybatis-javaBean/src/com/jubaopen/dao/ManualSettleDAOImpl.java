package com.jubaopen.dao;

import com.jubaopen.bean.settle.ManualSettle;
import com.jubaopen.bean.settle.ManualSettleExample;
import java.util.List;
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;

public class ManualSettleDAOImpl extends SqlMapClientDaoSupport implements ManualSettleDAO {

    public ManualSettleDAOImpl() {
        super();
    }

    public int countByExample(ManualSettleExample example) {
        Integer count = (Integer)  getSqlMapClientTemplate().queryForObject("manual_settle.countByExample", example);
        return count;
    }

    public int deleteByExample(ManualSettleExample example) {
        int rows = getSqlMapClientTemplate().delete("manual_settle.deleteByExample", example);
        return rows;
    }

    public int deleteByPrimaryKey(Integer id) {
        ManualSettle _key = new ManualSettle();
        _key.setId(id);
        int rows = getSqlMapClientTemplate().delete("manual_settle.deleteByPrimaryKey", _key);
        return rows;
    }

    public Integer insert(ManualSettle record) {
        Object newKey = getSqlMapClientTemplate().insert("manual_settle.insert", record);
        return (Integer) newKey;
    }

    public Integer insertSelective(ManualSettle record) {
        Object newKey = getSqlMapClientTemplate().insert("manual_settle.insertSelective", record);
        return (Integer) newKey;
    }

    @SuppressWarnings("unchecked")
    public List<ManualSettle> selectByExample(ManualSettleExample example) {
        List<ManualSettle> list = getSqlMapClientTemplate().queryForList("manual_settle.selectByExample", example);
        return list;
    }

    public ManualSettle selectByPrimaryKey(Integer id) {
        ManualSettle _key = new ManualSettle();
        _key.setId(id);
        ManualSettle record = (ManualSettle) getSqlMapClientTemplate().queryForObject("manual_settle.selectByPrimaryKey", _key);
        return record;
    }

    public int updateByExampleSelective(ManualSettle record, ManualSettleExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("manual_settle.updateByExampleSelective", parms);
        return rows;
    }

    public int updateByExample(ManualSettle record, ManualSettleExample example) {
        UpdateByExampleParms parms = new UpdateByExampleParms(record, example);
        int rows = getSqlMapClientTemplate().update("manual_settle.updateByExample", parms);
        return rows;
    }

    public int updateByPrimaryKeySelective(ManualSettle record) {
        int rows = getSqlMapClientTemplate().update("manual_settle.updateByPrimaryKeySelective", record);
        return rows;
    }

    public int updateByPrimaryKey(ManualSettle record) {
        int rows = getSqlMapClientTemplate().update("manual_settle.updateByPrimaryKey", record);
        return rows;
    }

    protected static class UpdateByExampleParms extends ManualSettleExample {
        private Object record;

        public UpdateByExampleParms(Object record, ManualSettleExample example) {
            super(example);
            this.record = record;
        }

        public Object getRecord() {
            return record;
        }
    }
}