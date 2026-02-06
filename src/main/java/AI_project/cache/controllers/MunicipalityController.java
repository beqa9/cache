package AI_project.cache.controllers;

import AI_project.cache.models.MunicipalityModel;
import AI_project.cache.services.MunicipalityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/municipalities")
@RequiredArgsConstructor
public class MunicipalityController {

    private final MunicipalityService municipalityService;

    @GetMapping
    public List<MunicipalityModel> getAll() {
        return municipalityService.getAll();
    }

    @GetMapping("/by-city/{cityId}")
    public List<MunicipalityModel> getByCity(@PathVariable Long cityId) {
        return municipalityService.getByCity(cityId);
    }

    @PostMapping
    public MunicipalityModel create(
            @RequestParam String name,
            @RequestParam Long cityId
    ) {
        return municipalityService.create(name, cityId);
    }
}