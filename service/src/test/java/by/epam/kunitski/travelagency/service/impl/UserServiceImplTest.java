package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.dao.repository.UserDAO;
import by.epam.kunitski.travelagency.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyNoMoreInteractions;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = UserServiceImpl.class)
public class UserServiceImplTest {

	@Autowired
	private UserService userService;

	@MockBean
	private UserDAO userDAO;

	User user = new User();

	@Before
	public void setUp() {
		user.setUsername("Saundra");
		user.setPassword("CDHjDf5Tnr");
		user.setRole(User.UserRole.MEMBER);
	}

	@Test
	public void findUserByUsername() {
		when(userDAO.findByUsername("Saundra")).thenReturn(user);

		userService.findUserByUsername("Saundra");
		verify(userDAO, times(1)).findByUsername("Saundra");
		verifyNoMoreInteractions(userDAO);
	}
}