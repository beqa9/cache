package AI_project.cache.models;


import lombok.Builder;

@Builder
public record CountryModel(
        Long id,
        String name,
        String code
) {}
