package rw.ac.rca.javaTestingWork.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import rw.ac.rca.javaTestingWork.domain.City;
import rw.ac.rca.javaTestingWork.dto.CreateCityDTO;
import rw.ac.rca.javaTestingWork.service.CityService;
import rw.ac.rca.javaTestingWork.utils.APICustomResponse;

@RestController
@RequestMapping("/api/cities")
public class CityController {

	@Autowired
	private CityService cityService;

	@GetMapping("/id/{id}")
	public ResponseEntity<?> getById(@PathVariable(name = "id") long id) {

		Optional<City> city = cityService.getById(id);

		if (city.isPresent()) {
			return ResponseEntity.status(HttpStatus.OK).body(city.get());
		}

		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new APICustomResponse(false, "City not found with id " + id));
	}

	@GetMapping("/all")
	public List<City> getAll() {

		return cityService.getAll();
	}

	@PostMapping("/add")
	public ResponseEntity<?> saveItem(@RequestBody CreateCityDTO dto) {

		if (cityService.existsByName(dto.getName())) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST)
					.body(new APICustomResponse(false, "City name " + dto.getName() + " is registered already"));
		}
		City city = cityService.save(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(city);
	}
}
