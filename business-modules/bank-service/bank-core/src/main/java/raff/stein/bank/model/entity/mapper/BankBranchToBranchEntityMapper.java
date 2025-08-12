package raff.stein.bank.model.entity.mapper;

import org.mapstruct.Mapper;
import raff.stein.bank.model.BankBranch;
import raff.stein.bank.model.entity.BranchEntity;
import raff.stein.platformcore.model.mapper.configuration.CommonMapperConfiguration;


@Mapper(config = CommonMapperConfiguration.class)
public interface BankBranchToBranchEntityMapper {

    BranchEntity toEntity(BankBranch bankBranch);

    BankBranch toModel(BranchEntity branchEntity);
}
