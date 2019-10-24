package ivan.vatlin;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AnnotationAnalyzer {

    public String analyze(String className) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException,
            InvocationTargetException, InstantiationException {
        Class<?> aClass = Class.forName(className);
        Constructor<?> constructor = aClass.getConstructor();
        Object item = constructor.newInstance();

        Method[] declaredMethods = item.getClass().getDeclaredMethods();
        StringBuilder stringBuilder = new StringBuilder();
        for (Method method : declaredMethods) {
            stringBuilder.append("Метод: ");
            stringBuilder.append(method.getName());
            stringBuilder.append("\n");
            stringBuilder.append(getAnnotationInfo(method.getDeclaredAnnotations()));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private String getAnnotationInfo(Annotation... annotations) {
        if (annotations.length == 0) {
            return "Аннотации отсутствуют";
        }

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Аннотации: ");
        for (Annotation annotation : annotations) {
            stringBuilder.append(annotation.toString());
            stringBuilder.append(" ");
        }
        return stringBuilder.toString();
    }
}
