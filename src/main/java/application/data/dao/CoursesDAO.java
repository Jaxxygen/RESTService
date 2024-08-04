package application.data.dao;

import application.data.dto.CoursesDTO;
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
public class CoursesDAO implements DAO<CoursesDTO, Integer>{
    DatabaseConnector dataBase = new DatabaseConnector();
    @Override
    public CoursesDTO get(Integer id) {
        String sql = "SELECT courses_id, courses_name FROM university.courses WHERE course_id=?";
        CoursesDTO coursesDTO = new CoursesDTO();

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            coursesDTO.setId(resultSet.getInt("course_id"));
            coursesDTO.setName(resultSet.getString("course_name"));
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
        return coursesDTO;
    }

    @Override
    public List<CoursesDTO> getAll() {
        String sql = "SELECT course_id, course_name FROM university.courses";
        ArrayList<CoursesDTO> coursesList = new ArrayList<>();

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                CoursesDTO coursesDTO = new CoursesDTO();
                coursesDTO.setId(resultSet.getInt("course_id"));
                coursesDTO.setName(resultSet.getString("course_name"));
                coursesList.add(coursesDTO);
            }
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
        return coursesList;
    }

    @Override
    public void create(CoursesDTO coursesDTO) {
        String sql = "INSERT INTO university.courses VALUES (?)";

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, coursesDTO.getName());
            preparedStatement.executeQuery();
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
    }

    @Override
    public void update(CoursesDTO coursesDTO) {
        String sql = "UPDATE university.courses SET course_name=? WHERE course_id=?";

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,coursesDTO.getName());
            preparedStatement.setInt(2,coursesDTO.getId());
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
        String sql = "DELETE FROM university.courses WHERE course_id=?";
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
