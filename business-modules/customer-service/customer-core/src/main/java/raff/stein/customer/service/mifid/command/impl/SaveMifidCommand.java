package raff.stein.customer.service.mifid.command.impl;

import jakarta.annotation.Nullable;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import raff.stein.customer.model.bo.mifid.filling.MifidFilling;
import raff.stein.customer.model.entity.mifid.MifidFillingEntity;
import raff.stein.customer.model.entity.mifid.mapper.MifidFillingEntityToMifidFillingMapper;
import raff.stein.customer.repository.mifid.MifidFillingRepository;
import raff.stein.customer.service.mifid.command.MifidActionType;
import raff.stein.customer.service.mifid.command.MifidCommand;

import java.util.UUID;

@Component
@RequiredArgsConstructor
public class SaveMifidCommand implements MifidCommand {

    private final MifidFillingRepository mifidFillingRepository;
    private static final MifidFillingEntityToMifidFillingMapper mifidFillingEntityToMifidFillingMapper =
            MifidFillingEntityToMifidFillingMapper.MAPPER;

    @Override
    public MifidActionType getActionType() {
        return MifidActionType.SAVE;
    }

    @Override
    public MifidFilling execute(@NonNull UUID customerId, @Nullable MifidFilling mifidFilling) {
        // save operation is meant to be executed once
        // create new entity from BO
        MifidFillingEntity mifidFillingEntity = mifidFillingEntityToMifidFillingMapper.toMifidFillingEntity(mifidFilling);
        mifidFillingEntity.initializeMifidFirstSave();
        //TODO: add default answer options for free text, number or data in questions
        mifidFillingEntity.setCustomerId(customerId);
        // save entity
        mifidFillingEntity = mifidFillingRepository.save(mifidFillingEntity);
        // convert back to BO and return
        return mifidFillingEntityToMifidFillingMapper.toMifidFilling(mifidFillingEntity);
    }
}
