package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.*;
import by.epam.kunitski.travelagency.dao.repository.CountryDAO;
import by.epam.kunitski.travelagency.dao.repository.ReviewDAO;
import by.epam.kunitski.travelagency.service.CountryService;
import by.epam.kunitski.travelagency.service.ReviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;

import static by.epam.kunitski.travelagency.dao.entity.Hotel.FeatureType.CHILDREN_AREA;
import static by.epam.kunitski.travelagency.dao.entity.Tour.TourType.ECONOM;
import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = ReviewServiceImpl.class)
public class ReviewServiceImplTest {

    @Autowired
    private ReviewService reviewService;

    @MockBean
    private ReviewDAO reviewDAO;

    Review review = new Review();

    @Before
    public void setUp() {
        Review review = new Review();

        Hotel hotel = new Hotel();
        hotel.setId("1");
        hotel.setName("Choloepus hoffmani");
        hotel.setStars(2);
        hotel.setWebsite("kvassman0@wikimedia.org");
        hotel.setLatitude(8.2673715);
        hotel.setLongitude(48.9086571);
        hotel.setFeatures(CHILDREN_AREA);

        Country country = new Country();
        country.setId("1");
        country.setName("China");


        User user = new User();
        user.setId("1");
        user.setUsername("Saundra");
        user.setPassword("CDHjDf5Tnr");
        user.setRole(User.UserRole.MEMBER);

        Tour tour = new Tour();
        tour.setId("1");
        tour.setPhoto("http://dummyimage.com/190x216.jpg/ff4444/ffffff");
        tour.setDate(LocalDate.of(2019, 12, 14));
        tour.setDuration(1);
        tour.setDescription("Integer non velit.");
        tour.setCost(1839.65);
        tour.setHotel(hotel);
        tour.setCountry(country);
        tour.setTour_type(ECONOM);

        review.setText("Excelent");
        review.setDate(LocalDate.of(2019, 1, 1));
        review.setText("Pellentesque ultrices mattis odio.");
        review.setUser(user);
        review.setTour(tour);

    }

    @Test
    public void getAllByTourId() {
        when(reviewDAO.getAllByTourId("1")).thenReturn(new ArrayList<>());

        reviewService.getAllByTourId("1");
        verify(reviewDAO, times(1)).getAllByTourId("1");
    }

    @Test
    public void getAllByUserId() {
        Mockito.when(reviewDAO.getAllByUserId("1")).thenReturn(new ArrayList<>());

        reviewService.getAllByUserId("1");
        verify(reviewDAO, times(1)).getAllByUserId("1");
    }
}