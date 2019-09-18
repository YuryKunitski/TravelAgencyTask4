package by.epam.kunitski.travelagency.web.controller;

import by.epam.kunitski.travelagency.dao.entity.Review;
import by.epam.kunitski.travelagency.service.ReviewService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
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
public class ReviewControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private ReviewService reviewService;

    private Review review;

    @Before
    public void setUp() {
        review = WebInitEntity.initReview();
    }

    @Test
    public void addReview() throws Exception {

        given(reviewService.add(review)).willReturn(review);

        mvc.perform(post("/review")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(review))
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());


        verify(reviewService, times(1)).add(review);
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void getReviewById() throws Exception {
        review.setId("1");

        given(reviewService.getById("1")).willReturn(Optional.of(review));

        mvc.perform(get("/review/get_by_id")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("id", is("1")));

        verify(reviewService, times(1)).getById("1");
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void getAllReview() throws Exception {

        List<Review> reviewList = Collections.singletonList(review);

        given(reviewService.getAll()).willReturn(reviewList);

        mvc.perform(get("/review/get_all")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(reviewService, times(1)).getAll();
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void getAllReviewByTourId() throws Exception {

        List<Review> reviewList = Collections.singletonList(review);

        given(reviewService.getAllByTourId("1")).willReturn(reviewList);

        mvc.perform(get("/review/get_all_by_tour_id")
                .param("id", "1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(reviewService, times(1)).getAllByTourId("1");
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void getAllReviewByUserId() throws Exception {

        List<Review> reviewList = Collections.singletonList(review);

        given(reviewService.getAllByUserId("1")).willReturn(reviewList);

        mvc.perform(get("/review/get_all_by_user_id")
                .param("id", "1")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)));

        verify(reviewService, times(1)).getAllByUserId("1");
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void updateReview() throws Exception {
        review.setId("1");

        given(reviewService.update(review, "1")).willReturn(review);

        mvc.perform(put("/review")
                .contentType(APPLICATION_JSON)
                .content(Converter.asJsonString(review))
                .param("id", "1"))
                .andDo(print())
                .andExpect(status().isOk());

        verify(reviewService, times(1)).update(review, "1");
        verifyNoMoreInteractions(reviewService);
    }

    @Test
    public void deleteReview() throws Exception {
        given(reviewService.getById("1")).willReturn(Optional.of(review));

        mvc.perform(delete("/review")
                .contentType(APPLICATION_JSON)
                .param("id", "1"))
                .andExpect(status().isOk());

        verify(reviewService, times(1)).delete("1");
        verify(reviewService, times(1)).getById("1");
        verifyNoMoreInteractions(reviewService);
    }
}