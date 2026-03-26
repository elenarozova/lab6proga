package client.managers;

import client.Client;

/**
 * Утилитный класс для проверки и валидации пользовательского ввода.
 * Содержит статические методы, которые обеспечивают корректность вводимых данных,
 * предотвращая сохранение пустых или некорректных значений.
 *
 * @author Елена
 */

public class CheckValues {
    public static String checkValuesNull(String name) {
        String test = Client.inout.read();
        while (test.trim().isEmpty()) {
            Client.inout.write(name + " не может быть null");
            Client.inout.write("Введите значение " + name + " :");
            test = Client.inout.read();
        }
        return test.trim();
    }
}
