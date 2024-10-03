package com.homework.jdbc.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class EntitySQLMetaDataImplTest {

    private EntityClassMetaData<?> entityClassMetaData;
    private EntitySQLMetaDataImpl<?> entitySQLMetaData;

    @BeforeEach
    void setUp() {
        entityClassMetaData = Mockito.mock(EntityClassMetaData.class);
        entitySQLMetaData = new EntitySQLMetaDataImpl<>(entityClassMetaData);
    }

    @Test
    @DisplayName("getSelectByIdSql generates correct query")
    void getSelectByIdSql_generatesCorrectQuery() {
        when(entityClassMetaData.getName()).thenReturn("test_table");
        Field idField = Mockito.mock(Field.class);
        when(entityClassMetaData.getIdField()).thenReturn(idField);
        when(idField.getName()).thenReturn("uniqueid");

        String expectedQuery = "select * from test_table where uniqueid = ?";
        String actualQuery = entitySQLMetaData.getSelectByIdSql();

        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    @DisplayName("getInsertSql generates correct query")
    void getInsertSql_generatesCorrectQuery() {
        var fields = MockTwoFieldsObject.class.getDeclaredFields();
        when(entityClassMetaData.getName()).thenReturn("test_table");
        when(entityClassMetaData.getFieldsWithoutId()).thenReturn(List.of(fields));

        String expectedQuery = "insert into test_table (name, age) values (?, ?)";
        String actualQuery = entitySQLMetaData.getInsertSql();

        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    @DisplayName("getSelectAllSql generates correct query")
    void getSelectAllSql_generatesCorrectQuery() {
        var fields = Arrays.stream(MockTwoFieldsObject.class.getDeclaredFields())
                .sorted(Comparator.comparing(Field::getName))
                .toList();
        when(entityClassMetaData.getName()).thenReturn("test_table");
        when(entityClassMetaData.getAllFields()).thenReturn(fields);

        String expectedQuery = "select age, name from test_table";
        String actualQuery = entitySQLMetaData.getSelectAllSql();

        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    @DisplayName("getUpdateSql generates correct query")
    void getUpdateSql_generatesCorrectQuery() {
        var fields = MockTwoFieldsObject.class.getDeclaredFields();
        Field idField = Mockito.mock(Field.class);
        when(entityClassMetaData.getIdField()).thenReturn(idField);
        when(entityClassMetaData.getName()).thenReturn("test_table");
        when(entityClassMetaData.getFieldsWithoutId()).thenReturn(List.of(fields));
        when(idField.getName()).thenReturn("uniqueid");

        String expectedQuery = "update test_table set name = ?, age = ? where uniqueid = ?";
        String actualQuery = entitySQLMetaData.getUpdateSql();

        assertEquals(expectedQuery, actualQuery);
    }

    @Test
    @DisplayName("getSelectByIdSql caches query")
    void getSelectByIdSql_cachesQuery() {
        when(entityClassMetaData.getName()).thenReturn("test_table");
        Field idField = Mockito.mock(Field.class);
        when(entityClassMetaData.getIdField()).thenReturn(idField);
        when(idField.getName()).thenReturn("uniqueid");

        String firstQuery = entitySQLMetaData.getSelectByIdSql();
        String secondQuery = entitySQLMetaData.getSelectByIdSql();

        assertEquals(firstQuery, secondQuery);
        verify(entityClassMetaData, times(1)).getIdField();
    }

    @Test
    @DisplayName("getInsertSql caches query")
    void getInsertSql_cachesQuery() {
        var fields = MockTwoFieldsObject.class.getDeclaredFields();
        when(entityClassMetaData.getName()).thenReturn("test_table");
        when(entityClassMetaData.getFieldsWithoutId()).thenReturn(List.of(fields));

        String firstQuery = entitySQLMetaData.getInsertSql();
        String secondQuery = entitySQLMetaData.getInsertSql();

        assertEquals(firstQuery, secondQuery);
        verify(entityClassMetaData, times(1)).getName();
        verify(entityClassMetaData, times(1)).getFieldsWithoutId();
    }

    @Test
    @DisplayName("getSelectAllSql caches query")
    void getSelectAllSql_cachesQuery() {
        var fields = Arrays.stream(MockTwoFieldsObject.class.getDeclaredFields())
                .sorted(Comparator.comparing(Field::getName))
                .toList();
        when(entityClassMetaData.getName()).thenReturn("test_table");
        when(entityClassMetaData.getAllFields()).thenReturn(fields);

        String firstQuery = entitySQLMetaData.getSelectAllSql();
        String secondQuery = entitySQLMetaData.getSelectAllSql();

        verify(entityClassMetaData, times(1)).getName();
        verify(entityClassMetaData, times(1)).getAllFields();
        assertEquals(firstQuery, secondQuery);
    }

    @Test
    @DisplayName("getUpdateSql caches query")
    void getUpdateSql_cachesQuery() {
        var fields = MockTwoFieldsObject.class.getDeclaredFields();
        Field idField = Mockito.mock(Field.class);
        when(entityClassMetaData.getIdField()).thenReturn(idField);
        when(entityClassMetaData.getName()).thenReturn("test_table");
        when(entityClassMetaData.getFieldsWithoutId()).thenReturn(List.of(fields));
        when(idField.getName()).thenReturn("uniqueid");

        String firstQuery = entitySQLMetaData.getUpdateSql();
        String secondQuery = entitySQLMetaData.getUpdateSql();

        verify(entityClassMetaData, times(1)).getName();
        verify(entityClassMetaData, times(1)).getIdField();
        verify(entityClassMetaData, times(1)).getFieldsWithoutId();
        assertEquals(firstQuery, secondQuery);
    }

    private class MockTwoFieldsObject {
        private final String name;
        private final int age;

        MockTwoFieldsObject(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
