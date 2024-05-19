package vn.iostar.springbootbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.iostar.springbootbackend.entity.SongCategory;

public interface SongCategoryRepository extends JpaRepository<SongCategory, Long> {

}
