package by.epam.kunitski.travelagency.dao.repository;

import by.epam.kunitski.travelagency.dao.entity.Country;
import by.epam.kunitski.travelagency.dao.entity.Hotel;
import by.epam.kunitski.travelagency.dao.entity.Tour;
import by.epam.kunitski.travelagency.dao.searchform.TourForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TourDAOImpl implements TourDAOCustom {

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

            listCriteria.add(Criteria.where("hotel").in(hotelIDList));
        }

        Query queryGeneral = new Query();
        queryGeneral.with(new Sort(Sort.Direction.DESC, "date", "cost"));

        if (!listCriteria.isEmpty()) {
            queryGeneral.addCriteria(new Criteria().andOperator(listCriteria.toArray(new Criteria[0])));
        }

        return mongoOperations.find(queryGeneral, Tour.class);
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
