package com.example.ec.web;

import com.example.ec.domain.TourRating;
import com.example.ec.repo.TourRatingRepository;
import com.example.ec.repo.TourRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/tours/{tourId}/ratings")
public class TourRatingController {
    TourRatingRepository tourRatingRepository;
    TourRepository tourRepository;

    @Autowired
    public TourRatingController(TourRatingRepository tourRatingRepository, TourRepository tourRepository) {
        this.tourRatingRepository = tourRatingRepository;
        this.tourRepository = tourRepository;
    }

    protected TourRatingController() {
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void createTourRating(@PathVariable(value = "tourId") String tourId,
                                 @RequestBody @Validated TourRating tourRating) {
        verifyTour(tourId);
        tourRatingRepository.save(new TourRating(tourId, tourRating.getCustomerId(),
                tourRating.getScore(), tourRating.getComment()));
    }

    @GetMapping
    public List<TourRating> getRating(@PathVariable(value = "tourId") String tourId,
                                      Pageable pageable) {
        return tourRatingRepository.findByTourId(tourId);
    }

    @GetMapping(path="/average")
    public Map<String, Double> getAverage(@PathVariable(value = "tourId") String tourId) {
        verifyTour(tourId);
        return Map.of("average", tourRatingRepository.findByTourId(tourId).stream()
                .mapToInt(TourRating::getScore).average()
                .orElseThrow(()->
                        new NoSuchElementException("Tour has no Ratings")));
    }

    private void verifyTour(String tourId) throws NoSuchElementException {
        if (!tourRepository.existsById(tourId)) {
            throw new NoSuchElementException("Tour does not exist " + tourId);
        }
    }

    //Updating all attributes
    @PutMapping
    public TourRating updateWithPut(@PathVariable(value="tourId") String tourId, @RequestBody @Validated TourRating tourRating) {
        TourRating rating = verifyTourRating(tourId, tourRating.getCustomerId());
        rating.setScore(tourRating.getScore());
        rating.setComment(tourRating.getComment());
        return tourRatingRepository.save(rating);
    }

    //Only update some attributes
    @PatchMapping
    public TourRating updateWithPatch(@PathVariable(value="tourId") String tourId,
                                     @RequestBody @Validated TourRating tourRating) {
        TourRating rating = verifyTourRating(tourId, tourRating.getCustomerId());
        if (tourRating.getScore() != null) {
            rating.setScore(tourRating.getScore());
        }
        if (tourRating.getComment() != null) {
            rating.setComment(tourRating.getComment());
        }
        return tourRatingRepository.save(rating);
    }

    @DeleteMapping(path= "/{customerId}")
    public void delete(@PathVariable(value="tourId") String tourId,
                       @PathVariable(value="customerId") int customerId) {
        TourRating rating = verifyTourRating(tourId, customerId);
        tourRatingRepository.delete(rating);
    }

    private TourRating verifyTourRating(String tourId, int customerId) throws NoSuchElementException {
        return tourRatingRepository.findByTourIdAndCustomerId(tourId, customerId).orElseThrow(() ->
                new NoSuchElementException("Tour rating pair for request("
                + tourId + " for customer " + customerId));
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoSuchElementException.class)
    public String return400(NoSuchElementException ex) {
        return ex.getMessage();
    }
}
