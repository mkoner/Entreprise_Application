package repository;

import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, Integer> {
    List<CD> findByArtistAndPriceLessThan(String artist, Double price);
}
