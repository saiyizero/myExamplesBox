package com.virugan.myTemple;

import com.virugan.context.myLogger;
import com.virugan.interfaces.myDbHandle;
import com.virugan.myException.myCheckException;
import com.virugan.utils.myBeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
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
    @Autowired
    myDbHandle myDbHandle;
    
    public boolean insert(Object tableEntity){
        StringBuffer sql = new StringBuffer();
        StringBuffer val = new StringBuffer();
        Map<String, Object> map = myBeanUtils.getKeyAndValue(tableEntity);
        int size=map.size();
        Object args[]=new Object[size];

        sql.append("insert into ");
        sql.append(myDbHandle.toChangeTableNames(tableEntity.getClass().getSimpleName()));
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
        sql.append(myDbHandle.toChangeTableNames(tableEntity.getClass().getSimpleName()));
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
    public <T> T selectByPrimaryKey(T tableEntity) throws Exception {
        StringBuffer sql = new StringBuffer();
        Map<String, Object> EntityMap = myBeanUtils.getKeyAndValue(tableEntity);
        sql.append("select * from ");
        sql.append(myDbHandle.toChangeTableNames(tableEntity.getClass().getSimpleName()));
        List<String> list = getTablePrimaryKey(tableEntity.getClass().getSimpleName());
        if(list.size()<=0){
            throw myCheckException.isNotExistException(tableEntity.getClass().getSimpleName()+" the PK");
        }
        Object args[]=new Object[list.size()];
        int i=0;
        for(String key: list){
            if(EntityMap.containsKey(key)&& EntityMap.get(key)!=null){
                if(i<=0){
                    sql.append(" where ");
                    sql.append(key);
                    sql.append("=?");
                }else{
                    sql.append(" and ");
                    sql.append(key);
                    sql.append("=?");
                }
                args[i]=EntityMap.get(key);
                i++;
            }else{
                throw myCheckException.isNullException(tableEntity.getClass().getSimpleName()+":"+key);
            }
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
        tableName=myDbHandle.toChangeTableNames(tableName);
        myLogger.debug("sql",sql);
        myLogger.debug("param",tableName);
        List<String> list = jdbcTemplate.queryForList(sql.toString(), String.class, tableName);
        return list;
    }
}
