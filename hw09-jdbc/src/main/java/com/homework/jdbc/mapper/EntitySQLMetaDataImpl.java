package com.homework.jdbc.mapper;

import org.slf4j.Logger;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {
    private static final Logger logger = org.slf4j.LoggerFactory.getLogger(EntitySQLMetaDataImpl.class);
    private final EntityClassMetaData<T> entityClassMetaData;
    private String selectByIdQuery;
    private String insertQuery;

    public EntitySQLMetaDataImpl(EntityClassMetaData<T> entityClassMetadata) {
        this.entityClassMetaData = entityClassMetadata;
    }

    //    @Override
    //    public String getSelectAllSql();

    @Override
    public String getSelectByIdSql() {
        if (selectByIdQuery == null) {
            var template = "select * from %s where %s = ?";
            var tableName = entityClassMetaData.getName();
            var fieldId = entityClassMetaData.getIdField().getName();
            selectByIdQuery = String.format(template, tableName, fieldId);
        }
        logger.info("selectByIdQuery: {}", selectByIdQuery);
        return selectByIdQuery;
    }

    @Override
    public String getInsertSql() {
        if (insertQuery == null) {
            var template = "insert into %s (%s) values (%s)";
            var tableName = entityClassMetaData.getName();
            var fieldsNames = entityClassMetaData.getFieldsWithoutId().stream()
                    .map(field -> field.getName().toLowerCase())
                    .toList();
            var fieldsValuesPlaceholders = entityClassMetaData.getFieldsWithoutId().stream()
                    .map(field -> "?")
                    .toList();
            CharSequence joiner = ", ";
            insertQuery = String.format(
                    template,
                    tableName,
                    String.join(joiner, fieldsNames),
                    String.join(joiner, fieldsValuesPlaceholders));
        }
        logger.info("insertQuery: {}", insertQuery);
        return insertQuery;
    }

    //    @Override
    //    public String getUpdateSql();
}
