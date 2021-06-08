package at.fhooe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class StreamTutorial {

    private static class IntegerRep {

        private final int i;

        public IntegerRep(int i) {
            this.i = i;
        }

        @Override
        public String toString() {
            return "This Integer has the value: " + this.i;
        }
    }

    public static void main(String... args) {
       Collection<Float> floats = List.of(1.0f, 2.0f, 3.0f, 4.0f, 5.0f, 6.0f, 7.0f, 8.0f);
        var dividedBy2 = floats.stream()
                .map(aFloat -> aFloat / 2);
        dividedBy2.forEach(System.out::println);

        List<String> strings = List.of("Test", "jkladsfj", "jklafsdjflöa");
        var t = strings.stream()
                .map(str -> str.replace('ö', 'o'))
                .map(str -> str.length())
                .map(i -> i * 2)
                .peek(System.out::println)
                .map(IntegerRep::new)
                .peek(System.out::println)
                .map(IntegerRep::toString)
                .collect(Collectors.toList());

        System.out.println("-----");

        var t1 = strings.stream()
                .map(str -> {
                    String replaced = str.replace('ö', 'o');
                    int len = replaced.length();
                    return len * 2;
                })
                .peek(System.out::println)
                .map(i -> {
                    IntegerRep rep = new IntegerRep(i);
                    System.out.println(rep);
                    return rep.toString();
                })
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();
        for (String str : strings) {
            String replaced = str.replace('ö', 'o');
            int len = replaced.length();
            int doubled = len * 2;
            System.out.println(doubled);
            IntegerRep rep = new IntegerRep(doubled);
            System.out.println(rep);
            String repString = rep.toString();
            result.add(repString);
        }

        List<Integer> numbers = List.of(1, 2, 4, 6, 8, 9);
        boolean hasOddNumber = numbers.stream()
                .anyMatch(i -> i % 2 == 1);
        var oddNumbers = numbers
                .stream()
                .filter(i -> i % 2 == 1);

    }
}
