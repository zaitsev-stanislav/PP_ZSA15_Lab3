package zsa15_lab3;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

public class MicrowaveRowMapper implements RowMapper {

    @Override
    public Object mapRow(ResultSet rs, int line) throws SQLException {
        PersonResultSetExtractor extractor = new PersonResultSetExtractor();
        return extractor.extractData(rs);
    }
    
    class PersonResultSetExtractor implements ResultSetExtractor {

        @Override
        public Object extractData(ResultSet rs) throws SQLException {
            Microwave microwaves = new Microwave();
            microwaves.setId(rs.getInt("id"));
            microwaves.setBrand(rs.getString("Brand"));
            microwaves.setModel(rs.getString("Model"));
            return microwaves;
        }
    }
}
