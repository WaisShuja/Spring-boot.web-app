package trust.example.trust.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import trust.example.trust.beans.User;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {
}
