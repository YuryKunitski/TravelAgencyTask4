package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.service.TourService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
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
import static org.junit.Assert.*;
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
public class TourControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private TourService tourService;

    private Tour tour;

    @Before
    public void setUp() {
        tour = WebInitEntity.initTour();
    }

    @Test
    public void addTour() throws Exception {
        given(tourService.add(tour)).willReturn(tour);

        mvc.perform(post("/tour")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(tour)))
                .andExpect(status().isOk());

        verify(tourService, times(1)).add(tour);
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void getTourById() throws Exception {
        tour.setId("1");

        given(tourService.getById("1")).willReturn(Optional.of(tour));

        mvc.perform(get("/tour/get_by_id")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is("1")));

        verify(tourService, times(1)).getById("1");
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void getAllTour() throws Exception {
        List<Tour> tourList = Collections.singletonList(tour);

        given(tourService.getAll()).willReturn(tourList);

        mvc.perform(get("/tour/get_all")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(tourService, times(1)).getAll();
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void getAllTourByUserId() throws Exception {

        List<Tour> tourList = Collections.singletonList(tour);

        given(tourService.getAllByUserId("1")).willReturn(tourList);

        mvc.perform(get("/tour/get_all_by_user_id").param("id", "1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(tourService, times(1)).getAllByUserId("1");
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void updateTour() throws Exception {
        tour.setId("1");

        given(tourService.update(tour, "1")).willReturn(tour);

        mvc.perform(put("/tour")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(tour))
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(tourService, times(1)).update(tour, "1");
        verifyNoMoreInteractions(tourService);
    }

    @Test
    public void deleteTour() throws Exception {
        given(tourService.getById("1")).willReturn(Optional.of(tour));

        mvc.perform(delete("/tour")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk());

        verify(tourService, times(1)).delete("1");
        verify(tourService, times(1)).getById("1");
        verifyNoMoreInteractions(tourService);
    }
}