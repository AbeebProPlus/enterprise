package com.enterprise.reviewms.messaging;

import com.enterprise.reviewms.data.dto.ReviewMessage;
import com.enterprise.reviewms.data.model.Review;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewMessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ModelMapper modelMapper;

    public void sendMessage(Review review){
        ReviewMessage reviewMessage = modelMapper.map(review, ReviewMessage.class);
        rabbitTemplate.convertAndSend("companyRatingQueue", reviewMessage);
    }
}
