package raff.stein.user.model.entity.mapper;

import org.mapstruct.Mapper;
import raff.stein.platformcore.model.mapper.configuration.CommonMapperConfiguration;
import raff.stein.user.model.BankBranch;
import raff.stein.user.model.entity.BankBranchEntity;

@Mapper(config = CommonMapperConfiguration.class)
public interface BankBranchToBankBranchEntityMapper {

    BankBranchEntity toEntity(BankBranch bankBranch);

    BankBranch toModel(BankBranchEntity bankBranchEntity);
}
