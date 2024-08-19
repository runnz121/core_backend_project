package kitten.core.coredomain.point.repository;

import kitten.core.coredomain.point.entity.Point;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Point, Long> {
}
