package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.service.HotelService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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
public class HotelControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private HotelService hotelService;

    private Hotel hotel;


    @Before
    public void setUp() throws Exception {
        hotel = WebInitEntity.initHotel();
    }

    @Test
    public void addHotel() throws Exception {
        given(hotelService.add(hotel)).willReturn(hotel);

        mvc.perform(post("/hotel")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(hotel)))
                .andExpect(status().isOk());

        verify(hotelService, times(1)).add(hotel);
        verifyNoMoreInteractions(hotelService);
    }

    @Test
    public void getHotelById() throws Exception {
        hotel.setId("1");

        given(hotelService.getById("1")).willReturn(Optional.of(hotel));

        mvc.perform(get("/hotel/get_by_id")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is("1")));

        verify(hotelService, times(1)).getById("1");
        verifyNoMoreInteractions(hotelService);
    }

    @Test
    public void getAllHotel() throws Exception {

        List<Hotel> hotelList = Collections.singletonList(hotel);

        given(hotelService.getAll()).willReturn(hotelList);

        mvc.perform(get("/hotel/get_all")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(hotel.getName())));

        verify(hotelService, times(1)).getAll();
        verifyNoMoreInteractions(hotelService);
    }

    @Test
    public void updateHotel() throws Exception {
        hotel.setId("1");

        given(hotelService.update(hotel, "1")).willReturn(hotel);

        mvc.perform(put("/hotel")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(hotel))
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(hotelService, times(1)).update(hotel, "1");
        verifyNoMoreInteractions(hotelService);
    }

    @Test
    public void deleteHotel() throws Exception {
        given(hotelService.getById("1")).willReturn(Optional.of(hotel));

        mvc.perform(delete("/hotel")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk());

        verify(hotelService, times(1)).delete("1");
        verify(hotelService, times(1)).getById("1");
        verifyNoMoreInteractions(hotelService);
    }
}