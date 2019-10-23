package ivan.vatlin;

import java.lang.reflect.InvocationTargetException;

public class Application {
    public static void main(String[] args) {
        String className = "ivan.vatlin.entity.Item";
        System.out.println("Класс: " + className);
        AnnotationAnalyzer annotationAnalyzer = new AnnotationAnalyzer();
        try {
            String classInfo = annotationAnalyzer.analyze(className);
            System.out.println(classInfo);
        } catch (ClassNotFoundException | NoSuchMethodException | IllegalAccessException |
                InvocationTargetException | InstantiationException e) {
            System.out.println("Проверьте корректонсть анализируемого класса " + e.getMessage());
        }
    }
}
