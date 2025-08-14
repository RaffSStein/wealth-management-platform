package raff.stein.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import raff.stein.document.exception.FileValidationException;
import raff.stein.document.model.Document;
import raff.stein.document.model.File;
import raff.stein.document.model.entity.*;
import raff.stein.document.model.entity.mapper.*;
import raff.stein.document.repository.DocumentAccessLogRepository;
import raff.stein.document.repository.DocumentMetadataRepository;
import raff.stein.document.repository.DocumentRepository;
import raff.stein.document.repository.DocumentVersionRepository;
import raff.stein.document.service.storage.CloudStorageService;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final DocumentTypeService documentTypeService;
    private final FileValidationService fileValidationService;
    private final CloudStorageService cloudStorageService;

    private final DocumentRepository documentRepository;
    private final DocumentAccessLogRepository documentAccessLogRepository;
    private final DocumentMetadataRepository documentMetadataRepository;
    private final DocumentVersionRepository documentVersionRepository;

    private static final DocumentEntityToDocumentMapper documentEntityToDocumentMapper = DocumentEntityToDocumentMapper.MAPPER;
    private static final DocumentVersionEntityToDocumentMapper documentVersionEntityToDocumentMapper = DocumentVersionEntityToDocumentMapper.MAPPER;
    private static final DocumentMetadataEntityToMetadataMapper documentMetadataEntityToMetadataMapper = DocumentMetadataEntityToMetadataMapper.MAPPER;
    private static final DocumentAccessLogEntityToDocumentMapper documentAccessLogEntityToDocumentMapper = DocumentAccessLogEntityToDocumentMapper.MAPPER;
    private static final DocumentTypeEntityToDocumentTypeMapper documentTypeEntityToDocumentTypeMapper = DocumentTypeEntityToDocumentTypeMapper.MAPPER;

    public Document uploadDocument(File fileInput) {
        // get the document type (configuration) from the input file
        DocumentTypeEntity documentTypeEntity = documentTypeService.getDocumentTypeEntity(fileInput.getDocumentType());
        // validate the file against the document type configuration
        // publish an event to notify other services about the validation result
        Boolean isValid = fileValidationService.validateUploadedFileAndPublish(
                fileInput,
                documentTypeEntityToDocumentTypeMapper.toDocumentType(documentTypeEntity));
        if(Boolean.TRUE.equals(isValid)) {
            // upload file to storage (e.g., S3, local file system, etc.)
            Document uploadedDocument = cloudStorageService.uploadFile(fileInput);
            // save document and related metadata to the database
            final DocumentEntity savedDocument = buildDocumentEntityAndRelatedEntities(uploadedDocument, documentTypeEntity);
            // publish an event to notify other services for upload and validation
            return null;
        } else {
            //TODO: replace with an exception that reports all the validation errors
            throw FileValidationException.with("", "").get();
        }
    }

    private DocumentEntity buildDocumentEntityAndRelatedEntities(Document document, DocumentTypeEntity documentTypeEntity) {
        // create a new DocumentVersionEntity from the Document
        DocumentVersionEntity documentVersionEntity = documentVersionEntityToDocumentMapper.toDocumentVersionEntity(document);
        // create a new DocumentMetadataEntity from the Document
        List<DocumentMetadataEntity> documentMetadataEntities = document.getMetadata().stream()
                .map(documentMetadataEntityToMetadataMapper::toDocumentMetadataEntity)
                .toList();
        // create a new DocumentAccessLogEntity from the Document
        DocumentAccessLogEntity documentAccessLogEntity = documentAccessLogEntityToDocumentMapper.toUploadAction(document);
        // create a new DocumentEntity from the Document
        DocumentEntity documentEntity = documentEntityToDocumentMapper.toDocumentEntity(document);
        documentEntity.setDocumentType(documentTypeEntity);
        DocumentEntity savedDocumentEntity = documentRepository.save(documentEntity);
        documentMetadataEntities.forEach(e -> e.setDocument(savedDocumentEntity));
        documentMetadataRepository.saveAll(documentMetadataEntities);
        documentVersionEntity.setDocument(savedDocumentEntity);
        documentVersionRepository.save(documentVersionEntity);
        documentAccessLogEntity.setDocument(savedDocumentEntity);
        documentAccessLogRepository.save(documentAccessLogEntity);
        return savedDocumentEntity;
    }


}
