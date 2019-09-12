package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.repository.TourDAO;
import by.epam.kunitski.travelagency.service.TourService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static by.epam.kunitski.travelagency.dao.entity.Hotel.FeatureType.CHILDREN_AREA;
import static by.epam.kunitski.travelagency.dao.entity.Tour.TourType.ECONOM;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TourServiceImpl.class)
public class TourServiceImplTest {

    @Autowired
    private TourService tourService;

    @MockBean
    private TourDAO tourDAO;

    Tour tour = new Tour();


    @Before
    public void setUp() {
        Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("Choloepus hoffmani");
        hotel.setStars(2);
        hotel.setWebsite("http://kvassman0@wikimedia.org");
        hotel.setLatitude(8.2673715);
        hotel.setLongitude(48.9086571);
        hotel.setFeatures(CHILDREN_AREA);

        Country country = new Country();
        country.setId("1");
        country.setName("China");

        tour.setPhoto("http://dummyimage.com/190x216.jpg/ff4444/ffffff");
        tour.setDate(LocalDate.of(2019, 12, 14));
        tour.setDuration(1);
        tour.setDescription("Integer non velit.");
        tour.setCost(1839.65);
        tour.setHotel(hotel);
        tour.setCountry(country);
        tour.setTour_type(ECONOM);

    }

    @Test
    public void getAllByUserId() {
        when(tourDAO.getAllByUserId("1")).thenReturn(new ArrayList<>());

        tourService.getAllByUserId("1");

        verify(tourDAO, times(1)).getAllByUserId("1");
        verifyNoMoreInteractions(tourDAO);
    }
}