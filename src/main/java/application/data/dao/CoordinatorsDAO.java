package application.data.dao;

import application.data.dto.CoordinatorsDTO;
import application.db.DatabaseConnector;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class CoordinatorsDAO implements DAO<CoordinatorsDTO, Integer> {

    DatabaseConnector dataBase = new DatabaseConnector();
    @Override
    public CoordinatorsDTO get(Integer id) {
        String sql = "SELECT coordinator_id,coordinator_name FROM university.coordinators WHERE id=?";
        CoordinatorsDTO coordinatorDTO = new CoordinatorsDTO();

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            coordinatorDTO.setId(resultSet.getInt("coordinator_id"));
            coordinatorDTO.setName(resultSet.getString("coordinator_name"));
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
        return coordinatorDTO;
    }

    @Override
    public List<CoordinatorsDTO> getAll() {
        String sql = "SELECT coordinator_id, coordinator_name FROM university.coordinator";
        ArrayList<CoordinatorsDTO> coordinatorsList = new ArrayList<>();

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                CoordinatorsDTO coordinatorDTO = new CoordinatorsDTO();
                coordinatorDTO.setId(resultSet.getInt("coordinator_id"));
                coordinatorDTO.setName(resultSet.getString("coordinator_name"));
                coordinatorsList.add(coordinatorDTO);
            }
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
        return coordinatorsList;
    }

    @Override
    public void create(CoordinatorsDTO coordinatorsDto) {
        String sql = "INSERT INTO university.coordinators VALUES (?)";
        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, coordinatorsDto.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
    }

    @Override
    public void update(CoordinatorsDTO coordinatorsDto) {
        String sql = "UPDATE university.coordinators SET coordinator_name=? WHERE coordinators id=?";
        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, coordinatorsDto.getName());
            preparedStatement.setInt(2,coordinatorsDto.getId());
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
    }

    @Override
    public void deleteById(Integer id) {
        String sql = "DELETE FROM university.coordinators WHERE coordinator_id=?";
        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeQuery();
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
    }
}
