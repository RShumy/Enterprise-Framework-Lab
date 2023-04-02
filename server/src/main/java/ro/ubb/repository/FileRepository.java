package ro.ubb.repository;


import ro.ubb.additional.GenericReflect;
import ro.ubb.domain.BaseEntity;
import ro.ubb.validators.ValidatorException;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Map;
import java.util.Optional;
import java.util.Set;


public class FileRepository<ID , T extends BaseEntity<ID>> extends InMemoryRepository<ID, T> {

    private String fileName;
    public static Set<Class> classSet;
    private Object ID;


    public FileRepository(String fileName){
        fileName = "./src/Data/" + fileName;
        this.fileName = fileName;
        loadData();
    }

    private Path path() {
        Path path = Paths.get(fileName);
        System.out.println(path.toAbsolutePath());
        return path;
    }



    public void loadData() {
        try {

            Files.lines(path()).forEach(line -> {
                Map<String, String> matchedFieldNames = GenericReflect.getFieldNamesAndValuesMapFromString(line);
                ID id = (ID) ((Number) 0);

                T entity = (T) GenericReflect.getEntityInstanceFromString(line);
                GenericReflect.setIdEntityFromString(entity, id, matchedFieldNames);
                GenericReflect.setEntityValuesFromString(entity, matchedFieldNames);
                super.save(entity);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


        @Override
    public Optional<T> save(T entity) {
        Optional<T> optional = super.save(entity);
        if (optional.isPresent()) {
            saveToFile(entity);
            return optional;
        }
        return Optional.empty();
    }



    public void saveToFile(T entity) {
        try (BufferedWriter bufferedWriter = Files.newBufferedWriter(path(), StandardOpenOption.APPEND)){
            bufferedWriter.write(GenericReflect.createStringFromEntity(entity));
            bufferedWriter.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




    @Override
    public Optional<T> findOne(ID id) {
        return super.findOne(id);
    }

    @Override
    public Iterable<T> findAll() {
        loadData();
        return super.findAll();
    }

    @Override
    public Optional<T> update(T entity) throws ValidatorException {
        return Optional.empty();
    }

    @Override
    public Optional<T> delete(ID id) throws ValidatorException {
        super.delete(findOne(id).get().getIdEntity());
        try (FileWriter fileWriter = new FileWriter(fileName);
        BufferedWriter bw = new BufferedWriter(fileWriter)) {
            findAll().forEach(entity -> {
                try {
                    bw.append(GenericReflect.createStringFromEntity(entity));
                    bw.newLine();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    return Optional.empty();
    }



//    private void readFromFile(Path path) throws IOException{
//
//        Files.lines(path).forEach(
//                line -> {
//
//            Map<String, String> matchedFieldNames = new LinkedHashMap<>();
//            Pattern classPattern = Pattern.compile("^[A-Z][a-z]{1,}\\{");
//            Matcher classMatcher = classPattern.matcher(line);
//            String theClass = classMatcher.group().replace("{", "");
//
//            try {
//                Class classEntity = Class.forName("ro.ubb.ro.ubb.domain." + theClass);
//                for (Field field : classEntity.getDeclaredFields()) {
//                    String value;
//                    Pattern fieldNamePattern = Pattern.compile("(?:\\s[A-Za-z]{1,}=`)");
//                    Matcher fieldMatcher = fieldNamePattern.matcher(line);
//
//                    Pattern valuePattern = Pattern.compile("(?:`.{1,}`)");
//                    Matcher valueMather = valuePattern.matcher(line);
////                    List<String> matchedFieldNames = new ArrayList<>();
//                    while (fieldMatcher.find()) {
//                        //index reference is for the .split() method that returns an Array of String[]
//                        String fieldName = fieldMatcher.group().trim().split("=`")[0];
//                        matchedFieldNames.putIfAbsent(fieldName,valueMather.group().replace("`",""));
//                        System.out.println(matchedFieldNames.get(fieldName));
//                    }
//                }
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//
//        });
//    }



//    public static void loadPackageResources () {
//        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
//        InputStream line = classLoader.getResourceAsStream("ro/ubb/ro.ubb.domain");
//        BufferedReader readLines = new BufferedReader(new InputStreamReader(line));
//        readLines.lines().forEach(
//                lineRow ->{
//                    try {
//                        Class classToInsertInSet = Class.forName("ro.ubb.entity"+lineRow.replace("class","."));
//                        classSet.add(classToInsertInSet);
//                    } catch (ClassNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                });
//    }

}
