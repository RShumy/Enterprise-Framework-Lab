package ro.ubb.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import ro.ubb.additional.GenericReflect;
import ro.ubb.domain.BaseEntity;
import ro.ubb.validators.Validator;
import ro.ubb.validators.ValidatorException;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.sql.Types;

import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

public class JDBC_Repository <ID,T extends BaseEntity<ID>> implements Repository<ID,T>{

    private Class entityClass;
    private Validator validator;

    public JDBC_Repository(Class entityClass) {
        this.entityClass = entityClass;
        try {
        this.validator = (Validator<T>) Class.forName("ro.ubb.validators." + entityClass.getSimpleName() + "Validator").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();}
    }

    public JDBC_Repository() {}

    @Autowired
    private JdbcOperations jdbcOperations;


    @Override
    public Optional<T> save(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("ID must not be null");
        }

//        validator.validate(entity);

        String entityString = "\"" + entity.getClass().getSimpleName().toLowerCase(Locale.ROOT) + "\"";

        var sql = "insert into " + entityString + "("+GenericReflect.stringFieldList(entity).getFirst()+") values (" + GenericReflect.stringFieldList(entity).getSecond() + ")";

        jdbcOperations.update(sql, GenericReflect.getSQLValues(entity), GenericReflect.getSQLTypes(entity));
        return Optional.ofNullable(entity);
    }

    @Override
    public Optional<T> findOne(ID id) {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        var entityString = "\"" + entityClass.getSimpleName().toLowerCase()+ "\"";
        var sql = "select * from " + entityString + " where " + "\"" + "idEntity" + "\"" + "= " + id;
        return jdbcOperations.query(sql,
                (resultSet) -> {

                    T finalEntity = null;
                    try {
                        while (resultSet.next()) {
                            finalEntity = (T) entityClass.getConstructor().newInstance();
                            AtomicReference<T> entity = new AtomicReference<>(finalEntity);
                            finalEntity.setIdEntity((ID) GenericReflect.getColumnValueFromResultSet(id.getClass(), resultSet, "idEntity"));
                            entity.set(finalEntity);
                            GenericReflect.getEntityFieldsList(finalEntity.getClass()).forEach(field -> {
                                field.setAccessible(true);
                                try {
                                    field.set(entity.get(), GenericReflect.getColumnValueFromResultSet(field.getType(), resultSet, field.getName()));
                                } catch (IllegalAccessException | SQLException e) {
                                    e.printStackTrace();
                                }
                            });
                        }
                    }
                    catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
                        {e.printStackTrace();}
                    return Optional.ofNullable(finalEntity);
                });
    }

    @Override
    public Iterable<T> findAll() {

        ID id = (ID) ((Number) 0);
        var entityString = "\"" + entityClass.getSimpleName().toLowerCase(Locale.ROOT) + "\"";
        var sql = "select * from " + entityString;

        return jdbcOperations.query(sql,
                (resultSet, index) -> {
                    T finalEntity = null;
                try {
                    finalEntity = (T) entityClass.getConstructor().newInstance();
                    AtomicReference<T> entity = new AtomicReference<>(finalEntity);
                    finalEntity.setIdEntity((ID) GenericReflect.getColumnValueFromResultSet(id.getClass(), resultSet, "idEntity"));
                    entity.set(finalEntity);
                    GenericReflect.getEntityFieldsList(finalEntity.getClass()).forEach(field -> {
                    field.setAccessible(true);
                        try {
                            field.set(entity.get(), GenericReflect.getColumnValueFromResultSet(field.getType(), resultSet, field.getName()));
                        } catch (IllegalAccessException | SQLException e) {
                            e.printStackTrace();
                        }
                    });
                }
                catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e)
                {e.printStackTrace();}
                validator.validate(finalEntity);
                return finalEntity;
        });
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        if (entity == null) {
            throw new IllegalArgumentException("ID must not be null");
        }

//        validator.validate(entity);
        String entityString = "\"" + entity.getClass().getSimpleName().toLowerCase(Locale.ROOT) + "\"";
        var sql = "update " + entityString + " set " + getStatementForUpdate(entity) + "where "+ "\"" + "idEntity" + "\"=" + entity.getIdEntity();

        jdbcOperations.update(sql, GenericReflect.getSQLValues(entity), GenericReflect.getSQLTypes(entity));
        return Optional.ofNullable(entity);
    }

    public String getStatementForUpdate(T entity){
        AtomicReference<String> statement = new AtomicReference<>("");
        GenericReflect.getEntityFieldsList(entity.getClass()).forEach(field -> {
            statement.set(statement + "\"" + field.getName()  + "\"" + " =?,");
//            statement.set(statement + "\"" + field.getName()  + "\"" + "=" + GenericReflect.getStringFieldValueForSQL(entity,field) + ",");
        });
        return statement.get().replaceAll(",$"," ");
    }

    @Override
    public Optional<T> delete(ID id) throws ValidatorException {
        if (id == null) {
            throw new IllegalArgumentException("ID must not be null");
        }
        var entityString = "\"" + entityClass.getSimpleName().toLowerCase(Locale.ROOT) + "\"";
        var sql = "delete from " + entityString + " where " + "\"" + "idEntity" + "\"" + "=" + id;
        jdbcOperations.update(sql);
        return Optional.empty();
    }
}
