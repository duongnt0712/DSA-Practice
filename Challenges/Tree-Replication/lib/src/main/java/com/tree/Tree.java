package com.tree;

import java.util.List;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.stream.Stream;

public interface Tree<T> extends Iterable<T> {

    Tree<T> add(T i);

    Tree<T> remove(T i);

    T root();

    default  <R> R transform(R initValue, BiFunction<R, T, R> function) {
        R value = initValue;
        for (T t : this) {
            value = function.apply(value, t);
        }
        return value;
    }

    /**
     * Sonar: Prefer BinaryOperator<T> instead of BiFunction<T,T,T>.
     */
    default T reduce(T identity, BinaryOperator<T> function) {
        return this.transform(identity, function);
    }

    // use a Consumer when you want to do something with a parameter but not return anything.
    default void scan(Consumer<T> consumer) {
        this.reduce(null, (init, item) -> {
            consumer.accept(item);
            return item;
        });
    }

    default List<T> toList() {
        return this.transform(List.of(), (l, item) -> Stream.concat(l.stream(), Stream.of(item)).toList());
    }
}
