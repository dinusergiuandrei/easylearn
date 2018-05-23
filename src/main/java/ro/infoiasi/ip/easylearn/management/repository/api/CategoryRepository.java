package ro.infoiasi.ip.easylearn.management.repository.api;


import ro.infoiasi.ip.easylearn.management.model.Category;

import java.util.List;

public interface CategoryRepository {
    Category findById(Long id);
    List<Category> findAll();
}
