package data.registry.cars.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import data.registry.cars.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    @EntityGraph(attributePaths = { "cars" })
    Page<User> findByNameContains(
            String name,
            PageRequest pageRequest);

    @Override
    @EntityGraph(attributePaths = { "cars" })
    Page<User> findAll(
            Pageable pageable);

    @Override
    @EntityGraph(attributePaths = { "cars" })
    User getReferenceById(
            Integer id);
}
