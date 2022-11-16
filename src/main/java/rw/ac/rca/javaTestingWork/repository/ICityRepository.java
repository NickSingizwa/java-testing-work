package rw.ac.rca.javaTestingWork.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import rw.ac.rca.javaTestingWork.domain.City;

@Repository
public interface ICityRepository extends JpaRepository<City, Long> {

	boolean existsByName(String name);

}
