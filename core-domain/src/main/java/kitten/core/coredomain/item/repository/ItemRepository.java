package kitten.core.coredomain.item.repository;

import kitten.core.coredomain.item.entity.Item;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ItemRepository extends CrudRepository<Item, Long> {

    Optional<Item> findByKey(Long key);
}
