package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.User;
import by.epam.kunitski.travelagency.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(username = "admin", roles = "ADMIN")
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;

    private User user;

    @Before
    public void setUp() {
        user = WebInitEntity.initUser();
    }

    @Test
    public void addMember() throws Exception {
        given(userService.add(user)).willReturn(user);
        given(passwordEncoder.encode(user.getPassword())).willReturn("codedPassword");

        mvc.perform(post("/user/add_member")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(user)))
                .andExpect(status().isOk());

        verify(userService, times(1)).add(user);
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void addAdmin() throws Exception {

        user.setRole(User.UserRole.ADMIN);

        given(userService.addAdmin(user)).willReturn(user);
        given(passwordEncoder.encode(user.getPassword())).willReturn("codedPassword");

        mvc.perform(post("/user/add_admin")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(user)))
                .andExpect(status().isOk());

        verify(userService, times(1)).addAdmin(user);
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getUserById() throws Exception {
        user.setId("1");

        given(userService.getById("1")).willReturn(Optional.of(user));

        mvc.perform(get("/user/get_by_id")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is("1")));

        verify(userService, times(1)).getById("1");
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getUserByUsername() throws Exception {
        user.setId("1");

        given(userService.findUserByUsername("Saundra")).willReturn(user);

        mvc.perform(get("/user/get_by_username").param("username", "Saundra")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("username", is("Saundra")));

        verify(userService, times(1)).findUserByUsername("Saundra");
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void getAllUser() throws Exception {
        List<User> userList = Collections.singletonList(user);

        given(userService.getAll()).willReturn(userList);

        mvc.perform(get("/user/get_all")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(userService, times(1)).getAll();
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void updateUser() throws Exception {
//        user.setId("1");

        given(userService.update(user, "1")).willReturn(user);
        given(passwordEncoder.encode(user.getPassword())).willReturn("codedPassword");

        mvc.perform(put("/user")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(user))
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(userService, times(1)).update(user, "1");
        verify(passwordEncoder, times(1)).encode(user.getPassword());
        verifyNoMoreInteractions(userService);
    }

    @Test
    public void deleteUser() {
    }
}