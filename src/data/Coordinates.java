package data;

import client.Client;
import client.managers.CheckValues;

import java.io.Serializable;

/**
 * Класс, представляющий координаты лабораторной работы.
 * Содержит координаты X и Y с ограничениями на допустимые значения.
 * Реализует интерфейс {@link Comparable} для сравнения координат.
 *
 * @author Елена
 * @version 1.0
 */

public class Coordinates implements Comparable<Coordinates>, Serializable {
    private Float x; //Максимальное значение поля: 254, Поле не может быть null
    private Long y; //Значение поля должно быть больше -991, Поле не может быть null

    public Coordinates() {
        setX();
        setY();
    }

    public Coordinates(Float x, Long y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Устанавливает координату X через пользовательский ввод.
     * Проверяет, что введённое значение является числом типа float и не превышает 254.
     */

    private void setX() {
        Client.inout.write("Введите значение X:");

        while (true) {
            try {
                String testX = CheckValues.checkValuesNull("X").replace(",",".");
                if (!testX.contains(".")){testX = testX + ".0";}
                float x = Float.parseFloat(testX);
                if (x > 254 ) {
                    Client.inout.write("X не может быть больше 254");
                    Client.inout.write("Введите значение X для координат:");
                } else if (!testX.equals(String.valueOf(x))){
                    Client.inout.write("Данные не подходят для float.");
                }
                else {
                    this.x = x;
                    break;
                }
            } catch (NumberFormatException e) {
                Client.inout.write("X должно быть типа float");
            }
        }

    }

    /**
     * Устанавливает координату Y через пользовательский ввод.
     * Проверяет, что введённое значение является числом типа long и больше -991.
     */

    private void setY() {
        while (true) {
            Client.inout.write("Введите значение Y:");
            String testY = CheckValues.checkValuesNull("Y");
            try {
                long y = Long.parseLong(testY);
                if (y < -991) {
                    Client.inout.write("Y не может быть меньше -991");
                } else {
                    this.y = (long) y;
                    break;
                }
            } catch (NumberFormatException e) {
                Client.inout.write("Y должно быть типа long");
            }
        }

    }


    public Float getX() {
        return x;
    }

    public Long getY() {
        return y;
    }

    @Override
    public int compareTo(Coordinates coordinates) {
        int xCompare = Float.compare(this.x, coordinates.x);
        if (xCompare != 0) {
            return xCompare;
        }

        return Long.compare(this.y, coordinates.y);
    }
}
