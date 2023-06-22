package pl.devfinder.business.dao;


import pl.devfinder.domain.Employer;

import java.util.List;

public interface EmployerDAO {
    List<Employer> findAll();

    void save(Employer employer);
    // sortuj liczba ofert
    // sortuj
    // jakie miasto
    // sortuj nazwa firmy

}
