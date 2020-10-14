package com.example.truecapp3.services;
import com.example.truecapp3.repositories.DonationRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DonationService {
  @Autowired
  DonationRepository donationRepository;
}
