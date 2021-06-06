package UserDataTest;

import com.xxx.dao.UserData.UserDataDao;
import com.xxx.dao.UserData.UserDataDaoImpl;
import com.xxx.pojo.UserData;
import org.junit.Test;

import java.util.List;

public class UserDataTest {
    @Test
    public void getAllTest(){
        UserDataDao userDataDao = new UserDataDaoImpl();
        List<UserData> userDataList = userDataDao.selectAll();
        System.out.println(userDataList);
    }
    @Test
    public void uuidTest() {
        String id = "user-2";
        UserDataDao userDataDao = new UserDataDaoImpl();
        UserData userData = userDataDao.selectByPrimaryKey(id);
        System.out.println(userData);
    }
}
