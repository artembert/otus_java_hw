package com.homework.jdbc.mapper;

import com.homework.crm.annotation.Id;
import com.homework.jdbc.mapper.exceptions.EntityClassInitializationException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private static final Logger logger = LoggerFactory.getLogger(EntityClassMetaDataImpl.class);
    private final Class<T> clazz;
    private final Constructor<T> constructor;

    public EntityClassMetaDataImpl(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class is null");
        }
        try {
            this.clazz = (Class<T>) clazz;
            this.constructor = clazz.getConstructor();

        } catch (Exception e) {
            logger.error("Entity class initialization exception");
            throw new EntityClassInitializationException("Entity class initialization exception", e);
        }
    }

    @Override
    public Constructor<T> getConstructor() {
        return this.constructor;
    }

    @Override
    public String getName() {
        return clazz.getSimpleName();
    }

    public Field getIdField() {
        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields.length == 0) {
            throw new IllegalArgumentException("No properties in class");
        }
        return Arrays.stream(declaredFields)
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No @Id annotation in class"));
    }

    @Override
    public List<Field> getAllFields() {
        return List.of();
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return List.of();
    }
}
