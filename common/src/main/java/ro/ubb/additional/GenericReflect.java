package ro.ubb.additional;

import ro.ubb.domain.BaseEntity;
import ro.ubb.domain.Specialty;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class GenericReflect {

    public static Object parseTypeHelper(Class<?> type, String valueFromFile){
        // Pentru primitive
        if (type == char.class){ return valueFromFile.charAt(0); }

        if (type == boolean.class){ return Boolean.parseBoolean(valueFromFile); }

        if (type == short.class){ return Short.parseShort(valueFromFile); }
        if (type == byte.class){ return Byte.parseByte(valueFromFile); }
        if (type == long.class){ return Long.parseLong(valueFromFile); }
        if (type == int.class){ return Integer.parseInt(valueFromFile); }
        if (type == float.class){ return Float.parseFloat(valueFromFile); }
        if (type == double.class){ return Double.parseDouble(valueFromFile); }

        // Pentru Clase wrapper
        if (type == Character.class){ return valueFromFile.charAt(0); }

        if (type == Boolean.class){ return Boolean.parseBoolean(valueFromFile); }

        if (type == Short.class){ return Short.parseShort(valueFromFile); }
        if (type == Byte.class){ return Byte.parseByte(valueFromFile); }
        if (type == Long.class){ return Long.parseLong(valueFromFile); }
        if (type == Integer.class){ return Integer.parseInt(valueFromFile); }
        if (type == Float.class){ return Float.parseFloat(valueFromFile); }
        if (type == Double.class){ return Double.parseDouble(valueFromFile); }

        //Trebuie sa fim atenti aici
        if (type.getSimpleName().matches("Specialty") && type.isEnum()){ return Specialty.valueOf(valueFromFile); }

        if (type == LocalDate.class){ return DateTimeParser.parseDate(valueFromFile);}
        if (type == LocalTime.class){ return DateTimeParser.parseTime(valueFromFile);}
        return valueFromFile;
    }

    public static List<Field> getEntityFieldsList(Class<?> entityClass){
        List<Field> fieldsList = new ArrayList<>();
        if (!(entityClass == BaseEntity.class)) {
            fieldsList.addAll(List.of(entityClass.getDeclaredFields()));
            fieldsList.addAll(getEntityFieldsList(entityClass.getSuperclass()));
        }
        return fieldsList;
    }



    public static Class getEntityClassFromString(String fileLine){
        Pattern classPattern = Pattern.compile("(^[A-Z][a-z]{1,}\\{)");
        Matcher classMatcher = classPattern.matcher(fileLine);
        classMatcher.find();
        String theClass = classMatcher.group().replace("{", "");
        try {
            Class classEntity = Class.forName("ro.ubb.domain." + theClass);
            return classEntity;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

/// Remoting Protocols for Individual Parameters
    //:TODO Figure this shit out
    public static String createStringFromParameter(Object param){
        String paramString="";
        Class<?> paramClass = param.getClass();

        paramString = paramClass.getName()+"=`"+param+"`~";
        return paramString;
    }

    public static Object getParameterFromString(String stringParams){
        Map<Class,String> paramsMap;
        Pattern valuePattern = Pattern.compile("(`[^`]{1,}`)");
        Matcher valueMatcher = valuePattern.matcher(stringParams);
        return new Object();
    }


    //---------------------------------------------------------------------------
    // METHODS FOR FILE REPOSITORY - or String Parsers for entities

    public static Object getEntityInstanceFromString(String fileLine){
        try {
            Object entityToSave = getEntityClassFromString(fileLine).newInstance();
            return entityToSave;
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getEntityFromString(Object entity, String fileLine){
        Map<String, String> matchedFieldNames = getFieldNamesAndValuesMapFromString(fileLine);

        entity = getEntityInstanceFromString(fileLine);
        setEntityValuesFromString(entity, matchedFieldNames);
        return entity;
    }

    public static Object getEntityFromStringWithID(Object entity, String fileLine){
        Map<String, String> matchedFieldNames = getFieldNamesAndValuesMapFromString(fileLine);
        entity = getEntityInstanceFromString(fileLine);
        Integer id = 0;
        GenericReflect.setIdEntityFromString(entity,id,matchedFieldNames);
        setEntityValuesFromString(entity, matchedFieldNames);
        return entity;
    }


    public static List<?> getEntityListFromString(String fileLine){
        Pattern valuePattern = Pattern.compile("(~[^~]{1,}~)");
        Matcher valueMatcher = valuePattern.matcher(fileLine);
        String entityString = "";

        List<Object> entityList = new ArrayList<>();
        while (valueMatcher.find()){
            entityString = valueMatcher.group().replace("~", "");
            entityList.add(getEntityFromStringWithID(getEntityInstanceFromString(entityString),entityString));
        }
        return entityList;
    }

    public static String createStringFromEntityList(List<? extends BaseEntity> entityList){
        AtomicReference<String> stringEntityList = new AtomicReference("");
        entityList.forEach(entity -> {
            stringEntityList.set(stringEntityList.get() + "~" + createStringFromEntity(entity) + "~");
        });
        return stringEntityList.get();
    }

    public static Class getIdClassFromString(String fileLine){
        Pattern idTypePattern = Pattern.compile("(```.{1,}```)");
        Matcher idTypeMatch = idTypePattern.matcher(fileLine);
        idTypeMatch.find();
        String idTypeString = idTypeMatch.group().replace("```","");
        try {
            Class idClass = Class.forName(idTypeString);
            return idClass;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> getFieldNamesAndValuesMapFromString(String fileLine){
        Map<String, String> matchedFieldNames = new LinkedHashMap<>();
        Pattern fieldNamePattern = Pattern.compile("(\\s[A-Za-z]{1,}=`)");
        Matcher fieldMatcher = fieldNamePattern.matcher(fileLine);

        Pattern valuePattern = Pattern.compile("(`[^`]{1,}`)");
        Matcher valueMatcher = valuePattern.matcher(fileLine);
        String fieldName;
        String value;

        /// Placing field and values in a Map
        while (fieldMatcher.find() && valueMatcher.find()) {
            fieldName = fieldMatcher.group().trim().replace("=`", "");
            value = valueMatcher.group().replace("`", "");
            matchedFieldNames.putIfAbsent(fieldName, value);
        }
        return matchedFieldNames;
    }

//    public static Type getIDType(Object id){
//        List<Field> fields = List.of(id.getClass().getDeclaredFields());
//        return fields.get(0).getType();
//    }

    public static void setIdEntityFromString(Object entity, Object id, Map<String, String> matchedFieldNames){
        Field[] superClassFields = entity.getClass().getSuperclass().getFields();
        try {
            //setting the idObject with its Type
            id = GenericReflect.parseTypeHelper(id.getClass(),
                    matchedFieldNames.get(superClassFields[0].getName()));
            superClassFields[0].setAccessible(true);
            superClassFields[0].set( entity, id);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void setEntityValuesFromString(Object entity, Map<String, String> matchedFieldNames){
        List<Field> fieldsList = getEntityFieldsList(entity.getClass());
        fieldsList.forEach(field -> {
            field.setAccessible(true);
            if (matchedFieldNames.containsKey(field.getName())) {
                try {
                    field.set(entity,
                            GenericReflect.parseTypeHelper(field.getType(),
                                    matchedFieldNames.get(field.getName())));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });
    }



    public static String createStringFromEntity(Object entity){
        Class entityClass = entity.getClass();
        String entityClassName = entity.getClass().getSimpleName() + "{";
        try {
            AtomicReference<String> entityFieldsAndValues = new AtomicReference<>(" idEntity=`"
                    +  BaseEntity.class.getDeclaredField("idEntity").get(entity) + "`,");
            getEntityFieldsList(entityClass).forEach(field -> {
                // verificare si parsare pt lista ar trebui sa vina aici;
                    field.setAccessible(true);
                try {
                    entityFieldsAndValues.set(entityFieldsAndValues + " " + field.getName() + "=`" + field.get(entity) + "`,");
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }});
            return entityClassName + entityFieldsAndValues.get().replaceAll(",$" , "")+"}";
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

//----------------------------
//    JDBC and JDBCTemplate methods
//----------------------------

    //method can become subject to SQL Injection
    public static String getStringFieldValueForSQL(Object entity,Field field){
        Type fieldType = field.getType();
        field.setAccessible(true);
        try {
            if (fieldType == String.class ||
                    fieldType == char.class ||
                    fieldType == Character.class ||
                    fieldType == LocalDate.class ||
                    fieldType == LocalTime.class ||
                    fieldType == boolean.class ||
                    fieldType == Boolean.class ||
                    fieldType == Specialty.class) {
                String stringValue = "'" + field.get(entity) + "'";
                return stringValue;
            }
            else return String.valueOf(field.get(entity));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return "";
    }

    private static int resolveSQLType(Object obj){
        Class<?> objClass = obj.getClass();
        Integer type = 0;
        if (objClass == Short.class || objClass == short.class) type = Types.SMALLINT;
        if (objClass == Integer.class || objClass == int.class) type = Types.INTEGER;
        if (objClass == Long.class || objClass == long.class) type = Types.BIGINT;
        if (objClass == Float.class || objClass == float.class) type = Types.NUMERIC;
        if (objClass == Double.class || objClass == double.class) type = Types.NUMERIC;
        if (objClass == String.class || objClass == char.class) type = Types.VARCHAR;
        if (objClass == Boolean.class || objClass == boolean.class) type = Types.BOOLEAN;
        if (objClass == LocalDate.class) type = Types.DATE;
        if (objClass == LocalTime.class) type = Types.TIME;
        if (objClass == Specialty.class) type = Types.VARCHAR;
        return type;
    }

    //for JDBC Repository save/create method
    public static Tuple<Object[],int[]> getValuesAndTypesForSQL(Object entity){

        List<Object> values = new ArrayList<>();
        List<Integer> types = new ArrayList<>();
        getEntityFieldsList(entity.getClass()).forEach( field-> {
            try {
                field.setAccessible(true);
                Object objWithValue = field.get(entity);
                values.add(objWithValue);
                Integer type = resolveSQLType(field.get(entity));
                types.add(type);
            }
            catch (IllegalAccessException e)
            {e.printStackTrace();}
        });
        Object[] valuesPrimitiveArray = values.toArray(new Object[0]);
        int[] typesPrimitiveArray = types.stream().mapToInt(i->i).toArray();
        return new Tuple<Object[], int[]>(valuesPrimitiveArray,typesPrimitiveArray);
    }

    public static Object[] getSQLValues(Object entity){
        return getValuesAndTypesForSQL(entity).getFirst();
    }

    public static int[] getSQLTypes(Object entity){
        return getValuesAndTypesForSQL(entity).getSecond();
    }

    public static Object[] getSQLValuesWithID(Object id, Object entity){
        List<Object> values = new ArrayList<>();
        values.add(id);
        values.addAll( Arrays.asList( getSQLValues(entity)) );
        return values.toArray(new Object[0]);
    }

    public static int[] getSQLTypesWithID(Object id, Object entity){
        List<Integer> types = new ArrayList<>();
        Class<?> idClass = id.getClass();
        Integer idType = resolveSQLType(id);
        types.add(idType);
        types.addAll( Arrays.stream(getSQLTypes(entity))
                .boxed()
                .collect(Collectors.toList()) );
        return types.stream().mapToInt(i->i).toArray();
    }

    public static Tuple<String,String> stringFieldList(Object entity) {

        AtomicReference<String> fields = new AtomicReference<>("");
        AtomicReference<String> placeHolders = new AtomicReference<>("");
//        AtomicReference<String> values = new AtomicReference<>("");
        getEntityFieldsList(entity.getClass())
                .forEach(
                        field -> {
                            field.setAccessible(true);
                            fields.set(fields + "\"" + field.getName() + "\"" + ",");
                            //                values.set(values + GenericReflect.getStringFieldValueForSQL(entity,field) + ",");
                            placeHolders.set(placeHolders + "?,");
                        });
        return new Tuple<>(
                fields.get().replaceAll(",$",""),
                placeHolders.get().replaceAll(",$",""));
    }

    public static void setPreparedStatement(Class<?> type, Object entity, int index, PreparedStatement prepstmt, Method method)
            throws InvocationTargetException, IllegalAccessException, SQLException {
        // Pentru primitive
        if (type == char.class){ prepstmt.setString(index,(String) method.invoke(entity)); }

        if (type == boolean.class){ prepstmt.setBoolean(index,(boolean) method.invoke(entity)); }

        if (type == short.class){ prepstmt.setShort(index,(short) method.invoke(entity)); }
        if (type == byte.class){ prepstmt.setByte(index,(byte) method.invoke(entity)); }
        if (type == long.class){ prepstmt.setLong(index,(long) method.invoke(entity)); }
        if (type == int.class){ prepstmt.setInt(index,(int) method.invoke(entity)); }
        if (type == float.class){ prepstmt.setFloat(index,(float) method.invoke(entity)); }
        if (type == double.class){ prepstmt.setDouble(index,(double) method.invoke(entity)); }

        // Pentru Clase wrapper
        if (type == Character.class){ prepstmt.setString(index,(String) method.invoke(entity));  }

        if (type == Boolean.class){ prepstmt.setBoolean(index,(Boolean) method.invoke(entity)); }

        if (type == Short.class){ prepstmt.setShort(index,(Short) method.invoke(entity)); }
        if (type == Byte.class){ prepstmt.setByte(index,(Byte) method.invoke(entity)); }
        if (type == Long.class){ prepstmt.setLong(index,(Long) method.invoke(entity)); }
        if (type == Integer.class){  prepstmt.setInt(index,(Integer) method.invoke(entity)); }
        if (type == Float.class){ prepstmt.setFloat(index,(Float) method.invoke(entity)); }
        if (type == Double.class){  prepstmt.setDouble(index,(Double) method.invoke(entity));}

        //Trebuie sa fim atenti aici
//        if (type.getName().contains("EventType") && type.isEnum()){
//            EventType eventType = (EventType) method.invoke(entity);
//            prepstmt.setString(index, eventType.name()); }
        if (type.getName().contains("Specialty") && type.isEnum()){
            Specialty specialty = (Specialty) method.invoke(entity);
            prepstmt.setString(index, specialty.name()); }

        if (type == LocalDate.class){ prepstmt.setDate(index, Date.valueOf((LocalDate)method.invoke(entity)));}
        if (type == LocalTime.class){ prepstmt.setTime(index, Time.valueOf((LocalTime)method.invoke(entity)));}
        if (type == String.class){ prepstmt.setString(index,(String) method.invoke(entity));}
    }

    public static Object getColumnValueFromResultSet(Class<?> type, ResultSet resultSet, String fieldName) throws SQLException {
        // ("\"" + fieldName + "\"");
//        fieldName = "\"" + fieldName + "\"";
        if (type == char.class){ return resultGetStringAdjusted(resultSet,fieldName); }
        if (type == boolean.class){ return  resultSet.getBoolean(fieldName); }

        if (type == short.class){ return resultSet.getShort(fieldName); }
        if (type == byte.class){ return resultSet.getByte(fieldName); }
        if (type == long.class){ return resultSet.getLong(fieldName); }
        if (type == int.class){ return resultSet.getInt(fieldName); }
        if (type == float.class){ return resultSet.getFloat(fieldName); }
        if (type == double.class){ return resultSet.getDouble(fieldName); }

        // Pentru Clase wrapper
        if (type == Character.class){ return resultGetStringAdjusted(resultSet,fieldName);  }

        if (type == Boolean.class){ return resultSet.getBoolean(fieldName); }

        if (type == Short.class){ return resultSet.getDouble(fieldName); }
        if (type == Byte.class){ return resultSet.getByte(fieldName); }
        if (type == Long.class){ return resultSet.getLong(fieldName); }
        if (type == Integer.class){ return resultSet.getInt(fieldName); }
        if (type == Float.class){ return resultSet.getFloat(fieldName); }
        if (type == Double.class){ return resultSet.getDouble(fieldName);}

        //Trebuie sa fim atenti aici
//        if (type.getName().contains("EventType") && type.isEnum()){ return EventType.valueOf(resultGetStringAdjusted(resultSet,fieldName)); }
        if (type.getSimpleName().matches("Specialty") && type.isEnum()){ return Specialty.valueOf(resultGetStringAdjusted(resultSet,fieldName)); }
        if (type == LocalDate.class){ return resultSet.getDate(fieldName).toLocalDate();}
        if (type == LocalTime.class){ return resultSet.getTime(fieldName).toLocalTime();}

        return resultGetStringAdjusted(resultSet,fieldName);
    }

    private static String resultGetStringAdjusted(ResultSet resultSet, String fieldName) throws SQLException {
        return resultSet.getString(fieldName).replaceAll("\s{1,}$","");
    }

    public static Method getGetterMethod(Class entityClass, Field field){
        Method getter;
        try {
            // Se poate face si pentru conventia de getter "is" pentru boolean
            String methodName = field.getName().replaceAll("^[a-z]", "get" + field.getName().toUpperCase().charAt(0));
            getter = entityClass.getMethod(methodName);
            return getter;

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }

 }
