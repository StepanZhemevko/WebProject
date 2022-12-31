package dataTet;

import com.example.registration.sql.Member;
import com.example.registration.sql.RegisterDao;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class RegisterDaoTest {
    @Test
    void insert() {
        RegisterDao registerDao = new RegisterDao();
        assertTrue(registerDao.insert(new Member("tes_test","testTest","test Test","test@Test","0997671286")));
    }
}
