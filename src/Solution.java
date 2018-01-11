package com.javarush.task.task20.task2008;

import java.io.Serializable;

/*
Как сериализовать Singleton?
Два десериализованных объекта singleton и singleton1 имеют разные ссылки в памяти, а должны иметь одинаковые.
В класс Singleton добавь один метод (погуглите), чтобы после десериализации ссылки на объекты были равны.
Метод main не участвует в тестировании.


Требования:
1. Класс Solution.Singleton должен поддерживать интерфейс Serializable.
2. В классе Solution.Singleton должен быть реализован метод readResolve без параметров.
3. Метод readResolve должен возвращать синглтон (ourInstance).
4. Метод readResolve должен быть приватным.

public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton instance = Singleton.getInstance();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //Serializing the singleton instance
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(instance);
        oos.close();

        //Recreating the instance by reading the serialized object data add
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        Singleton singleton = (Singleton) ois.readObject();
        ois.close();

        //Recreating the instance AGAIN by reading the serialized object data add
        byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        ObjectInputStream ois2 = new ObjectInputStream(byteArrayInputStream);
        Singleton singleton1 = (Singleton) ois2.readObject();
        ois2.close();

        //The singleton behavior has been broken
        System.out.println("Instance reference check : " + singleton.getInstance());
        System.out.println("Instance reference check : " + singleton1.getInstance());
        System.out.println("=========================================================");
        System.out.println("Object reference check : " + singleton);
        System.out.println("Object reference check : " + singleton1);
    }

    public static class Singleton implements Serializable {
        private static Singleton ourInstance;

        public static Singleton getInstance() {
            if (ourInstance == null) {
                ourInstance = new Singleton();
            }
            return ourInstance;
        }

        private Singleton() {
        }
    }
}
*/

import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/*
Как сериализовать Singleton?
*/
public class Solution implements Serializable {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Singleton instance = Singleton.getInstance();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        //Serializing the singleton instance
        ObjectOutputStream oos = new ObjectOutputStream(byteArrayOutputStream);
        oos.writeObject(instance);
        oos.close();

        //Recreating the instance by reading the serialized object data add
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        ObjectInputStream ois = new ObjectInputStream(byteArrayInputStream);
        Singleton singleton = (Singleton) ois.readObject();
        ois.close();

        //Recreating the instance AGAIN by reading the serialized object data add
        byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());

        ObjectInputStream ois2 = new ObjectInputStream(byteArrayInputStream);
        Singleton singleton1 = (Singleton) ois2.readObject();
        ois2.close();

        //The singleton behavior has been broken
        System.out.println("Instance reference check : " + singleton.getInstance());
        System.out.println("Instance reference check : " + singleton1.getInstance());
        System.out.println("=========================================================");
        System.out.println("Object reference check : " + singleton);
        System.out.println("Object reference check : " + singleton1);
    }

    public static class Singleton implements Serializable {
        private static Singleton ourInstance;

        public static Singleton getInstance() {
            if (ourInstance == null) {
                ourInstance = new Singleton();
            }
            return ourInstance;
        }

        private Object readResolve() {
            return ourInstance;
        }

        private Singleton() {
        }
    }
}