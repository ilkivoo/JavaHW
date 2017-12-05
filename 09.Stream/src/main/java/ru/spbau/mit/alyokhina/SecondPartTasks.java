package ru.spbau.mit.alyokhina;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public final class SecondPartTasks {

    private SecondPartTasks() {}

    // Найти строки из переданных файлов, в которых встречается указанная подстрока.
    public static List<String> findQuotes(List<String> paths, CharSequence sequence) {
        return paths.stream().flatMap(path -> {
            try {
                return Files.lines(Paths.get(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        })
                .filter(str -> str.contains(sequence))
                .collect(Collectors.toList());
    }

    // В квадрат с длиной стороны 1 вписана мишень.
    // Стрелок атакует мишень и каждый раз попадает в произвольную точку квадрата.
    // Надо промоделировать этот процесс с помощью класса java.util.Random и посчитать, какова вероятность попасть в мишень.
    public static double piDividedBy4() {
        Random generator = new Random();
        return Stream.generate(() -> {
            if (Math.hypot(generator.nextDouble(), generator.nextDouble()) <= 1.0) return 1;
            else return 0;
        })
                .limit(10000)
                .collect(Collectors.averagingInt(a -> a));
    }

    // Дано отображение из имени автора в список с содержанием его произведений.
    // Надо вычислить, чья общая длина произведений наибольшая.
    public static String findPrinter(Map<String, List<String>> compositions) {
        return compositions.entrySet()
                .stream()
                .max(Comparator.comparing(composition -> composition.getValue().stream().mapToInt(String::length).sum()))
                .map(Map.Entry::getKey).orElse("");
    }

    // Вы крупный поставщик продуктов. Каждая торговая сеть делает вам заказ в виде Map<Товар, Количество>.
    // Необходимо вычислить, какой товар и в каком количестве надо поставить.
    public static Map<String, Integer> calculateGlobalOrder(List<Map<String, Integer>> orders) {
        return orders.stream().flatMap(order -> order.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, Integer::sum));
    }
}