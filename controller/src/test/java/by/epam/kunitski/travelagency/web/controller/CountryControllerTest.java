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
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DirtiesContext
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CountryControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CountryService countryService;

    private Country country;

    @Before
    public void setUp() {
        country = WebInitEntity.initCountry();
    }

    @Test
    public void addCountry() throws Exception {

        given(countryService.add(country)).willReturn(country);

        mvc.perform(post("/country")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(country)))
                .andExpect(status().isOk());

        verify(countryService, times(1)).add(country);
        verifyNoMoreInteractions(countryService);
    }

    @Test
    public void getCountryById() throws Exception {
        country.setId("1");

        given(countryService.getById("1")).willReturn(Optional.of(country));

        mvc.perform(get("/country/get_by_id")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("id", is("1")))
                .andExpect(jsonPath("name", is("China")));

        verify(countryService, times(1)).getById("1");
        verifyNoMoreInteractions(countryService);
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

        verify(countryService, times(1)).getAll();
        verifyNoMoreInteractions(countryService);
    }

    @Test
    public void updateCountry() throws Exception {

        country.setId("1");

        given(countryService.update(country, "1")).willReturn(country);

        mvc.perform(put("/country")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(country))
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(countryService, times(1)).update(country, "1");
        verifyNoMoreInteractions(countryService);
    }

    @Test
    public void deleteCountry() throws Exception {

        given(countryService.getById("1")).willReturn(Optional.of(country));

        mvc.perform(delete("/country")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk());

        verify(countryService, times(1)).delete("1");
        verify(countryService, times(1)).getById("1");
        verifyNoMoreInteractions(countryService);
    }
}