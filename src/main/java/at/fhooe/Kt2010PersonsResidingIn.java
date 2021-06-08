package at.fhooe;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

public class Kt2010PersonsResidingIn {

    private static class Address {
        public String zipCode;
        public String city;

        public Address(String zipCode, String city) {
            this.zipCode = zipCode;
            this.city = city;
        }
    }

    private static class Person {
        public String name;
        public Collection<Address> addresses;

        public Person(String name, Address... addresses) {
            this.name = name;
            this.addresses = Arrays.asList(addresses);
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static Collection<Person> personsResidingIn(Collection<Person> allPersons, String zipCode) {
        Collection<Person> newPersons = new ArrayList<>();
        for (Person p: allPersons) {
            for (Address a: p.addresses)
                if(a.zipCode.equals(zipCode)) newPersons.add(p);
        }
        return newPersons;
    }

    public static Collection<Person> personsResidingInStream(Collection<Person> allPersons, String zipCode) {
        return allPersons.stream()
                .filter(p -> p.addresses
                        .stream()
                .anyMatch(a -> a.zipCode.equals(zipCode)))
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        Collection<Person> persons = new ArrayList<>();
        persons.add(new Person("Flockig", new Address("4070", "Pupping"),
                new Address("4232", "Hagenberg"),
                new Address("1230", "Winston")
        ));
        persons.add(new Person("Lea", new Address("4070", "Schatting")));
        persons.add(new Person("Andi", new Address("4070", "Pupping")));

        personsResidingIn(persons, "4232")
                .forEach(System.out::println);

        System.out.printf("%n");

        personsResidingInStream(persons, "4232")
                .forEach(System.out::println);
    }
}
