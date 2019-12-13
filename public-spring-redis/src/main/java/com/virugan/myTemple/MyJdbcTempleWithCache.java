package com.virugan.myTemple;

import com.virugan.context.myLogger;
import com.virugan.utils.myBeanUtils;
import com.virugan.utils.myDbUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
@CacheConfig(cacheNames = "MyJdbcTempleWithCache")
public class MyJdbcTempleWithCache {

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
            if(i<EntityMap.size()-1){
                sql.append(",");

            }
            args[i]=EntityMap.get(key);
            i=i+1;
        }
        sql.append(" where ");
        for(String key: KeyMap.keySet()){
            sql.append(key);
            sql.append("=?");
            if(i<KeyMap.size()+EntityMap.size()-1){
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

    @Cacheable(keyGenerator = "TableKeyGeneratorImpl")
    public <T> T selectByPrimaryKey(T tableEntity){
        StringBuffer sql = new StringBuffer();
        Map<String, Object> EntityMap = myBeanUtils.getKeyAndValue(tableEntity);
        sql.append("select * from ");
        sql.append(myDbUtils.toDbTableNames(tableEntity.getClass().getSimpleName()));
        List addList = myBeanUtils.getList();

        for(String key: EntityMap.keySet()){
            if(EntityMap.get(key)!=null){
                if(addList.size()<=0){
                    sql.append(" where ");
                    sql.append(key);
                    sql.append("=?");
                }else{
                    sql.append(" and ");
                    sql.append(key);
                    sql.append("=?");
                }
                addList.add(EntityMap.get(key));
            }
        }
        Object args[]=new Object[addList.size()];
        for(int i=0;i<addList.size();i++){
            args[i]=addList.get(i);
        }

        myLogger.debug("sql",sql);
        myLogger.debug("param",args);
        T result = (T)jdbcTemplate.queryForObject(sql.toString(), args, new BeanPropertyRowMapper(tableEntity.getClass()));
        return result;
    }


    @Cacheable(key = "'MyTablePKmap:'+#p0",unless = "#result==null||#result.isEmpty()")
    public List<String> getTablePrimaryKey(String tableName){
        StringBuilder sql = new StringBuilder();
        sql.append("select column_name from information_schema.key_column_usage where table_name=?");
        tableName=myDbUtils.toDbTableNames(tableName);
        myLogger.debug("sql",sql);
        myLogger.debug("param",tableName);
        List<String> list = jdbcTemplate.queryForList(sql.toString(), String.class, tableName);
        return list;
    }
}
