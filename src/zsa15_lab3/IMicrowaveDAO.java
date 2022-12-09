package zsa15_lab3;

import java.util.List;
import javax.sql.DataSource;

public interface IMicrowaveDAO {
    void setDataSource(DataSource ds);                          
    void insert(Microwave customer);                                
    void append(String Brand, String Model);
    void appendOnlyBrand(String Brand);
    void deleteByBrand(String Brand);    
    void deleteByModel(String Model);
    void delete(String Brand, String Model);                 
    void deleteAll();                                           
    void update(String newModel, String oldModel);
    void updateBrand(String newBrand, String oldBrand);
    List<Microwave> findByBrand(String Brand);
    List<Microwave> findByModel(String Model);
    List<Microwave> select(String Brand, String Model);
    List<Microwave> selectByBrand(String Brand);
    List<Microwave> selectByModel(String Model);
    List<Microwave> selectAll();
}
