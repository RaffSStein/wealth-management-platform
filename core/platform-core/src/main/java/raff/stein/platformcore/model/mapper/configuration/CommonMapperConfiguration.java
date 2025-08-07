package raff.stein.platformcore.model.mapper.configuration;

import org.mapstruct.InjectionStrategy;
import org.mapstruct.MapperConfig;
import org.mapstruct.ReportingPolicy;
import raff.stein.platformcore.model.mapper.UUIDMapper;

@MapperConfig(
        unmappedTargetPolicy = ReportingPolicy.IGNORE,
        unmappedSourcePolicy = ReportingPolicy.WARN,
        injectionStrategy = InjectionStrategy.CONSTRUCTOR,
        uses = {UUIDMapper.class}
)
public interface CommonMapperConfiguration {
}
