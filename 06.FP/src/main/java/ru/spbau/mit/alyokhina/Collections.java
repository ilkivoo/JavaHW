package ru.spbau.mit.alyokhina;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/** Class for work with iterable collections */
public class Collections {
    /**
     * Takes function from one variable and iterable collection, applies function to each element a_i and returns a list of [f (a_1), ..., f (a_n)]
     * @param func function to be applied
     * @param data elements to which will be applied
     * @param <TypeArgument> element type in data
     * @param <TypeReturnValue> return type
     * @return List new element ( new element = applied function to the old element)
     */
    @NotNull
    public static <TypeArgument, TypeReturnValue> List<TypeReturnValue> map(
            @NotNull Function1<? super TypeArgument, ? extends TypeReturnValue> func, @NotNull Iterable<TypeArgument> data) {
        List<TypeReturnValue> answer = new LinkedList<>();
        for (TypeArgument element : data) {
            answer.add(func.apply(element));
        }
        return answer;
    }

    /**
     * Takes predicate and iterable collection, returns a list containing the elements a_i on which p (a_i) == true
     * @param predicate predicate to be applied
     * @param data elements to which will be applied
     * @param <TypeArgument> element type in data
     * @return list of elements on which the predicate is true
     */
    @NotNull
    public static <TypeArgument> List<TypeArgument> filter(@NotNull Predicate<? super TypeArgument> predicate,
                                                           @NotNull Iterable<TypeArgument> data) {
        List<TypeArgument> result = new LinkedList<>();
        for (TypeArgument element : data) {
            if (predicate.apply(element)) {
                result.add(element);
            }
        }
        return result;
    }

    /**
     * Takes predicate and iterable collection, returns a list with the beginning up to the first element ai, for which p (ai) == false
     * @param predicate predicate to be applied
     * @param data elements to which will be applied
     * @param <TypeArgument> element type in data
     * @return list with the beginning up to the first element ai, for which p (ai) == false
     */
    @NotNull
    public static <TypeArgument> List<TypeArgument> takeWhile(@NotNull Predicate<? super TypeArgument> predicate, @NotNull Iterable<TypeArgument> data) {
        List<TypeArgument> result = new LinkedList<>();
        for (TypeArgument element : data) {
            if (!predicate.apply(element)) {
                break;
            }
            result.add(element);
        }
        return result;
    }

    /**
     * Takes predicate and iterable collection, returns a list with the beginning up to the first element ai, for which p (ai) == true
     * @param predicate predicate to be applied
     * @param data elements to which will be applied
     * @param <TypeArgument> element type in data
     * @return list with the beginning up to the first element ai, for which p (ai) == true
     */
    @NotNull
    public static <TypeArgument> List<TypeArgument> takeUnless(@NotNull Predicate<? super TypeArgument> predicate, @NotNull Iterable<TypeArgument> data) {
        return takeWhile(predicate.not(), data);
    }

    /**
     * Function which converts the data structure to a single atomic value using a given function starting with the first element
     * @param func function to be applied
     * @param mempty neutral element
     * @param data elements to which will be applied
     * @param <TypeArgument> element type in data
     * @param <TypeReturnValue> return type
     * @return the value of the function applied to the elements in turn
     */
    @NotNull
    public static <TypeArgument, TypeReturnValue> TypeReturnValue foldl(@NotNull Function2 <? super TypeReturnValue, ? super TypeArgument, ? extends TypeReturnValue> func,
                                                                        TypeReturnValue mempty, @NotNull Iterable<TypeArgument> data) {
        TypeReturnValue answer = mempty;
        for (TypeArgument element : data) {
            answer = func.apply(answer, element);
        }
        return answer;
    }


    /**
     * Function which converts the data structure to a single atomic value using a given function starting with the last element
     * @param func function to be applied
     * @param mempty neutral element
     * @param iteratorCurrentElement iterator on the current element
     * @param <TypeArgument> element type in data
     * @param <TypeReturnValue> return type
     * @return the value of the function applied to the elements in turn
     */
    @NotNull
    private static <TypeArgument, TypeReturnValue> TypeReturnValue foldr(@NotNull Function2<? super TypeArgument, ? super TypeReturnValue, ? extends TypeReturnValue> func,
                                                                         TypeReturnValue mempty, @NotNull Iterator<TypeArgument> iteratorCurrentElement) {
        if (iteratorCurrentElement.hasNext()) {
            return foldr(func, func.apply(iteratorCurrentElement.next(), mempty), iteratorCurrentElement);
        } else {
            return mempty;
        }
    }

    /**
     * Function which converts the data structure to a single atomic value using a given function starting with the last element
     * @param func function to be applied
     * @param mempty neutral element
     * @param data elements to which will be applied
     * @param <TypeArgument> element type in data
     * @param <TypeReturnValue> return type
     * @return the value of the function applied to the elements in turn
     */
    @NotNull
    public static <TypeArgument, TypeReturnValue> TypeReturnValue foldr(@NotNull Function2<? super TypeArgument, ? super TypeReturnValue, ? extends TypeReturnValue> func,
                                                             TypeReturnValue mempty, @NotNull Iterable<TypeArgument> data) {
        return foldr(func, mempty, data.iterator());
    }
}
