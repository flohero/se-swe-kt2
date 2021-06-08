package at.fhooe;

import java.util.*;
import java.util.stream.Collectors;

public class Kt2012bLongMirgratingBirdsWithCollections {

    public class Bird implements Comparable<Bird> {

        private final String kind;
        private double flightDistance;

        public Bird(String kind) {
            this.kind = kind;
        }

        public String getKind() {
            return kind;
        }

        public void setFlightDistance(double flightDistance) {
            this.flightDistance = flightDistance;
        }

        public double getFlightDistance() {
            return flightDistance;
        }

        @Override
        public int compareTo(Bird bird) {
            return kind.compareTo(bird.kind);
        }
    }

    public static Collection<Bird> longMigratingBirds(Set<Bird> allBirds) {

        return allBirds.stream()
                .filter(b -> b.getFlightDistance() > 4000)
                .sorted(Comparator.reverseOrder())
                .collect(Collectors.toList());
    }

    public static Collection<Bird> longMigratingBirdsByLea(Set<Bird> allBirds) {
        TreeSet<Bird> birds = new TreeSet<>(new BirdDescendingDistanceComparator());

        for (Bird b : allBirds)
            if (b.getFlightDistance() > 4000) birds.add(b);

        return birds;
    }

    public static Collection<Bird> longMigratingBirdsAndi(Set<Bird> allBirds) {

        TreeSet<Bird> birds = new TreeSet<>(new BirdDescendingDistanceComparator());

        for (Bird bird : allBirds) {
            if (bird.getFlightDistance() > 4000) {
                birds.add(bird);
            }
        }

        return birds;
    }

    public static class BirdDescendingDistanceComparator implements Comparator<Bird> {
        @Override
        public int compare(Bird bird1, Bird bird2) {
            // Sehr sauber gelöst
            return Double.compare(bird2.getFlightDistance(), bird1.getFlightDistance());
        }
    }
}
