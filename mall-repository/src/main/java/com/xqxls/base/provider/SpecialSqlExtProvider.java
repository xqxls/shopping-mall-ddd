package com.xqxls.base.provider;

import org.apache.ibatis.mapping.MappedStatement;
import tk.mybatis.mapper.entity.EntityColumn;
import tk.mybatis.mapper.mapperhelper.EntityHelper;
import tk.mybatis.mapper.mapperhelper.MapperHelper;
import tk.mybatis.mapper.mapperhelper.MapperTemplate;
import tk.mybatis.mapper.mapperhelper.SqlHelper;

import java.util.Set;

/**
 * @Description:
 * @Author: huzhuo
 * @Date: Created in 2023/4/26 19:46
 */
public class SpecialSqlExtProvider extends MapperTemplate {

    public SpecialSqlExtProvider (Class<?> mapperClass, MapperHelper mapperHelper) {
        super(mapperClass, mapperHelper);
    }

    /**
     *
     * @param ms insertBatch
     * @return 成功插入条数
     */
    public String insertBatch(MappedStatement ms){
        Class<?> entityClass = getEntityClass(ms);
        //开始拼sql
        StringBuilder sql = new StringBuilder();
        sql.append(SqlHelper.insertIntoTable(entityClass, super.tableName(entityClass)));
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" index=\"index\">");
        sql.append("<if test=\"index == 0\">");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        //获取全部列
        Set<EntityColumn> columnList = EntityHelper.getColumns(entityClass);
        for (EntityColumn column : columnList) {
            if (column.isInsertable()) {
                sql.append(SqlHelper.getIfNotNull("record", column, column.getColumn()+",", isNotEmpty()));
            }
        }
        sql.append("</trim>");
        sql.append("</if>");
        sql.append("</foreach>");
        sql.append(" VALUES ");
        sql.append("<foreach collection=\"list\" item=\"record\" separator=\",\" >");
        sql.append("<trim prefix=\"(\" suffix=\")\" suffixOverrides=\",\">");
        for (EntityColumn column : columnList) {
            if (column.isInsertable()) {
                sql.append(SqlHelper.getIfNotNull("record", column, column.getColumnHolder("record")+",", isNotEmpty()));
            }
        }
        sql.append("</trim>");
        sql.append("</foreach>");
        return sql.toString();

    }

    /**
     *
     * @param ms deleteByIdList
     * @return 成功删除条数
     */
    public String deleteByIdList(MappedStatement ms){
        Class<?> entityClass = getEntityClass(ms);
        //开始拼sql
        return SqlHelper.deleteFromTable(entityClass, super.tableName(entityClass)) +
                " where id in " +
                "<foreach item=\"record\" collection=\"list\" open=\"(\" separator=\",\" close=\")\">" +
                "#{record}" +
                "</foreach>";

    }

}
