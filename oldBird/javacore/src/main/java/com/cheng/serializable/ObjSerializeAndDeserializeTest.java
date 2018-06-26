package com.cheng.serializable;

import java.io.*;

/**
 * @author cheng_mboy
 */
public class ObjSerializeAndDeserializeTest {


    public static void main(String[] args) {
         serializePerson();
        //deSerializePerson();
    }

    private static void serializePerson() {
        Person person = new Person();
        person.setAge(30);
        person.setName("tom");
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("person.text"))) {
            outputStream.writeObject(person);
            System.out.println("serialize success!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void deSerializePerson() {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("person.text"))) {
            Person person =(Person) inputStream.readObject();
            System.out.println("deSerialize success!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
