package raff.stein.document.model.entity.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import raff.stein.document.model.Document;
import raff.stein.document.model.entity.DocumentEntity;

@Mapper(config = DocumentEntityCommonMapperConfig.class)
public interface DocumentEntityToDocumentMapper {

    DocumentEntityToDocumentMapper MAPPER = Mappers.getMapper(DocumentEntityToDocumentMapper.class);

    @Mapping(target = "documentName", source = "fileName")
    @Mapping(target = "documentType", ignore = true)
    @Mapping(target = "metadata", ignore = true)
    @Mapping(target = "accessLogs", ignore = true)
    @Mapping(target = "versions", ignore = true)
    DocumentEntity toDocumentEntity(Document document);
}
