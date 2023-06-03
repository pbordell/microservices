package com.pbs.micro.report.dao;

//import java.util.Map;
import com.pbs.micro.report.model.Country;

/**
 * Methods a PersonSupportDao class must implement to provide
 * additional information related to a Person.
 * 
 * @author bruce phillips
 * @author antonio sánchez
 */
public interface PersonSupportDao {

    Country[] getCountries();
    
    String[] getCarModels();
    
    String[] getSports();
    
    String[] getGenders();
    
}
