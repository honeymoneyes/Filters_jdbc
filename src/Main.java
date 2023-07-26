import dao.TicketDao;
import dto.TicketFilter;
import model.Ticket;

import java.sql.SQLException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws SQLException {
        TicketFilter ticketFilter = new TicketFilter(3, 0, "Иван Иванов", "A", "112233");
        List<Ticket> all = TicketDao.getInstance().findAll(ticketFilter);
        all.forEach(System.out::println);
    }
}