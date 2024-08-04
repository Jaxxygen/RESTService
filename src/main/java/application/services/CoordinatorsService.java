package application.services;

import application.data.dao.CoordinatorsDAO;

import java.util.Collection;

public class CoordinatorsService implements CRUDService<CoordinatorsDAO> {

    @Override
    public CoordinatorsDAO getById(Integer id) {
        return null;
    }

    @Override
    public Collection<CoordinatorsDAO> getAll() {
        return null;
    }

    @Override
    public void create(CoordinatorsDAO item) {

    }

    @Override
    public void update(Integer id, CoordinatorsDAO item) {

    }

    @Override
    public void deleteById(Integer id) {

    }
}
