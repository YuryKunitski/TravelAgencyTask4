package by.epam.kunitski.travelagency.service.impl;

import by.epam.kunitski.travelagency.dao.entity.Review;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.repository.ReviewDAO;
import by.epam.kunitski.travelagency.dao.repository.TourDAO;
import by.epam.kunitski.travelagency.service.ReviewService;
import by.epam.kunitski.travelagency.service.TourService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

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

    }

    @Test
    public void getAllByUserId() {
    }
}