package data;

import client.Client;
import client.managers.CheckValues;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * Класс, представляющий лабораторную работу.
 * Содержит полную информацию о лабораторной работе: идентификатор, название,
 * координаты, дату создания, минимальный балл, сложность и автора.
 * Реализует интерфейс {@link Comparable} для сравнения работ по набору критериев.
 *
 * @author Елена
 * @see Coordinates
 * @see Difficulty
 * @see Person
 */

public class LabWork implements Comparable<LabWork>, Serializable {
    private  Integer id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private double minimalPoint; //Значение поля должно быть больше 0
    private Difficulty difficulty; //Поле может быть null
    private Person author;//Поле не может быть null


    private LabWork(Builder builder){
        id = builder.id;
        name = builder.name;
        coordinates = builder.coordinates;
        creationDate = builder.creationDate;
        minimalPoint = builder.minimalPoint;
        difficulty = builder.difficulty;
        author = builder.author;
    }


    public static class Builder {
        Integer id ;//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
        String name ; //Поле не может быть null, Строка не может быть пустой
        Coordinates coordinates ; //Поле не может быть null
        LocalDate creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
        double minimalPoint ; //Значение поля должно быть больше 0
        Difficulty difficulty; //Поле может быть null
        Person author;

        public Builder(){
            id = Generate.generateId();//Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
            name = setName(); //Поле не может быть null, Строка не может быть пустой
            coordinates = new Coordinates(); //Поле не может быть null
            creationDate= LocalDate.now(); //Поле не может быть null, Значение этого поля должно генерироваться автоматически
            minimalPoint = setMinimalPoint(); //Значение поля должно быть больше 0
            difficulty = setDifficult(); //Поле может быть null
            author = new Person();
        }
        public Builder(boolean n){}

        public LabWork doLab(){
            return new LabWork(this);
        }

        public Builder nameS(String name){
            this.name = name;
            return this;
        }
        public Builder idS(Integer id){
            this.id = id;
            return this;
        }
        public Builder coordinatesS(Coordinates cor){
            this.coordinates = cor;
            return this;
        }
        public Builder creationDateS(LocalDate date){
            creationDate = date;
            return this;
        }
        public Builder minimalPointS(double minimalPoint){
            this.minimalPoint = minimalPoint;
            return this;
        }
        public Builder difficultS(Difficulty difficulty){
            this.difficulty = difficulty;
            return this;
        }
        public Builder authorS(Person author){
            this.author= author;
            return this;
        }




        /**
         * Устанавливает сложность лабораторной работы через пользовательский ввод.
         * Предлагает выбрать из списка доступных значений enum {@link Difficulty}.
         * При некорректном вводе устанавливает значение по умолчанию NORMAL.
         */

         private Difficulty setDifficult() {
            Client.inout.write("Выберите сложность лабы: easy, normal, hard, impossible, insane");
            String test = CheckValues.checkValuesNull("сложность ");
            Difficulty difficult = switch (test) {
                case "easy" -> Difficulty.EASY;
                case "normal" -> Difficulty.NORMAL;
                case "hard" -> Difficulty.HARD;
                case "impossible" -> Difficulty.IMPOSSIBLE;
                case "insane" -> Difficulty.INSANE;
                default -> Difficulty.NORMAL;
            };
            return difficult;
        }

        /**
         * Устанавливает минимальный балл через пользовательский ввод.
         * Проверяет, что введённое значение является числом типа double и больше 0.
         * При некорректном вводе запрашивает значение повторно.
         */

        private double setMinimalPoint () {
            double minimalPoint;
            while (true) {
                Client.inout.write("Введите минимальное значение:");
                String test = CheckValues.checkValuesNull("минимальное значение").replace(",", ".");
                if (!test.contains(".")){test = test+ ".0";}
                try {
                    minimalPoint = Double.parseDouble(test);
                    if (!test.equals(String.valueOf(minimalPoint))){
                        Client.inout.write("Было превышено допустимое количество символов для double");
                    }
                    else if (minimalPoint <= 0) {
                        Client.inout.write("Минимальное значение не может быть меньше 0");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    Client.inout.write("Минимальное значение должно быть типа double");
                }
            }
            return minimalPoint;
        }

        private String setName () {
            Client.inout.write("Введите название лабораторной работы:");
            name = CheckValues.checkValuesNull("Название лабораторной работы");
            return name;
        }
    }

    public Person getAuthor() {
        return author;
    }

    public Integer getId() {
        return id;
    }

    public Double getMinimalPoint() {
        return minimalPoint;
    }

    public String getName() {
        return name;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return " | "
                + id
                + " | " + name
                + " | " + coordinates.getX() + ", " + coordinates.getY()
                + " | " + creationDate
                + " | " + minimalPoint
                + " | " + difficulty
                + " | " + author.toString()
                + " | ";
    }

    @Override
    public int compareTo(LabWork o) {
        int nameCompare = Long.compare(this.name.length(), o.name.length());
        if (nameCompare != 0) {
            return nameCompare;
        }

        int coordCompare = coordinates.compareTo(o.coordinates);
        if (coordCompare != 0) {
            return coordCompare;
        }

        int minCompare = Double.compare(this.minimalPoint, o.minimalPoint);
        if (minCompare != 0) {
            return minCompare;
        }

        int personCompare = author.compareTo(o.author);
        if (personCompare != 0) {
            return personCompare;
        }

        return Integer.compare(this.id, o.id);
    }

    public void setId(Integer id) {
        this.id = id;
    }
}