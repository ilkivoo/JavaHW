package ru.spbau.mit.alyokhina;
/** Exception for Maybe */
public class ValueNotPresentException extends Exception {
    public ValueNotPresentException(String msg){
        super(msg);
    }
}
