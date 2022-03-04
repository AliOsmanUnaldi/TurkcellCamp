package com.turkcellIntelij.intelijturkcell.dataAccess.abstracts;

import com.turkcellIntelij.intelijturkcell.entities.concretes.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryDao extends JpaRepository<Category,Integer> {

}
