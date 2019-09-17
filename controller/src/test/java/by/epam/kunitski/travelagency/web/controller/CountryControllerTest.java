package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.service.CountryService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataMongo
public class CountryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CountryService countryService;

    private Country country = new Country();

    @Before
    public void setUp() {
        country.setName("Ozz");
    }

    @Test
    public void addCountry() {
    }

    @Test
    public void getCountryById() throws Exception {
        country.setId("1");

        given(countryService.getById("1")).willReturn(Optional.of(country));

        mvc.perform(get("/country/get_by_id").contentType(APPLICATION_JSON) .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is("1")))
                .andExpect(jsonPath("name", is("Ozz")));
    }

    @Test
    public void getAllCountry() throws Exception {

        List<Country> countryList = Collections.singletonList(country);

        given(countryService.getAll()).willReturn(countryList);

        mvc.perform(get("/country/get_all")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].name", is(country.getName())));

    }

    @Test
    public void updateCountry() {
    }

    @Test
    public void deleteCountry() throws Exception {

//        mvc.perform(delete("/country")
//                .contentType(APPLICATION_JSON).param("id", "1"))
//                .andExpect(status().isOk());
////                .andExpect(jsonPath("$", hasSize(1)))
////                .andExpect(jsonPath("$[0].name", is(country.getName())));
//
//        verify(countryService, times(1)).delete("1");


    }
}