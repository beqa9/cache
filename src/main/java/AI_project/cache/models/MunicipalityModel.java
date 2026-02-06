package AI_project.cache.models;

import lombok.Builder;

@Builder
public record MunicipalityModel(
        Long id,
        String name,
        Long cityId,
        String cityName
) {}