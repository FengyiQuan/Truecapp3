package com.example.truecapp3.controllers;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Rating;
import com.example.truecapp3.services.RatingService;
import com.example.truecapp3.services.TransactionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class RatingController {
  @Autowired
  private RatingService ratingService;

  @Autowired
  private TransactionService transactionService;

  @RequestMapping("/addRating/{transactionId}/{userId}")
  public String addRating(ModelMap modelo, @RequestParam int star, @RequestParam(required = false) String msg,
                          @PathVariable String transactionId, @PathVariable String userId) {
    try {
      Rating rating = ratingService.createRating(star, msg);
      transactionService.bindTransaction(transactionId, userId, rating);

    } catch (ServiceError ex) {
      modelo.put("error", ex.getMessage());
      return "new_user";
    }
    return "user_tiendahome2";
  }

}
