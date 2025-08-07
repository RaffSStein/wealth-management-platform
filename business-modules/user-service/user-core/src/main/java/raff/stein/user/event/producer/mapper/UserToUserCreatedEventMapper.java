package raff.stein.user.event.producer.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.openapitools.model.UserCreatedEvent;
import raff.stein.platformcore.model.mapper.configuration.CommonMapperConfiguration;
import raff.stein.user.model.User;

@Mapper(config = CommonMapperConfiguration.class)
public interface UserToUserCreatedEventMapper {

    UserToUserCreatedEventMapper MAPPER = Mappers.getMapper(UserToUserCreatedEventMapper.class);

    UserCreatedEvent toUserCreatedEvent(User user);
}
