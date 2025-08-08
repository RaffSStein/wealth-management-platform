package raff.stein.platformcore.model.mapper;

import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper
public abstract class UUIDMapper {

    public String map(UUID value) {
        return value == null ? null : value.toString();
    }

    public UUID map(String value) {
        return value == null ? null : UUID.fromString(value);
    }
}
