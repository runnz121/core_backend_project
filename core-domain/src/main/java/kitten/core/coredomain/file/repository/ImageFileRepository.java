package kitten.core.coredomain.file.repository;

import kitten.core.coredomain.file.entity.ImageFile;
import org.springframework.data.repository.CrudRepository;

public interface ImageFileRepository extends CrudRepository<ImageFile, Long> {
}
