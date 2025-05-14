package com.example.booking.repository;

import com.example.booking.model.Passenger;
import com.example.booking.model.Ticket;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

/**
 * Реализация интерфейса {@link TicketRepository}.
 *
 * <p>Этот класс управляет хранилищем билетов. Для хранения билетов можно использовать коллекцию Map:
 * {@code Map<String, Ticket>} — ключом будет {@code ticketId}, значением — объект {@code Ticket}.
 *
 * <p>Если нужно найти билеты по пассажиру, можно просматривать все билеты и сравнивать их владельцев.
 *
 * <p>Ниже описаны шаги для реализации каждого метода. Тело методов пока пустое — реализовать их должны студенты.
 */
public class TicketRepositoryImpl implements TicketRepository {

    // Внутреннее хранилище всех билетов: ticketId -> Ticket
    private final Map<String, Ticket> storage = new HashMap<>();

    /**
     * Находит билет по его уникальному идентификатору.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Проверить, что параметр {@code ticketId} не равен null.</li>
     *     <li>Использовать метод {@code get(ticketId)} у {@code storage}, чтобы найти нужный билет.</li>
     *     <li>Если билет найден — вернуть его. Если не найден — вернуть {@code null}.</li>
     * </ol>
     *
     * @param ticketId уникальный идентификатор билета
     * @return найденный билет или null, если не найден
     */
    @Override
    public String findById(String ticketId) {
        Ticket ticket = storage.get(ticketId);
        return ticket != null ? ticket.toString() : "Билет не найден";
    } // реализацию нужно написать по инструкции выше


    /**
     * Находит все билеты, принадлежащие заданному пассажиру.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Создать пустой список, куда будут добавляться найденные билеты.</li>
     *     <li>Пройтись циклом по всем билетам в {@code storage.values()}.</li>
     *     <li>Для каждого билета сравнить пассажира внутри билета с переданным пассажиром.</li>
     *     <li>Если пассажиры совпадают — добавить билет в список.</li>
     *     <li>После окончания цикла вернуть итоговый список (он может быть пустым).</li>
     * </ol>
     *
     * <p><b>Как сравнивать пассажиров:</b> можно использовать метод {@code equals}, если он корректно переопределён в классе Passenger,
     * или сравнивать, например, по паспорту.
     *
     * @param passenger пассажир, для которого нужно найти билеты
     * @return список билетов, принадлежащих этому пассажиру
     */
    @Override
    public List<Ticket> findByPassenger(Passenger passenger) {
        List<Ticket> tickets = new ArrayList<>();
        if (passenger != null) {
            for (Ticket ticket : storage.values()) {
                if (ticket.getPassenger().equals(passenger)) {
                    tickets.add(ticket);
                }
            }
        }
        return tickets;
    }

@Override
public List<Ticket> findAll() {
    return new ArrayList<>(storage.values());
}

    @Override
    public List<Ticket> findByStatus(String status) {
        return List.of();
    }

    /**
     * Сохраняет новый билет в хранилище.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Проверить, что билет не равен {@code null}, и у него есть валидный {@code ticketId}.</li>
     *     <li>Использовать {@code put(ticketId, ticket)} для добавления билета в {@code storage}.</li>
     *     <li>Если билет с таким {@code ticketId} уже есть — он будет перезаписан (это допустимо).</li>
     * </ol>
     *
     * <p>Можно добавить проверку: если такой билет уже есть, не сохранять его и вывести сообщение или выбросить исключение — по желанию.
     *
     * @param ticket билет, который нужно сохранить
     */
    @Override
    public boolean saveTicket(Ticket ticket) {
        // реализацию нужно написать по инструкции выше
        return false;
    }

    /**
     * Удаляет билет по его идентификатору.
     *
     * <p><b>Как реализовать (шаги):</b>
     * <ol>
     *     <li>Проверить, что {@code ticketId} не равен null.</li>
     *     <li>Использовать метод {@code remove(ticketId)} у {@code storage}.</li>
     *     <li>Если билет существует — он будет удалён, если нет — ничего не произойдёт (это нормально).</li>
     * </ol>
     *
     * <p>Метод не должен выбрасывать ошибку, если билет не найден.
     *
     * @param ticketId идентификатор билета, который нужно удалить
     */
    @Override
    public boolean delete(String ticketId) {
        // реализацию нужно написать по инструкции выше
        return false;
    }

    @Override
    public boolean update(Ticket ticket) {
        return false;
    }
}