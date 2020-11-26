package jp.co.ntt.ojt.domain.repository.User;

import static org.junit.Assert.assertEquals;

import java.util.TreeSet;

import javax.inject.Inject;

import jp.co.ntt.ojt.domain.model.User;
import jp.co.ntt.ojt.domain.repository.user.UserRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {
        "classpath:META-INF/spring/ojt-infra.xml",
        "classpath:META-INF/spring/test-context.xml"} )
public class UserRepositoryTest {

	@Inject
	UserRepository userRepository;

    @Test
    @Sql(scripts = "classpath:META-INF/sql/user.sql", config = @SqlConfig(encoding = "utf-8"))
	public void userRepositoryTest01() {
		
		String userId = "0001";

		User result = userRepository.findOneByUserId(userId);
		
		assertEquals(result.getUserId(),"0001");

	}
    
    @Test
    @Sql(scripts = "classpath:META-INF/sql/user.sql", config = @SqlConfig(encoding = "utf-8"))
	public void userRepositoryTest02() {
		
    	TreeSet<Integer> result = userRepository.findAllUserId();
		
		assertEquals(result.size(),1);

	}
}
