package com.homework.jdbc.mapper;

import java.util.List;
import org.slf4j.Logger;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(EntitySQLMetaDataImpl.class);
    private final EntityClassMetaData<T> entityClassMetaData;
    private String selectByIdQuery;
    private String insertQuery;
    private String selectAllQuery;
    private String updateSql;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetadata) {
        this.entityClassMetaData = entityClassMetadata;
    }

    @Override
    public String getSelectAllSql() {
        if (selectAllQuery == null) {
            var template = "select %s from %s";
            var tableName = getTableName();
            var fieldsNames = entityClassMetaData.getAllFields().stream()
                    .map(field -> field.getName().toLowerCase())
                    .toList();
            selectAllQuery = String.format(template, joinFields(fieldsNames), tableName);
        }
        logger.info("selectAllQuery: {}", selectAllQuery);
        return selectAllQuery;
    }

    @Override
    public String getSelectByIdSql() {
        if (selectByIdQuery == null) {
            var template = "select * from %s where %s = ?";
            var tableName = getTableName();
            var fieldId = getIdFieldName();
            selectByIdQuery = String.format(template, tableName, fieldId);
        }
        logger.info("selectByIdQuery: {}", selectByIdQuery);
        return selectByIdQuery;
    }

    @Override
    public String getInsertSql() {
        if (insertQuery == null) {
            var template = "insert into %s (%s) values (%s)";
            var tableName = getTableName();
            var fieldsNames = entityClassMetaData.getFieldsWithoutId().stream()
                    .map(field -> field.getName().toLowerCase())
                    .toList();
            var fieldsValuesPlaceholders =
                    fieldsNames.stream().map(field -> "?").toList();
            insertQuery =
                    String.format(template, tableName, joinFields(fieldsNames), joinFields(fieldsValuesPlaceholders));
        }
        logger.info("insertQuery: {}", insertQuery);
        return insertQuery;
    }

    @Override
    public String getUpdateSql() {
        if (updateSql == null) {
            var template = "update %s set %s where %s = ?";
            var tableName = getTableName();
            var fieldId = getIdFieldName();
            var fieldsNames = entityClassMetaData.getFieldsWithoutId().stream()
                    .map(field -> field.getName().toLowerCase() + " = ?")
                    .toList();
            updateSql = String.format(template, tableName, joinFields(fieldsNames), fieldId);
        }
        logger.info("updateSql: {}", updateSql);
        return updateSql;
    }

    private String getTableName() {
        return entityClassMetaData.getName().toLowerCase();
    }

    private String getIdFieldName() {
        return entityClassMetaData.getIdField().getName().toLowerCase();
    }

    private String joinFields(List<String> fields) {
        CharSequence joiner = ", ";
        return String.join(joiner, fields);
    }
}
