package at.fhooe;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Kt2006BadGuyWithCollections {
    private static class Student {
        public String name;
        public Collection<Integer> grades; // contains Integer objects

        public Student(String name, Collection<Integer> grades) {
            this.name = name;
            this.grades = grades;
        }

        public boolean isBadGuy() {
            return grades.stream()
                    .anyMatch(grade -> grade == 5);
        }

        public boolean isBadGuy2() {
            for (Integer grade :
                    grades) {
                if (grade == 5) {
                    return true;
                }
            }
            return false;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static Collection<String> filterBadGuys(Collection<Student> allStudents) {
        return allStudents.stream()
                .filter(Student::isBadGuy)
                .map(Student::toString)
                .collect(Collectors.toList());
    }

    public static Collection<String> filterBadGuys2(Collection<Student> allStudents) {
        List<String> badGuys = new ArrayList<>();
        for (Student stud :
                allStudents) {
            if(stud.isBadGuy2()) {
                badGuys.add(stud.name);
            }
        }
        return badGuys;
    }

        public static void main(String... args) {
        var allStudents = List.of(
                new Student("Lea", List.of(5, 5, 5, 5, 1)),
                new Student("Flo", List.of(1, 1, 1, 1, 5)),
                new Student("Andi", List.of(3, 2, 1, 4, 5)),
                new Student("Alex", List.of(5, 5, 5, 5, 5)),
                new Student("PowerGamer", List.of(1, 1, 1, 1, 1))
        );
        System.out.println("These are the Bad Guys!");
            filterBadGuys2(allStudents)
                .forEach(System.out::println);
    }

}
