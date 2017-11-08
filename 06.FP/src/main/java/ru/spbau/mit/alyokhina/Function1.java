package ru.spbau.mit.alyokhina;

/**
 * Interface for working with lambda functions.
 * Function of one argument ("f (x)")
 * @param <TypeArgument> function argument type
 * @param <TypeReturnValue> function return value type
 */
public interface Function1 <TypeArgument, TypeReturnValue> {
    /** Applies function to an argument */
    TypeReturnValue apply (TypeArgument argument);

    /**
     * Composition, takes "Function1 func", returns "func (f (x))"
     * @param func function that will be applied after applying the original
     * @param <Type> type of the return value, after applying to func
     * @return function that is equal to the composition
     */
    default <Type> Function1 <TypeArgument, Type> compose(Function1<? super TypeReturnValue, Type> func) {
        return argument -> func.apply(Function1.this.apply(argument));
    }
}
