package com.dev.portfolio.repository;

import com.dev.portfolio.model.entity.Item;
import com.dev.portfolio.repository.custom.ItemRepositoryCustom;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends CrudRepository<Item, Long>, ItemRepositoryCustom {
}
