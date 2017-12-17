package ru.spbau.mit.alyokhina;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.stream.Collectors;

public class Injector {
    public static Object initialize(String rootClassName, List<String> parametrs) throws ImplementationNotFoundException, ClassNotFoundException,NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, InjectionCycleException{
        Class<?> rootClass;
        try{
            rootClass = Class.forName(rootClassName);
        }
        catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw e;
        }

        List<Class<?>> paramClass = parametrs.stream().map(x -> {
            try {
                return Class.forName(x);
            }
            catch (ClassNotFoundException e) {
                System.out.println(e.getMessage());
                throw new ClassCastException(e.getMessage());
            }
        }).collect(Collectors.toList());
        paramClass.add(rootClass);
        Map<Class<?>, Object> tmp = new HashMap<>();
        HashSet<String> visit = new HashSet<>();
        return  dfs(rootClass,paramClass, tmp, visit);
    }

    private static Object dfs(Class<?> curClass, List<Class<?>> impl,  Map<Class<?>, Object> have, HashSet<String> visit) throws ImplementationNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, InjectionCycleException{
        if (visit.contains(curClass.getName())) {
            throw new InjectionCycleException();
        }
        visit.add(curClass.getName());
        if (have.containsKey(curClass)) {
            return have.get(curClass);
        }
        else {
            Constructor<?> constructor = (curClass.getDeclaredConstructors())[0];
            Class<?>[] classParam = constructor.getParameterTypes();
            List<Object> forConstructor = new ArrayList<>();
            for (Class<?> i : classParam) {
                Class<?> c = getClass(i, impl);
                forConstructor.add(dfs(i, impl, have, visit));
            }
            Object ans;
            ans = constructor.newInstance(forConstructor.toArray(new Object [forConstructor.size()]));
            have.put(curClass, ans);
            visit.remove(curClass.getName());
            return have.get(curClass);
        }
    }

    private static Class<?> getClass(Class<?> root, List<Class<?>> impl) {
        for(Class<?> i : impl) {
            boolean flag = false;
            if (i.equals(root)) {
                flag = true;
            }
            if (i.isInterface()) {
                for (Class<?> j : root.getInterfaces()) {
                    if (j.equals(i))
                        flag = true;
                }
            }
            else {
                Class <?> j = root;
                while (j.getSuperclass() != null && flag== false) {
                    if (j.equals(i)) {
                        flag = true;
                    }
                }
            }
            if (flag) {
                return i;
            }
        }
        return null;
    }


}
