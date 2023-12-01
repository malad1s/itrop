import com.my.DBConnection.DBCPDataSource;
import com.my.DBConnection.DBCPSetProperties;
import org.apache.commons.dbcp.BasicDataSource;
import org.junit.Test;

import static org.testng.AssertJUnit.assertNotNull;

public class TestDBCon {

    @Test
    public void testConnection(){
        BasicDataSource ds= DBCPDataSource.getDataSource();
        DBCPSetProperties.setProperties();
        assertNotNull(DBCPDataSource.getConnection());
    }

}
