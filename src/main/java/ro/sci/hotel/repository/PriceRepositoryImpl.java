package ro.sci.hotel.repository;

import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import ro.sci.hotel.model.util.Currency;
import ro.sci.hotel.model.util.Price;

/**
 * Implementation for PriceRepository DAO
 */
@Repository("priceRepository")
public class PriceRepositoryImpl extends BaseRepository implements PriceRepository<Price> {

    private static final Logger LOGGER = Logger.getLogger("Hotel");

    private static final String DATABASE_ERROR = "Database error!";

    private static final String EXCEPTION_THROWN = "Exception thrown";
    private static final String WRITING_PRICE_HAS_FINISHED = "Writing Price has finished";
    private static final String SQL_INSERT_INTO_PRICE_VALUE_CURRENCY_VALUES = "INSERT INTO price(value,currency) values(?,?)";
    private static final String PRICE_UPDATE_HAS_FISNISHED = "Price update has fisnished";
    private static final String SQL_UPDATE_PRICE = "UPDATE price " + "SET value=?, currency=? WHERE id = ?";
    private static final String SQL_DELETE_FROM_PRICE_WHERE_ID = "DELETE FROM price where id=?";
    private static final String PRICE_ROW_HAS_BEEN_DELETED = "Price row has been deleted";
    private static final String SQL_SELECT_FROM_PRICE_WHERE_ID = "SELECT * FROM price WHERE id=?";
    private static final String VALUE = "value";
    private static final String CURRENCY = "currency";

    @Override
    public List<Price> getAll() {
        List<Price> prices = new ArrayList<>();

        try (Connection conn = newConnection(); Statement stm = conn.createStatement(); ResultSet rs = stm.executeQuery("SELECT * FROM price")) {

            while (rs.next()) {

                Price price = new Price();
                price.setId(rs.getInt("id"));
                price.setValue(rs.getDouble(VALUE));
                price.setCurrency(Currency.valueOf(rs.getString(CURRENCY)));

                prices.add(price);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return prices;
    }

    @Override
    public void create(Price price) {

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_INSERT_INTO_PRICE_VALUE_CURRENCY_VALUES)) {

            stm.setDouble(1, price.getValue());
            stm.setString(2, price.getCurrency()
                                  .toString());

            stm.execute();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, WRITING_PRICE_HAS_FINISHED);
    }

    @Override
    public void update(Price price) {

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_UPDATE_PRICE)) {

            stm.setDouble(1, price.getValue());
            stm.setString(2, price.getCurrency()
                                  .toString());

            stm.setInt(5, price.getId());

            stm.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, PRICE_UPDATE_HAS_FISNISHED);
    }

    @Override
    public void delete(Price price) {

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_DELETE_FROM_PRICE_WHERE_ID)) {

            stm.setInt(1, price.getId());
            stm.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        LOGGER.log(Level.INFO, PRICE_ROW_HAS_BEEN_DELETED);
    }

    @Override
    public Price searchById(Integer id) {

        Price price = new Price();

        try (Connection conn = newConnection(); PreparedStatement stm = conn.prepareStatement(SQL_SELECT_FROM_PRICE_WHERE_ID)) {

            stm.setInt(1, id);

            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                price.setId(rs.getInt("id"));
                price.setValue(rs.getDouble(VALUE));
                price.setCurrency(Currency.valueOf(rs.getString(CURRENCY)));
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            LOGGER.log(Level.WARNING, DATABASE_ERROR);
            throw new RuntimeException(EXCEPTION_THROWN);
        }

        return price;
    }
}
