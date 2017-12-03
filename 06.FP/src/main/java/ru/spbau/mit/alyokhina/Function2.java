package ru.spbau.mit.alyokhina;

/**
 * Interface for working with lambda functions.
 * Function of two arguments ("f (x, y)")
 *
 * @param <TypeFirstArgument>  type of the first argument
 * @param <TypeSecondArgument> type of second argument
 * @param <TypeReturnValue>    return type
 */
public interface Function2<TypeFirstArgument, TypeSecondArgument, TypeReturnValue> {
    /**
     * Applying a function to arguments
     *
     * @param argument1 the first argument
     * @param argument2 the second argument
     * @return the value of a function of these arguments
     */
    TypeReturnValue apply(TypeFirstArgument argument1, TypeSecondArgument argument2);

    /**
     * Composition, takes "Function1 g", returns "g (f (x, y))"
     *
     * @param func   function that will be applied after applying the original
     * @param <Type> type of the return value, after applying to func
     * @return function that is equal to the composition
     */
    default <Type> Function2<TypeFirstArgument, TypeSecondArgument, Type> compose(Function1<? super TypeReturnValue, Type> func) {
        return (argument1, argument2) -> func.apply(Function2.this.apply(argument1, argument2));
    }

    /**
     * Bind one argument, take the first argument, return "f (_, y)"
     *
     * @param argument1 the first argument
     * @return function from one variable (the second argument)
     */
    default Function1<TypeSecondArgument, TypeReturnValue> bind1(TypeFirstArgument argument1) {
        return argument2 -> Function2.this.apply(argument1, argument2);
    }

    /**
     * Bind one argument, take the first argument, return "f (x, _)"
     *
     * @param argument2 the second argument
     * @return function from one variable (the first argument)
     */
    default Function1<TypeFirstArgument, TypeReturnValue> bind2(TypeSecondArgument argument2) {
        return argument1 -> Function2.this.apply(argument1, argument2);
    }

    /**
     * Currying, converting to "Function1". For example, f = (x, y) -> x + y, f (5) = x -> x + 5
     *
     * @return function that returns the function from a single variable
     */
    default Function1<TypeSecondArgument, Function1<TypeFirstArgument, TypeReturnValue>> curry() {
        return Function2.this::bind2;
    }

}
