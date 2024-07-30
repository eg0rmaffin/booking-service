package ru.prime.service;

import ru.prime.entity.Lot;
import ru.prime.repository.LotRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

@Service
public class LotService {

    private final LotRepository lotRepository;

    public LotService(LotRepository lotRepository) {
        this.lotRepository = lotRepository;
    }

    public List<Lot> getAllLots() {
        return lotRepository.findAll();
    }

    public List<Lot> filterLots(Optional<String> name, Optional<String> city, Optional<String> address, Optional<Double> minArea, Optional<Double> maxArea, Optional<Integer> minFloor, Optional<Integer> maxFloor, Optional<Integer> minRooms, Optional<Integer> maxRooms) {
        return lotRepository.findAll((Specification<Lot>) (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            name.ifPresent(n -> predicates.add(cb.like(cb.lower(root.get("name")), "%" + n.toLowerCase() + "%")));
            city.ifPresent(c -> predicates.add(cb.equal(cb.lower(root.get("city")), c.toLowerCase())));
            address.ifPresent(a -> predicates.add(cb.like(cb.lower(root.get("address")), "%" + a.toLowerCase() + "%")));
            minArea.ifPresent(min -> predicates.add(cb.greaterThanOrEqualTo(root.get("area"), min)));
            maxArea.ifPresent(max -> predicates.add(cb.lessThanOrEqualTo(root.get("area"), max)));
            minFloor.ifPresent(min -> predicates.add(cb.greaterThanOrEqualTo(root.get("floor"), min)));
            maxFloor.ifPresent(max -> predicates.add(cb.lessThanOrEqualTo(root.get("floor"), max)));
            minRooms.ifPresent(min -> predicates.add(cb.greaterThanOrEqualTo(root.get("rooms"), min)));
            maxRooms.ifPresent(max -> predicates.add(cb.lessThanOrEqualTo(root.get("rooms"), max)));

            return cb.and(predicates.toArray(new Predicate[0]));
        });
    }
}
