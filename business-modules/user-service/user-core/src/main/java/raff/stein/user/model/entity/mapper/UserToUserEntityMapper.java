package raff.stein.user.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import raff.stein.platformcore.model.mapper.configuration.CommonMapperConfiguration;
import raff.stein.user.model.User;
import raff.stein.user.model.entity.UserEntity;

@Mapper(config = CommonMapperConfiguration.class)
public interface UserToUserEntityMapper {

    UserToUserEntityMapper MAPPER = Mappers.getMapper(UserToUserEntityMapper.class);

    UserEntity toEntity(User user);

    User toModel(UserEntity userEntity);
}
