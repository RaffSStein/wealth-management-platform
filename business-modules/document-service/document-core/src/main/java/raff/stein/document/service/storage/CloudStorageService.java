package raff.stein.document.service.storage;

import raff.stein.document.model.Document;
import raff.stein.document.model.File;

public interface CloudStorageService {

    String getBucketName();

    Document uploadFile(File file);

    Document downloadFile(String documentId);
}
