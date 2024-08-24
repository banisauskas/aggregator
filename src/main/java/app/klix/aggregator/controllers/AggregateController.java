package app.klix.aggregator.controllers;

import app.klix.aggregator.data.Aggregate;
import app.klix.aggregator.data.Request;
import app.klix.aggregator.services.AggregateService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/aggregates")
public class AggregateController {

	@Autowired
	private AggregateService service;

	@GetMapping("/{id}")
	public Aggregate findAggregate(@PathVariable long id) {
		return service.findAggregate(id);
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Aggregate createAggregate(@RequestBody @Valid Request request) {
		return service.createAggregate(request);
	}
}