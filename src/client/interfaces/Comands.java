package client.interfaces;

import data.CommandPacket;

/**
 * Базовый интерфейс для всех команд
 * Все конкретные команды должны реализовывать этот интерфейс и определять
 * логику выполнения в методе {@link #implementCommand()}.
 *
 * @author Елена
 */

public interface Comands {
    CommandPacket implementCommand(String[] args);
}
