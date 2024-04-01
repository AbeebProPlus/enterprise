package com.enterprise.reviewms.service;

import com.enterprise.reviewms.data.model.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getAllReviews(Long companyId);
    boolean addReview(Long companyId, Review review);
    Review getReview(Long reviewId);
    boolean updateReview(Long reviewId, Review review);
    boolean deleteReview(Long reviewId);
}

