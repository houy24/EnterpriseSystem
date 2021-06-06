import com.xxx.dao.PersonalResume.PersonalResumeDao;
import com.xxx.dao.PersonalResume.PersonalResumeDaoImpl;
import com.xxx.pojo.PersonalResume;
import org.junit.Test;

public class PersonalResumeTest {
    @Test
    public void test1(){
        PersonalResumeDao personalResumeDao = new PersonalResumeDaoImpl();
        PersonalResume personalResume = personalResumeDao.selectAllByUserId("user-1");
        System.out.println(personalResume);
    }
}
