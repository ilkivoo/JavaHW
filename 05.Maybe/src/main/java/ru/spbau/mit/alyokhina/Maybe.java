package ru.spbau.mit.alyokhina;

import org.jetbrains.annotations.NotNull;
import java.util.function.Function;

public class Maybe<T> {
    /** The value that is stored */
    private T value;

    /**
     * Constructor
     * @param newValue this is the value that will be stored
     */
    private Maybe (T newValue) {
        value = newValue;
    }

    /**
     * Creates a new object of type Maybe storing the value of t in itself
     * @param t this is the value that will be stored
     * @param <T> type of t
     */
    @NotNull
    public static <T> Maybe<T> just(T t) {
        return new Maybe<T>(t);
    }

    /** Creates a new object of type Maybe be without a stored value */
    @NotNull
    public static <T> Maybe<T> nothing() {
        return new Maybe<T>(null);
    }

    /** returns the stored value, if it is, throws an exception (MaybeException), if there is no value */
    public T get() throws  MaybeException{
        if (value == null) {
            throw new MaybeException("value is null");
        }
        return value;
    }

    /** Returns true if the value is, and false if not */
    public boolean isPresent() {
        return (value != null);
    }

    /**
     * Takes a function and returns a new Maybe object with the value obtained by applying the function to the stored value
     * or empty Maybe, if the current Maybe is empty.
     * @param mapper is function that will be applied to the value that is stored in Maybe
     * @param <U> is a type value that will be store in return MAybe
     * @return new Maybe
     */
    public <U> Maybe<U> map(Function<T, U> mapper) {
        if (value == null) {
            return new Maybe<U>(null);
        }
        else
            return new Maybe<U> (mapper.apply(value));
    }

}
