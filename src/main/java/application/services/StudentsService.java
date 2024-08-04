package application.services;

import application.data.dto.StudentsDTO;

import java.util.Collection;

public class StudentsService implements CRUDService<StudentsDTO> {

    @Override
    public StudentsDTO getById(Integer id) {
        return null;
    }

    @Override
    public Collection<StudentsDTO> getAll() {
        return null;
    }

    @Override
    public void create(StudentsDTO item) {

    }

    @Override
    public void update(Integer id, StudentsDTO item) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
