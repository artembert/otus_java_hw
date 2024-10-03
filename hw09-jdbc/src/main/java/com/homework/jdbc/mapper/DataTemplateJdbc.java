package com.homework.jdbc.mapper;

import com.homework.core.repository.DataTemplate;
import com.homework.core.repository.DataTemplateException;
import com.homework.core.repository.executor.DbExecutor;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * Сохратяет объект в базу, читает объект из базы
 */
@SuppressWarnings("java:S1068")
public class DataTemplateJdbc<T> implements DataTemplate<T> {

    private final DbExecutor dbExecutor;
    private final EntitySQLMetaData entitySQLMetaData;
    private final EntityClassMetaData<T> entityClassMetaData;

    public DataTemplateJdbc(
            DbExecutor dbExecutor, EntitySQLMetaData entitySQLMetaData, EntityClassMetaData<T> entityClassMetaData) {
        this.dbExecutor = dbExecutor;
        this.entitySQLMetaData = entitySQLMetaData;
        this.entityClassMetaData = entityClassMetaData;
    }

    @Override
    public Optional<T> findById(Connection connection, long id) {
        return this.dbExecutor.executeSelect(
                connection, entitySQLMetaData.getSelectByIdSql(), List.of(id), resultSet -> {
                    try {
                        if (resultSet.next()) {
                            return createObject(resultSet);
                        }
                        return null;
                    } catch (Exception e) {
                        throw new DataTemplateException(e);
                    }
                });
    }

    @Override
    public List<T> findAll(Connection connection) {
        throw new UnsupportedOperationException();
    }

    @Override
    public long insert(Connection connection, T client) {
        throw new UnsupportedOperationException(); // TODO: To implement next
    }

    @Override
    public void update(Connection connection, T client) {
        throw new UnsupportedOperationException();
    }

    private T createObject(ResultSet resultSet) throws DataTemplateException {
        try {
            var instance = entityClassMetaData.getConstructor().newInstance();
            for (Field field : entityClassMetaData.getAllFields()) {
                var value = resultSet.getObject(field.getName(), field.getType());
                field.setAccessible(true);
                field.set(instance, value);
            }
            return instance;
        } catch (Exception e) {
            throw new DataTemplateException(e);
        }
    }
}
