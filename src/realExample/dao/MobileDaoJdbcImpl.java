package realExample.dao;

import realExample.ConnectionManager.ConnectionManager;
import realExample.ConnectionManager.ConnectionManagerJdbcImpl;
import realExample.pojo.Manufacturer;
import realExample.pojo.Mobile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MobileDaoJdbcImpl implements MobileDao {
    private static ConnectionManager connectionManager =
            ConnectionManagerJdbcImpl.getInstance();

    @Override
    public boolean addMobile(Mobile mobile) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "INSERT INTO mobile values (?, ?, ?, ?)");
            preparedStatement.setInt(1, mobile.getId());
            preparedStatement.setString(2, mobile.getModel());
            preparedStatement.setFloat(3, mobile.getPrice());
            preparedStatement.setInt(4, mobile.getManufacturer());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public Mobile getMobileById(Integer id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM mobile WHERE id = ?");
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Mobile mobile = new Mobile(
                        resultSet.getInt(1),
                        resultSet.getString(2),
                        resultSet.getFloat(3),
                        resultSet.getInt(4));
                return mobile;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean updateMobileById(Mobile mobile) {

        try (Connection connection = connectionManager.getConnection();) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE mobile SET model=?, price=?, manufacturer_id=? " +
                            "WHERE id=?");
            preparedStatement.setString(1, mobile.getModel());
            preparedStatement.setFloat(2, mobile.getPrice());
            preparedStatement.setInt(3, mobile.getManufacturer());
            preparedStatement.setInt(4, mobile.getId());
            preparedStatement.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean deleteMobileById(Integer id) {
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM mobile WHERE id=?");
            preparedStatement.setInt(1, id);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public String getManufacturerById(Mobile mobile) {
        Integer manufacturerId = mobile.getManufacturer();
        try (Connection connection = connectionManager.getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT manufacturer.name, manufacturer.country, mobile.model, mobile.price " +
                            "FROM manufacturer JOIN mobile ON manufacturer.id=? AND mobile.manufacturer_id=?;");
            preparedStatement.setInt(1, manufacturerId);
            preparedStatement.setInt(2, manufacturerId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                return "Brand: " + resultSet.getString(1) +
                        ", country: " + resultSet.getString(2) +
                        ", model: " + resultSet.getString(3) +
                        ", price: " + resultSet.getString(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}