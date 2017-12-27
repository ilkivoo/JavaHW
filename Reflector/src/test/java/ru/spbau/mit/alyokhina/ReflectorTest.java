package ru.spbau.mit.alyokhina;

import org.junit.Test;


public class ReflectorTest {
    @Test
    public void printStructure() throws Exception {
        Reflector re = new Reflector();
        re.printStructure(Example.class);
    }
}

class Parent<T, S> {

}


class Example<T> extends java.util.AbstractList {
    public int pub;
    private double pri;
    protected char pro;
    long without;
    T gen;
    java.util.List<java.lang.Integer> list;
    private final int fipriv = 0;
    final double x = 0;
    final boolean t = false;

    private Example() {
    }

    ;

    private <S> Example(S arg0, java.util.List<? extends T> arg1) {
    }

    ;

    Example(int arg0, double arg1) {
    }

    ;

    public Example(int arg0) {
    }

    ;

    private final void fpv() {
    }

    ;

    private void priv() {
    }

    ;

    public int publ(char arg0) {
        return 0;
    }

    ;

    protected double prot() {
        return 0;
    }

    ;

    char witho() {
        return 0;
    }

    ;

    public void foo(int arg0, int arg1) {
    }

    ;

    public <S> void foo(S arg0) {
    }

    ;

    public <S, E> void bar(S arg0, E arg1) {
    }

    ;

    public void bar(java.lang.Object arg0) {
    }

    ;

    private <S, E> S fb(S arg0, E arg1) {
        return null;
    }

    ;

    public int zzz() {
        return 0;
    }

    ;

    public java.lang.Object get(int arg0) {
        return null;
    }

    ;

    public int size() {
        return 0;
    }

    ;

    public class genericClass<E> extends java.lang.Object {
        public genericClass(ru.spbau.mit.alyokhina.Example arg0) {
        }

        ;

    }

    static class emptySteticClass extends java.lang.Object {
        emptySteticClass() {
        }

        ;

    }

    class emptyClass extends java.lang.Object {
        emptyClass(ru.spbau.mit.alyokhina.Example arg0) {
        }

        ;

    }

    protected class protectedClass extends java.lang.Object {
        protected protectedClass(ru.spbau.mit.alyokhina.Example arg0) {
        }

        ;

    }

    protected static class protectedStaticClass extends java.lang.Object {
        protected protectedStaticClass() {
        }

        ;

    }

    private class privateClass extends java.lang.Object {
        java.util.Set<java.lang.Double> set;

        private privateClass(ru.spbau.mit.alyokhina.Example arg0) {
        }

        ;

    }

    private static class privateStaticClass extends java.lang.Object {
        private privateStaticClass() {
        }

        ;

    }

    public class publicClass extends java.lang.Object {
        public publicClass(ru.spbau.mit.alyokhina.Example arg0) {
        }

        ;

        private final void fpv() {
        }

        ;

        private void priv() {
        }

        ;

        public int publ(char arg0) {
            return 0;
        }

        ;

        protected double prot() {
            return 0;
        }

        ;

        char witho() {
            return 0;
        }

        ;

        public <S> void foo(S arg0) {
        }

        ;

        public <S, E> void bar(S arg0, E arg1) {
        }

        ;

        private <S, E> S fb(S arg0, E arg1) {
            return null;
        }

        ;

    }

    public static class publicStaticClass extends java.lang.Object {
        public publicStaticClass() {
        }

        ;

    }

}

