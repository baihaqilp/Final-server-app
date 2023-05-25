package id.co.metrodata.serverApp.repositories;

import id.co.metrodata.serverApp.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public Boolean existsByName(String name);
}
