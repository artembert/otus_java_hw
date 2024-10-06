package com.homework.jdbc.mapper;

import com.homework.crm.annotation.Id;
import com.homework.jdbc.mapper.exceptions.EntityClassInitializationException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private static final Logger logger = LoggerFactory.getLogger(EntityClassMetaDataImpl.class);
    private final Class<T> clazz;
    private final Constructor<T> constructor;
    private final List<Field> fields;
    private final List<Field> fieldsWithoutId;
    private final Field idField;

    public EntityClassMetaDataImpl(Class<T> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class is null");
        }
        try {
            this.clazz = clazz;
            this.constructor = clazz.getConstructor();
            this.fields = Arrays.stream(clazz.getDeclaredFields())
                    .sorted(Comparator.comparing(Field::getName))
                    .toList();
            this.fieldsWithoutId = this.fields.stream()
                    .filter(field -> !field.isAnnotationPresent(Id.class))
                    .toList();
            this.idField = this.fields.stream()
                    .filter(field -> field.isAnnotationPresent(Id.class))
                    .findFirst()
                    .orElseThrow(() -> new IllegalArgumentException("No @Id annotation in class"));
        } catch (Exception e) {
            logger.error("Entity class initialization exception", e);
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
        return this.idField;
    }

    @Override
    public List<Field> getAllFields() {
        return this.fields;
    }

    @Override
    public List<Field> getFieldsWithoutId() {
        return this.fieldsWithoutId;
    }
}
