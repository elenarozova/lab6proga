package data;

import java.io.Serializable;

/**
 * Перечисление возможных уровней сложности лабораторной работы.
 * Содержит пять уровней сложности от простого до невыполнимого.
 *
 * @author Елена
 */

public enum Difficulty implements Serializable {
    EASY,
    NORMAL,
    HARD,
    IMPOSSIBLE,
    INSANE;
}
