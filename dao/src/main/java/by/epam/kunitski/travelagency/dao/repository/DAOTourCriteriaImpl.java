package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.searchform.TourForm;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Query.query;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class DAOTourCriteriaImpl implements DAOTourCriteria {

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

    @Autowired
    MongoOperations mongoOperations;

    @Override
    public List<Tour> findToursByCriteria(TourForm tourForm) {

        List<Criteria> listCriteria = new ArrayList<>();

        listCriteria.add(getRangeQuery(tourForm.getMinCost(), tourForm.getMaxCost(), "cost"));
        listCriteria.add(getRangeQuery(tourForm.getMinDuration(), tourForm.getMaxDuration(), "duration"));
        listCriteria.add(getRangeQuery(tourForm.getMinDate(), tourForm.getMaxDate(), "date"));

        if (tourForm.getTourType() != null) {
            listCriteria.add(Criteria.where("tour_type").is(tourForm.getTourType()));
        }

        if (tourForm.getCountryName() != null) {
            Query countryQuery = new Query();
            countryQuery.addCriteria(Criteria.where("name").is(tourForm.getCountryName()));
            Country country = mongoOperations.findOne(countryQuery, Country.class);

            if (country != null) {
                listCriteria.add(Criteria.where("country._id").is(country.getId()));
            }
        }

        if (tourForm.getMinStars() != null || tourForm.getMaxStars() != null) {
            Query hotelQuery = getHotelRangeQuery(tourForm.getMinStars(), tourForm.getMaxStars());
            List<String> hotelIDList = mongoOperations.find(hotelQuery, Hotel.class).stream()
                    .map(Hotel::getId).collect(Collectors.toList());


                listCriteria.add(Criteria.where("hotel._id").in(hotelIDList));

        }

        Query queryGeneral = new Query();
        queryGeneral.with(new Sort(Sort.Direction.DESC, "date", "cost"));

        if (!listCriteria.isEmpty()) {
            queryGeneral.addCriteria(new Criteria().andOperator(listCriteria.toArray(new Criteria[0])));
        }

        System.out.println("listCriteria - " + listCriteria);


        List<Tour> tourList = mongoOperations.find(queryGeneral, Tour.class);

        return tourList;
    }

    private Criteria getRangeQuery(Object min, Object max, String entityField) {

        Criteria criteria = new Criteria();

        if (min != null && max != null) {
            criteria = Criteria.where(entityField).lte(max).gte(min);
        }

        if (min != null && max == null) {
            criteria = Criteria.where(entityField).gte(min);
        }

        if (min == null && max != null) {
            criteria = Criteria.where(entityField).lte(max);
        }

        return criteria;
    }

    private Query getHotelRangeQuery(Integer minStars, Integer maxStars) {

        Query hotelQuery = new Query();

        if (minStars != null && maxStars != null) {
            hotelQuery.addCriteria(Criteria.where("stars").lte(maxStars).gte(minStars));
        }

        if (minStars != null && maxStars == null) {
            hotelQuery.addCriteria(Criteria.where("stars").gte(minStars));
        }
        if (minStars == null && maxStars != null) {
            hotelQuery.addCriteria(Criteria.where("stars").lte(maxStars));
        }

        return hotelQuery;

    }

}
