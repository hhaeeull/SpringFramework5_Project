package mylab.user.di.annot;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:mylab-user-di.xml")
public class UserServiceTest {
	
	@Autowired
	private UserService userService;
	
	@Test
	public void testUserService() {
		
		// UserService 주입 확인
		assertNotNull(userService);
		
		// UserRepository 주입 확인
		assertNotNull(userService.getUserRepository());
		
		// dbType 값 확인
		assertEquals("MySQL", userService.getUserRepository().getDbType());
		
		// SecurityService 주입 확인
		assertNotNull(userService.getSecurityService());
		
		// 비밀번호 인증 확인
		assertTrue(userService.registerUser("user01", "홍길동", "1234"));
		assertFalse(userService.registerUser("user02", "홍길동", null));
		
	}
}
