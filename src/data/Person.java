package data;


import client.Client;
import client.managers.CheckValues;

import java.io.Serializable;

/**
 * Класс, представляющий автора лабораторной работы.
 * Содержит персональные данные: имя, рост, вес и паспортные данные.
 * Реализует интерфейс {@link Comparable} для сравнения авторов по набору критериев.
 *
 * @author Елена
 */

public class Person implements Comparable<Person> , Serializable {
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Double height; //Поле не может быть null, Значение поля должно быть больше 0
    private Double weight; //Поле не может быть null, Значение поля должно быть больше 0
    private String passportID; //Длина строки не должна быть больше 26, Строка не может быть пустой, Поле может быть null

    public Person() {
        Client.inout.write("Введите данные автора лабораторной работы");
        setName();
        setHeight();
        setWeight();
        setPassportID();
    }

    public Person(String name, Double height, Double weight, String passportID) {
        this.name = name;
        this.height = height;
        this.weight = weight;
        this.passportID = passportID;
    }

    /**
     * Устанавливает паспортные данные через пользовательский ввод.
     * Проверяет, что строка не пустая и её длина не превышает 26 символов.
     */

    private void setPassportID() {
        Client.inout.write("Введите паспортные данные автора:");
        String passportID = Client.inout.read();
        while (passportID.length() > 26 || passportID.trim() == " ") {
            Client.inout.write("Значение не должно быть пустой строкой и должно быть меньше 26");
            Client.inout.write("Введите паспортные данные автора:");
            passportID = Client.inout.read();
        }
        this.passportID = passportID;
    }

    private void setWeight() {
        while (true) {
            Client.inout.write("Введите вес автора:");
            try {
                String testWeight = CheckValues.checkValuesNull("вес автора").replace(",",".");
                if (!testWeight.contains(".")){testWeight = testWeight+ ".0";}
                double weight = Double.parseDouble(testWeight);
                if (weight <= 0) {
                    Client.inout.write("Вес должен быть больше нуля");
                } else if (!testWeight.equals(String.valueOf(weight))) {
                    Client.inout.write("Данные не подходят для типа double");
                } else {
                    this.weight = (double) weight;
                    break;
                }
            } catch (NumberFormatException e) {
                Client.inout.write("Вес должно быть типа double");
            }
        }
    }

    private void setHeight() {
        while (true) {
            Client.inout.write("Введите рост автора:");
            try {
                String testHeight = CheckValues.checkValuesNull("рост автора").replace(",",".");
                if (!testHeight.contains(".")){testHeight = testHeight+ ".0";}
                double height = Double.parseDouble(testHeight);
                if (height <= 0 ) {
                    Client.inout.write("Рост должен быть больше нуля");
                } else if (!testHeight.equals(String.valueOf(height))){
                    Client.inout.write("Данные не подходят");
                } else {
                    this.height = (double) height;
                    break;
                }
            } catch (NumberFormatException e) {
                Client.inout.write("Рост должно быть типа double");
            }
        }
    }

    private void setName() {
        Client.inout.write("Введите имя автора:");
        String testName = CheckValues.checkValuesNull("имя автора");
        name = testName;
    }


    @Override
    public String toString() {
        return "Person: "
                + "имя: " + name + ", "
                + "рост: " + height + ", "
                + "вес: " + weight + ", "
                + "паспортные данные: " + passportID;
    }

    @Override
    public int compareTo(Person person) {
        int nameCompare = Long.compare(this.name.length(), person.name.length());
        if (nameCompare != 0) {
            return nameCompare;
        }

        int hCompare = Double.compare(this.height, person.height);
        if (hCompare != 0) {
            return hCompare;
        }

        int wCompare = Double.compare(this.weight, person.weight);
        if (wCompare != 0) {
            return wCompare;
        }
        return Long.compare(this.passportID.length(), person.passportID.length());
    }

    public String getName() {
        return name;
    }

    public String getPassportID() {
        return passportID;
    }

    public Double getHeight() {
        return height;
    }

    public Double getWeight() {
        return weight;
    }
}
