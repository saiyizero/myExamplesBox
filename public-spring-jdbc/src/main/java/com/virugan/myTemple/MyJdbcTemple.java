package com.virugan.myTemple;

import com.virugan.context.myLogger;
import com.virugan.utils.myBeanUtils;
import com.virugan.utils.myDbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class MyJdbcTemple {
    @Autowired
    JdbcTemplate jdbcTemplate;

    public boolean insert(Object tableEntity){
        StringBuffer sql = new StringBuffer();
        StringBuffer val = new StringBuffer();
        Map<String, Object> map = myBeanUtils.getKeyAndValue(tableEntity);
        int size=map.size();
        Object args[]=new Object[size];

        sql.append("insert into ");
        sql.append(myDbUtils.toDbTableNames(tableEntity.getClass().getSimpleName()));
        sql.append(" (");
        int i=0;

        for(String key: map.keySet()){
            sql.append(key);
            if(i<size-1){
                sql.append(",");
                val.append("?,");
            }else {
                val.append("?");
            }
            args[i]=map.get(key);
            i=i+1;
        }

        sql.append(")  value (");
        sql.append(val.toString());
        sql.append(")");

        myLogger.debug("sql",sql);
        myLogger.debug("param",args);

        int temp = jdbcTemplate.update(sql.toString(), args);
        if(temp > 0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean update(Object Key,Object tableEntity){
        StringBuffer sql = new StringBuffer();
        StringBuffer val = new StringBuffer();
        Map<String, Object> KeyMap = myBeanUtils.getKeyAndValue(Key);
        Map<String, Object> EntityMap = myBeanUtils.getKeyAndValue(tableEntity);
        Object args[]=new Object[KeyMap.size()+EntityMap.size()];

        sql.append("update ");
        sql.append(myDbUtils.toDbTableNames(tableEntity.getClass().getSimpleName()));
        sql.append(" set ");
        int i=0;
        for(String key: EntityMap.keySet()){
            sql.append(key);
            sql.append("=?");
            if(i<EntityMap.size()){
                sql.append(",");

            }
            args[i]=EntityMap.get(key);
            i=i+1;
        }
        sql.append(" where ");
        for(String key: KeyMap.keySet()){
            sql.append(key);
            sql.append("=?");
            if(i<KeyMap.size()+EntityMap.size()){
                sql.append(" and ");
            }
            args[i]=KeyMap.get(key);
            i=i+1;
        }
        int temp = jdbcTemplate.update(sql.toString(), args);
        if(temp > 0) {
            return true;
        }else {
            return false;
        }
    }

    public boolean updateByPK(Object tableEntity){
        return false;
    }

    public Object selectOneToEntity(){
        return null;
    }

    public Object selectOneToMap(){
        return null;
    }

    public Object selectAllToEntity(){
        return null;
    }
    public Object selectAllToMap(){
        return null;
    }
}
