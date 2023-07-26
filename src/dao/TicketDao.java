package dao;

import dto.TicketFilter;
import model.Ticket;
import util.ConnectionManager;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static java.util.stream.Collectors.joining;

public final class TicketDao {

    public static final TicketDao INSTANCE = new TicketDao();
    public static final String SELECT_FROM_TICKET = """
            SELECT *
            FROM ticket
            """;
    private TicketDao() {
    }

    public List<Ticket> findAll(TicketFilter filter) throws SQLException {
        List<Object> parameters = new ArrayList<>();
        List<String> whereSql = new ArrayList<>();

        if (filter.passengerNo() != null) {
            parameters.add(filter.passengerNo());
            whereSql.add("passenger_no = ?");
        }

        if (filter.passengerName() != null) {
            parameters.add(filter.passengerName());
            whereSql.add("passenger_name = ?");
        }

        if (filter.seatNo() != null) {
            parameters.add("%" + filter.seatNo() + "%");
            whereSql.add("seat_no LIKE ?");
        }
        parameters.add(filter.limit());
        parameters.add(filter.offset());

        String where = whereSql.stream()
                .collect(joining("\nAND ", "WHERE ", "\nLIMIT ?\nOFFSET ?"));

        String sql = SELECT_FROM_TICKET + where;
        try (Connection connection = ConnectionManager.open()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            for (int i = 0; i < parameters.size(); i++) {
                preparedStatement.setObject(i + 1, parameters.get(i));
            }
            System.out.println(preparedStatement);
            System.out.println();

            List<Ticket> tickets = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                tickets.add(getTicket(resultSet));
            }
            return tickets;
        }
    }
    private Ticket getTicket(ResultSet resultSet) {
        try {
            return new Ticket(
                    resultSet.getObject("id", BigInteger.class),
                    resultSet.getObject("passenger_no", String.class),
                    resultSet.getObject("passenger_name", String.class),
                    resultSet.getObject("flight_id", BigInteger.class),
                    resultSet.getObject("seat_no", String.class),
                    resultSet.getObject("cost", BigDecimal.class)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static TicketDao getInstance() {
        return INSTANCE;
    }
}
