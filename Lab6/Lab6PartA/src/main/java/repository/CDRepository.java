package repository;

import domain.CD;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CDRepository extends JpaRepository<CD, Integer> {
    List<CD> findByArtistAndPriceLessThan(String artist, Double price);
    List<CD> findByArtist(@Param("artist") String artist);
    @Query("select cd from CD cd where cd.artist = :artist and cd.price < :price")
    List<CD> findByArtistAndPriceLessThanUsingQuery(String artist, Double price);
}
