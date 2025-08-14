package raff.stein.document.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import raff.stein.document.exception.FileValidationException;
import raff.stein.document.model.Document;
import raff.stein.document.model.DocumentType;
import raff.stein.document.model.File;
import raff.stein.document.repository.DocumentRepository;
import raff.stein.document.service.validation.FileValidationResult;
import raff.stein.document.service.validation.FileValidatorProvider;
import raff.stein.platformcore.exception.ErrorCode;

@Service
@RequiredArgsConstructor
@Slf4j
public class DocumentService {

    private final DocumentRepository documentRepository;
    private final DocumentTypeService documentTypeService;

    public Document uploadDocument(File fileInput) {
        // get the document type (configuration) from the input file
        DocumentType documentType = documentTypeService.getDocumentType(fileInput.getDocumentType());
        FileValidationResult fileValidationResult = FileValidatorProvider.getUploadFileValidator().validate(
                fileInput,
                new FileValidationResult(),
                documentType);
        if(fileValidationResult.isValid()) {
            // upload the file
            // upload file to storage (e.g., S3, local file system, etc.)
            // save document and related metadata to the database
            // publish an event to notify other services for upload and validation
            return null;
        } else {
            // TODO: publish an event to notify other services about the validation failure
            // TODO: log the validation errors
            //TODO: replace with an exception that reports all the validation errors
            FileValidationException.of(ErrorCode.DOCUMENT_UPLOAD_VALIDATION_ERROR, "", "");
        }
        return null;
    }


}
