package com.homework.jdbc.mapper;

import com.homework.crm.annotation.Id;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EntityClassMetaDataImpl<T> implements EntityClassMetaData<T> {
    private final List<Field> fields = new ArrayList<>();
    private final Class<T> clazz;

    public EntityClassMetaDataImpl(Class<?> clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("Class is null");
        }
        this.clazz = (Class<T>) clazz;
        Field[] declaredFields = clazz.getDeclaredFields();
        if (declaredFields.length == 0) {
            throw new IllegalArgumentException("No properties in class");
        }
        this.fields.addAll(Arrays.asList(declaredFields));
    }

    //    Constructor<T> getConstructor();

    @Override
    public String getName() {
        return clazz.getSimpleName();
    }

    public Field getIdField() {
        return this.fields.stream()
                .filter(field -> field.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow();
    }

//    List<Field> getAllFields();

//    List<Field> getFieldsWithoutId();
}
