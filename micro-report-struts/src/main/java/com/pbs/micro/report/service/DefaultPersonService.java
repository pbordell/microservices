package com.pbs.micro.report.service;

import com.pbs.micro.report.dao.MemoryPersonDao;
import com.pbs.micro.report.dao.MemoryPersonSupportDao;
import com.pbs.micro.report.dao.PersonDao;
import com.pbs.micro.report.dao.PersonSupportDao;
import com.pbs.micro.report.model.Country;
import com.pbs.micro.report.model.Person;

/**
 * Implement Services needed to edit and save
 * a Person object's state.  
 * 
 * @author bruce phillips
 * @author antonio sanchez
 */

public class DefaultPersonService implements PersonService {
    PersonDao personDao;
    PersonSupportDao personSupportDao;
    
    public DefaultPersonService() {
        personDao = new MemoryPersonDao();
        personSupportDao = new MemoryPersonSupportDao();
    }

    @Override
    public Person getPerson(Integer id) {
        return personDao.getPerson(id);
    }

    @Override
    public Person[] getAllPersons() {
        return personDao.getAllPersons();
    }

    @Override
    public void updatePerson(Person personBean) {
        personDao.updatePerson(personBean);
    }

    @Override
    public void insertPerson(Person personBean) {
        personDao.insertPerson(personBean);
    }

    @Override
    public void deletePerson(Integer id) {
        personDao.deletePerson(id);
    }

    @Override
    public Country[] getCountries() {
        return personSupportDao.getCountries();
    }

    @Override
    public String[] getCarModels() {
        return personSupportDao.getCarModels();
    }

    @Override
    public String[] getSports() {
        return personSupportDao.getSports();
    }

    @Override
    public String[] getGenders() {
        return personSupportDao.getGenders();
    }
}
