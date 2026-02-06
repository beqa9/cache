package AI_project.cache.controllers;

import AI_project.cache.models.CountryModel;
import AI_project.cache.services.CountryService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public List<CountryModel> getAll() {
        return countryService.getAll();
    }

    @GetMapping("/{id}")
    public CountryModel getById(@PathVariable Long id) {
        return countryService.getById(id);
    }

    @PostMapping
    public CountryModel create(@RequestBody CountryModel model) {
        return countryService.create(model);
    }
}