package com.homework.jdbc.mapper;

public class EntitySQLMetaDataImpl<T> implements EntitySQLMetaData {
    private final EntityClassMetaData<T> entityClassMetaData;
    private String selectByIdQuery;

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
        return selectByIdQuery;
    }

    //    @Override
    //    public String getInsertSql();

    //    @Override
    //    public String getUpdateSql();
}
