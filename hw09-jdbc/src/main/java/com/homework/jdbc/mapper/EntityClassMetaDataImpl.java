package com.homework.jdbc.mapper;

import com.homework.crm.annotation.Id;

import java.lang.reflect.Field;
import java.util.Arrays;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final Class<T> clazz;

    public EntityClassMetaDataImpl(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class is null");
        }
        this.clazz = (Class<T>) clazz;
    }

    //    Constructor<T> getConstructor();

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

    //    List<Field> getAllFields();

    //    List<Field> getFieldsWithoutId();
}
