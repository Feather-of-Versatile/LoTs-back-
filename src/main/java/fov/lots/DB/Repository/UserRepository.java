package FoV.LoTs.DB.Repository;

import FoV.LoTs.DB.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<User, UUID> {
    public User findOneByEmailAndPassword(String id, String password);

    public User findOneByEmail(String email);
}