package FoV.LoTs.DB.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import FoV.LoTs.DB.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
    public User findOneByEmailAndPassword(String id, String password);

    public User findOneByEmail(String email);
}