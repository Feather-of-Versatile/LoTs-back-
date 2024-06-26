package fov.lots.DB.Repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import fov.lots.DB.Entity.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, UUID> {
    public User findOneByEmailAndPassword(String id, String password);

    public User findOneByEmail(String email);
}