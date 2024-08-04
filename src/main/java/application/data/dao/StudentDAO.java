package application.data.dao;

import application.data.dto.StudentsDTO;
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
public class StudentDAO implements DAO<StudentsDTO, Integer>{
    DatabaseConnector dataBase = new DatabaseConnector();
    @Override
    public StudentsDTO get(Integer id) {
        String sql = "SELECT student_id, student_name FROM university.students WHERE student_id=?";
        StudentsDTO studentsDTO = new StudentsDTO();

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            studentsDTO.setId(resultSet.getInt("student_id"));
            studentsDTO.setName(resultSet.getString("student_name"));
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
        return studentsDTO;
    }

    @Override
    public List<StudentsDTO> getAll() {
        String sql = "SELECT student_id, student_name FROM university.students";
        ArrayList<StudentsDTO> studentsList = new ArrayList<>();

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery(sql);
            while (resultSet.next()) {
                StudentsDTO studentsDTO = new StudentsDTO();
                studentsDTO.setId(resultSet.getInt("student_id"));
                studentsDTO.setName(resultSet.getString("student_name"));
                studentsList.add(studentsDTO);
            }
        } catch (SQLException ex) {
            log.error("Couldn't get access to database: " + ex);
            dataBase.closeConnection();
        } catch (IOException ex) {
            log.error("Input/Output error: " + ex);
            dataBase.closeConnection();
        }
        return studentsList;
    }

    @Override
    public void create(StudentsDTO studentsDTO) {
        String sql = "INSERT INTO university.students VALUES (?)";

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentsDTO.getName());
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
    public void update(StudentsDTO studentsDTO) {
        String sql = "UPDATE university.students SET student_name=? WHERE student_id=?";

        try (Connection connection = dataBase.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1,studentsDTO.getName());
            preparedStatement.setInt(2,studentsDTO.getId());
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
        String sql = "DELETE FROM university.students WHERE student_id=?";
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
