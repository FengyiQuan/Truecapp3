package com.example.truecapp3.services;

import com.example.truecapp3.errors.ServiceError;
import com.example.truecapp3.models.Category;
import com.example.truecapp3.repositories.CategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class CategoryService {
  @Autowired
  private CategoryRepository categoryRepository;

  // if it has already exist, return it; otherwise, create a new category
  @Transactional
  public Category createCategory(String name) throws ServiceError {
    validate(name);
    Category category = categoryRepository.getCategoryByName(name);
    if (category == null) {
      Category newCategory = new Category(name);
      return categoryRepository.save(newCategory);
    }
    return category;

  }

  public Category getCategoryById(String id) throws ServiceError {
    Category category = categoryRepository.getOne(id);
    if (category == null) {
      throw new ServiceError("Category does not exist.");
    } else {
      return category;
    }
  }


  private void validate(String name) throws ServiceError {
    if (name == null || name.isEmpty()) {
      throw new ServiceError("Category name cannot be empty or null. ");
    }
  }

}
