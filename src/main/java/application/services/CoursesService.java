package application.services;

import application.data.dto.CoursesDTO;

import java.util.Collection;

public class CoursesService implements CRUDService<CoursesDTO> {


    @Override
    public CoursesDTO getById(Integer id) {
        return null;
    }

    @Override
    public Collection<CoursesDTO> getAll() {
        return null;
    }

    @Override
    public void create(CoursesDTO item) {

    }

    @Override
    public void update(Integer id, CoursesDTO item) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
