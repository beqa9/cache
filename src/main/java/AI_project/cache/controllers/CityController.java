package AI_project.cache.controllers;

import AI_project.cache.models.CityModel;
import AI_project.cache.services.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    @GetMapping
    public List<CityModel> getAll() {
        return cityService.getAll();
    }

    @GetMapping("/by-country/{countryId}")
    public List<CityModel> getByCountry(@PathVariable Long countryId) {
        return cityService.getByCountry(countryId);
    }

    @PostMapping
    public CityModel create(
            @RequestParam String name,
            @RequestParam Long countryId
    ) {
        return cityService.create(name, countryId);
    }
}