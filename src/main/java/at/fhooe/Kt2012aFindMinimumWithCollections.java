package at.fhooe;

import java.util.*;

public class Kt2012aFindMinimumWithCollections {
    public static <T> Object findMinimum(Iterable<T> collection, Comparator<T> comparator) throws EmptyCollectionException {
        final Iterator<T> iterator = collection.iterator();
        if(!iterator.hasNext()) {
            throw new EmptyCollectionException();
        }
        T min = iterator.next();
        for (T t : collection) {
            if(comparator.compare(t, min) < 0) {
                min = t;
            }
        }
        return min;
    }

    public static <T> Object findMinimumByLea(Iterable<T> collection, Comparator<T> comparator) throws EmptyCollectionException {
        Iterator<T> iterator = collection.iterator();
        if(!iterator.hasNext()) throw new EmptyCollectionException();

        T min = iterator.next();
        for (T t : collection)
            if(comparator.compare(t, min) < 0) min = t;
        return min;
    }

    public static <T extends Comparable<T>> Object findMinimum(Iterable<T> collection) throws EmptyCollectionException {
        return findMinimum(collection, Comparator.naturalOrder());
    }

    public static <T extends Comparable<T>> Object findMinimum2(Iterable<T> collection) throws EmptyCollectionException {
        return findMinimum(collection, T::compareTo);
    }

    public static <T extends Comparable<T>> Object findMinimum3(Iterable<T> collection) throws EmptyCollectionException {
        return findMinimum(collection, (t1, t2) -> t1.compareTo(t2));
    }

    private static class EmptyCollectionException extends RuntimeException {
    }

    private static class IntegerRepresentation implements Comparable<IntegerRepresentation> {

        private final int value;

        public IntegerRepresentation(int value) {
            this.value = value;
        }

        @Override
        public int compareTo(IntegerRepresentation representation) {
            return value - representation.value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "" + value;
        }
    }

    private static class IntegerRepresentationComparator implements Comparator<IntegerRepresentation> {
        @Override
        public int compare(IntegerRepresentation i1, IntegerRepresentation i2) {
            if(i1 == null && i2 == null) {
                return 0;
            }
            if(i1 == null) {
                return -1;
            }
            if(i2 == null) {
                return 1;
            }
            return i1.getValue() - i2.getValue();
        }
    }

    private static class IntegerRepresentationComparator2 implements Comparator<IntegerRepresentation> {
        @Override
        public int compare(IntegerRepresentation integerRepresentation, IntegerRepresentation t1) {
            return Integer.compare(integerRepresentation.getValue(), t1.getValue());
        }
    }

    public static void main(String[] args) {
        IntegerRepresentation i1 = new IntegerRepresentation(3);
        IntegerRepresentation i2 = new IntegerRepresentation(4000);
        Collection<IntegerRepresentation> integerRepresentationsList = new ArrayList<>();
        integerRepresentationsList.add(i1);
        integerRepresentationsList.add(i2);

        System.out.println(findMinimum(integerRepresentationsList, new IntegerRepresentationComparator()));
    }
}
