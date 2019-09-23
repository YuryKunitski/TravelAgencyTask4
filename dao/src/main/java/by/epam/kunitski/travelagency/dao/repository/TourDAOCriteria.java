package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.searchform.TourForm;
import org.springframework.data.mongodb.core.query.Query;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class TourDAOCriteria implements TourDAO {

//    private Integer maxStars;
//    private Integer minStars;
//    private LocalDate minDate;
//    private LocalDate maxDate;
//    private Integer minDuration;
//    private Integer maxDuration;
//    private Double minCost;
//    private Double maxCost;
//    private String countryName;
//    private Tour.TourType tourType;
//
//    public TourDAOCriteria(TourForm tourForm) {
//        this.maxStars = tourForm.getMaxStars();
//        this.minStars = tourForm.getMinStars();
//        this.minDate = tourForm.getMinDate();
//        this.maxDate = tourForm.getMaxDate();
//        this.minDuration = tourForm.getMinDuration();
//        this.maxDuration = tourForm.getMaxDuration();
//        this.minCost = tourForm.getMinCost();
//        this.maxCost = tourForm.getMaxCost();
//        this.countryName = tourForm.getCountryName();
//        this.tourType = tourForm.getTourType();
//    }

    @Override
    public List<Tour> findToursByCriteria(TourForm tourForm) {
        List<Query> list = new ArrayList<>();

        Query query = new Query();

        return null;
    }
}
