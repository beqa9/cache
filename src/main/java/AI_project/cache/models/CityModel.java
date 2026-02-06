package AI_project.cache.models;

import lombok.Builder;

@Builder
public record CityModel(
        Long id,
        String name,
        Long countryId,
        String countryName
) {}