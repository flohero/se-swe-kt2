package at.fhooe;

import java.io.*;

public class Serialization {

    public static class Person implements Serializable, Cloneable {
        private final String name;

        public Person(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        private  void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
            in.defaultReadObject();
//            boolean x = in.readBoolean();
        }

        private  void writeObject(ObjectOutputStream out) throws IOException {
            out.defaultWriteObject();
//            out.writeBoolean(true);
        }

        @Override
        public String toString() {
            return "Person {" +
                    "name = '" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {

        // Serialize to file
        try (ObjectOutputStream out =
                     new ObjectOutputStream(new FileOutputStream("test.er"))) {
            out.writeObject(new Person("Test"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Serialize from file
        try (ObjectInputStream in =
                     new ObjectInputStream(new FileInputStream("test.er"))) {
            Person p = (Person) in.readObject();
            System.out.println(p);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
