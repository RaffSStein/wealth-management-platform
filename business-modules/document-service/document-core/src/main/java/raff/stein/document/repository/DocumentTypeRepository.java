package raff.stein.document.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import raff.stein.document.model.entity.DocumentTypeEntity;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentTypeEntity, Integer> {

    DocumentTypeEntity findByTypeName(String typeName);
}
