package ru.spbau.mit.alyokhina;

/**
 * Interface for working with lambda functions.
 * Predicate = function of one variable that returns boolean
 *
 * @param <TypeArgument> predicate argument type
 */
public interface Predicate<TypeArgument> extends Function1<TypeArgument, Boolean> {
    /**
     * Disjunction
     *
     * @param predicate predicate with which the disjunction will be made
     * @param <Type>    type of predicate with which the disjunction will be made
     * @return predicate that behaves like a disjunction
     */
    default <Type extends TypeArgument> Predicate<Type> or(Predicate<Type> predicate) {
        return argument -> Predicate.this.apply(argument) || predicate.apply(argument);
    }

    /**
     * Conjunction
     *
     * @param predicate predicate with which the conjunction will be made
     * @param <Type>    type of predicate with which the conjunction will be made
     * @return predicate that behaves like a conjunction
     */
    default <Type extends TypeArgument> Predicate<Type> and(Predicate<Type> predicate) {
        return argument -> Predicate.this.apply(argument) && predicate.apply(argument);
    }

    /**
     * Negation
     *
     * @return a predicate-negative of the current predicate
     */
    default Predicate<TypeArgument> not() {
        return argument -> !Predicate.this.apply(argument);
    }

    /**
     * Const predicate True
     *
     * @param <TypeArgument> predicate argument type
     * @return predicate that behaves like a const True
     */
    static <TypeArgument> Predicate<TypeArgument> alwaysTrue() {
        return argument -> true;
    }

    /**
     * Const predicate False
     *
     * @param <TypeArgument> predicate argument type
     * @return predicate that behaves like a const True
     */
    static <TypeArgument> Predicate<TypeArgument> alwaysFalse() {
        return argument -> false;
    }
}