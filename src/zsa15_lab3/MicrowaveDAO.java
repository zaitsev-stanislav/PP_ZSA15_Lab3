package zsa15_lab3;

import java.util.List;
import javax.sql.DataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallback;
import org.springframework.transaction.support.TransactionTemplate;

public class MicrowaveDAO implements IMicrowaveDAO {

    private DataSource dataSource;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void insert(Microwave customer) { // Реализация вставки новой записи

        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO microwave (brand, model) VALUES(?,?)",
                new Object[]{customer.getBrand(), customer.getModel()});
    }

    @Override
    public void append(String brand, String model) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO microwave (brand, model) VALUES(?,?)", new Object[]{brand, model});
    }

    @Override
    public void deleteByBrand(String brand) {  // Реализация удаления записей по бренду
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM microwave WHERE brand LIKE ?", new Object[]{'%' + brand + '%'});
    }

    @Override
    public void delete(final String brand, final String model) {  // Реализация удаления записей с указанными брендом и механизмом
        TransactionTemplate transactionTemplate = new TransactionTemplate(new DataSourceTransactionManager(dataSource));

        transactionTemplate.execute(new TransactionCallback() {
            @Override
            public Object doInTransaction(TransactionStatus status) {

                try {
                    JdbcTemplate delete = new JdbcTemplate(dataSource);
                    delete.update("DELETE FROM microwave WHERE brand = ? AND model = ?", new Object[]{brand, model});
                } catch (RuntimeException e) {
                    status.setRollbackOnly();
                    throw e;
                } catch (Exception e) {
                    status.setRollbackOnly();
                    throw new RuntimeException(e);
                }
                return null;
            }
        });
    }

    @Override
    public void deleteAll() {  // Реализация удаления всех запией
        JdbcTemplate delete = new JdbcTemplate(dataSource);
        delete.update("DELETE FROM microwave");
    }

    @Override
    public void update(String newModel, String oldModel) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE microwave SET model = ? WHERE model = ?", new Object[]{newModel, oldModel});
    }

    @Override
    public List<Microwave> findByBrand(String brand) {  // Реализация поиска записей по бренду
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM microwave WHERE brand LIKE ?";
        List<Microwave> microwaves = select.query(sql, new Object[]{'%' + brand + '%'}, new MicrowaveRowMapper());
        return microwaves;
    }

    @Override
    public List<Microwave> select(String brand, String model) {  // Реализация получения записей с заданными брендом и механизмом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT  * FROM microwave WHERE brand = ? AND model= ?",
                new Object[]{brand, model}, new MicrowaveRowMapper());
    }

    @Override
    public List<Microwave> selectAll() {  // Реализация получения всех записей
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT * FROM microwave", new MicrowaveRowMapper());
    }
    
    @Override
    public void appendOnlyBrand(String brand) {  // Реализация добавления новой записи
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("INSERT INTO microwave (brand, model) VALUES(?,?)", new Object[]{brand, "????"});
    }
    
    @Override
    public void deleteByModel(String model) {  // Реализация удаления записей по бренду
        JdbcTemplate insert = new JdbcTemplate(dataSource);
        insert.update("DELETE FROM microwave WHERE model LIKE ?", new Object[]{'%' + model + '%'});
    }
    
    @Override
    public void updateBrand(String newBrand, String oldBrand) {  // Изменение записей в таблице
        JdbcTemplate update = new JdbcTemplate(dataSource);
        update.update("UPDATE microwave SET brand = ? WHERE brand = ?", new Object[]{newBrand, oldBrand});
    }
    
    @Override
    public List<Microwave> findByModel(String model) {  // Реализация поиска записей по бренду
        JdbcTemplate select = new JdbcTemplate(dataSource);
        String sql = "SELECT * FROM microwave WHERE model LIKE ?";
        List<Microwave> microwaves = select.query(sql, new Object[]{'%' + model + '%'}, new MicrowaveRowMapper());
        return microwaves;
    }
    
    @Override
    public List<Microwave> selectByBrand(String brand) {  // Реализация получения записей с заданными брендом и механизмом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT  * FROM microwave WHERE brand = ?",
                new Object[]{brand}, new MicrowaveRowMapper());
    }
    
    @Override
    public List<Microwave> selectByModel(String model) {  // Реализация получения записей с заданными брендом и механизмом
        JdbcTemplate select = new JdbcTemplate(dataSource);
        return select.query("SELECT  * FROM microwave WHERE model = ?",
                new Object[]{model}, new MicrowaveRowMapper());
    }
}
