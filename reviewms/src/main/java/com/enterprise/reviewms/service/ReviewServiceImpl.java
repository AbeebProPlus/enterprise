package com.enterprise.reviewms.service;


import com.enterprise.reviewms.data.dto.ReviewDto;
import com.enterprise.reviewms.data.model.Review;
import com.enterprise.reviewms.repo.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {
    private final ReviewRepository reviewRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<ReviewDto> getAllReviews(Long companyId) {
        List<Review> reviews = reviewRepository.findByCompanyId(companyId);
        return reviews.stream().map(review -> modelMapper.map(review, ReviewDto.class)).toList();
    }

    @Override
    public boolean addReview(Long companyId, Review review) {
        if (companyId != null && review != null){
            review.setCompanyId(companyId);
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public ReviewDto getReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            return modelMapper.map(review, ReviewDto.class);
        } else {
            return null;
        }
    }

    @Override
    public boolean updateReview(Long reviewId, Review updatedReview) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            review.setTitle(updatedReview.getTitle());
            review.setDescription(updatedReview.getDescription());
            review.setRating(updatedReview.getRating());
            review.setCompanyId(updatedReview.getCompanyId());
            reviewRepository.save(review);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean deleteReview(Long reviewId) {
        Review review = reviewRepository.findById(reviewId).orElse(null);
        if (review != null){
            reviewRepository.delete(review);
            return true;
        } else {
            return false;
        }
    }
}
