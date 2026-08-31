package org.example.solid_principles.single_responsibility_principle.after;

import java.util.List;

/**
 * Interface for user persistence operations
 * 
 * Using an interface allows us to:
 * - Switch between different implementations (InMemory, MySQL, MongoDB, etc.)
 * - Test with mock implementations
 * - Follow Dependency Inversion Principle
 */
public interface UserRepository {
    void save(User user);
    User findById(String id);
    List<User> findAll();
    void update(User user);
    void delete(String id);
}
