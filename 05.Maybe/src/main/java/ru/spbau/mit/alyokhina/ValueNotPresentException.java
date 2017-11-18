package ru.spbau.mit.alyokhina;
/** Exception for Maybe. If the user calls get(), and the value isn't stored in the class, then this exception throws */
public class ValueNotPresentException extends Exception {
    public ValueNotPresentException(String msg){
        super(msg);
    }
}
