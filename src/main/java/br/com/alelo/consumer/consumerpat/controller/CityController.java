package br.com.alelo.consumer.consumerpat.controller;

import br.com.alelo.consumer.consumerpat.entity.City;
import br.com.alelo.consumer.consumerpat.services.CityService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(CityController.PATH)
public class CityController {

    protected static final String PATH = "cities";

    private final CityService cityService;

    public CityController(CityService cityService) {
        this.cityService = cityService;
    }

    @GetMapping("/findByStateId")
    public List<City> findByStateId(@RequestParam Long id) {
        return cityService.findByStateCode(id);
    }

    @GetMapping
    public List<City> findAll() {
        return cityService.findAll();
    }
}
