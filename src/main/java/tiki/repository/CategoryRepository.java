package tiki.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tiki.entity.Category;

@Repository
public interface CategoryRepository extends CrudRepository<Category, Long> {
}
