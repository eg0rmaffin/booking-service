package ru.prime.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ru.prime.entity.Lot;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LotRepository extends JpaRepository<Lot, Long>, JpaSpecificationExecutor<Lot> {
    List<Lot> findByNameContainingIgnoreCase(String name);
    List<Lot> findByCityIgnoreCase(String city);
    List<Lot> findByAddressContainingIgnoreCase(String address);
    List<Lot> findByAreaBetween(Double minArea, Double maxArea);
    List<Lot> findByFloorBetween(Integer minFloor, Integer maxFloor);
    List<Lot> findByRoomsBetween(Integer minRooms, Integer maxRooms);
}
