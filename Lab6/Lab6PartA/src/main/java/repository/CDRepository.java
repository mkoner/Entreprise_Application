package repository;

import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, Integer> {
    List<CD> findByArtistAndPriceLessThan(String artist, Double price);
    List<CD> findByArtist(@Param("artist") String artist);
}
