package ru.spbau.mit.alyokhina;


import java.io.*;
import java.lang.reflect.*;


public class Reflector {

    public static void printStructure(Class<?> someClass) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(someClass.getPackage().toString()).append(";\n");
        stringBuilder.append(getStructure(someClass));
        printInFIle(someClass.getSimpleName(), stringBuilder.toString());
    }

    private static void printInFIle(String fileName, String data) throws IOException {
        File file = new File(fileName + ".java");
        file.createNewFile();
        FileWriter writer = new FileWriter(file);
        writer.write(data);
        writer.close();
    }

    private static String getStructure(Class<?> someClass) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getHeader(someClass));
        stringBuilder.append(" {\n");
        stringBuilder.append(getFields(someClass));
        stringBuilder.append(getConstructor(someClass));
        stringBuilder.append(getMethods(someClass));
        stringBuilder.append(getClasses(someClass));
        stringBuilder.append("\n}");
        return stringBuilder.toString();
    }

    private static String getHeader(Class<?> someClass) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getModifiers(someClass.getModifiers()));
        stringBuilder.append(Modifier.isInterface(someClass.getModifiers()) ? " " : "class ");
        stringBuilder.append(someClass.getSimpleName());
        stringBuilder.append(" ");
        Type[] types = someClass.getTypeParameters();
        if (types.length != 0) {
            stringBuilder.append('<');
            for (int i = 0; i < types.length; i++) {
                stringBuilder.append(types[i].getTypeName());
                if (i < types.length - 1) {
                    stringBuilder.append(", ");
                }
            }
            stringBuilder.append("> ");
        }
        if (someClass.getGenericSuperclass() != null) {
            stringBuilder.append("extends ");
            stringBuilder.append(someClass.getGenericSuperclass().getTypeName());
            stringBuilder.append(" ");

        }
        Type[] interfaces = someClass.getGenericInterfaces();
        if (interfaces.length != 0) {
            stringBuilder.append("implements ");
            for (int i = 0; i < interfaces.length; i++) {
                stringBuilder.append(interfaces[i].getTypeName());
                if (i < interfaces.length - 1) {
                    stringBuilder.append(", ");
                }
            }
        }
        return stringBuilder.toString();
    }

    private static String getFields(Class<?> someClass) {
        StringBuilder stringBuilder = new StringBuilder();
        Field[] fields = someClass.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isSynthetic()) {
                stringBuilder.append(getModifiers(field.getModifiers()));
                stringBuilder.append(field.getGenericType());
                stringBuilder.append(' ');
                stringBuilder.append(field.getName());
                stringBuilder.append(Modifier.isFinal(field.getModifiers()) ? " = " + initializeFields(field.getType()) : "");
                stringBuilder.append(";\n");
            }
        }

        return stringBuilder.toString();
    }

    private static String getConstructor(Class<?> someClass) {
        StringBuilder stringBuilder = new StringBuilder();
        Constructor<?>[] constructors = someClass.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            stringBuilder.append(getModifiers(constructor.getModifiers()));
            stringBuilder.append(getGenericTypes(constructor));
            stringBuilder.append(someClass.getSimpleName());
            stringBuilder.append(getParameters(constructor));
            stringBuilder.append("{};");
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private static String getMethods(Class<?> someClass) {
        StringBuilder stringBuilder = new StringBuilder();
        Method[] methods = someClass.getDeclaredMethods();
        for (Method method : methods) {
            stringBuilder.append(getModifiers(method.getModifiers()));
            stringBuilder.append(getGenericTypes(method));
            stringBuilder.append(deleteSubstrClass(method.getGenericReturnType().toString()));
            stringBuilder.append(' ');
            stringBuilder.append(method.getName());
            stringBuilder.append(getParameters(method));
            if (!Modifier.isInterface(someClass.getModifiers())) {
                stringBuilder.append(method.getGenericReturnType().toString().equals("void") ? " {}" : " { return " + initializeFields(method.getReturnType()) + "; }");
            }
            stringBuilder.append(";\n");
        }
        return stringBuilder.toString();
    }

    private static String getClasses(Class<?> someClass) {
        StringBuilder stringBuilder = new StringBuilder();
        Class<?>[] classes = someClass.getDeclaredClasses();
        for (Class<?> clazz : classes) {
            stringBuilder.append(getStructure(clazz));
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }

    private static String getGenericTypes(Executable method) {
        String genericTypeString = method.toGenericString();
        int indexBegin = -1;
        for (int i = 0; i < genericTypeString.length() && indexBegin == -1; i++) {
            if (genericTypeString.charAt(i) == '<') {
                indexBegin = i;
            }
        }
        if (indexBegin == -1) {
            return "";
        }
        int indexEnd = -1;
        for (int j = indexBegin; j < genericTypeString.length() && indexEnd == -1; j++) {
            if (genericTypeString.charAt(j) == '>') {
                indexEnd = j;
            }
        }
        if (indexEnd == -1) {
            return "";
        }
        String result = genericTypeString.substring(indexBegin, indexEnd + 1);
        if (result.length() != 0) {
            result += ' ';
        }
        return result;
    }

    private static String initializeFields(Class<?> type) {
        if (!type.isPrimitive()) {
            return "null";
        }

        if (type == boolean.class) {
            return "false";
        }

        return "0";
    }

    private static String getModifiers(int modifier) {
        String modifierString = Modifier.toString(modifier);
        if (modifierString.length() != 0) {
            modifierString += ' ';
        }
        return modifierString;
    }


    private static String getParameters(Executable method) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append('(');
        Parameter[] parameters = method.getParameters();
        Type[] typeParameters = method.getGenericParameterTypes();
        for (int i = 0; i < parameters.length; i++) {
            stringBuilder.append(typeParameters[i].getTypeName());
            stringBuilder.append(" ");
            stringBuilder.append(parameters[i].getName());
            if (i < parameters.length - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append(")");
        return stringBuilder.toString();
    }

    private static String deleteSubstrClass(String string) {
        if (string.length() >= 6 && string.substring(0, 6).equals("class ")) {
            return string.substring(6);
        } else
            return string;
    }

}
