package org.example.solid_principles.single_responsibility_principle.after;

import java.util.*;

/**
 * RESPONSIBILITY: User Data Persistence (In-Memory Implementation)
 * 
 * This class has ONE reason to change:
 * - Database schema or storage mechanism changes
 * 
 * Benefits:
 * - Easy to test in isolation
 * - Can be easily swapped with MySQL, MongoDB, etc.
 * - Changes to database don't affect business logic
 * - Can implement caching strategies independently
 */
public class InMemoryUserRepository implements UserRepository {
    private Map<String, User> database;
    
    public InMemoryUserRepository() {
        this.database = new HashMap<>();
    }
    
    @Override
    public void save(User user) {
        System.out.println("💾 [Repository] Saving user to database: " + user.getId());
        database.put(user.getId(), user);
    }
    
    @Override
    public User findById(String id) {
        System.out.println("🔍 [Repository] Finding user by ID: " + id);
        return database.get(id);
    }
    
    @Override
    public List<User> findAll() {
        System.out.println("🔍 [Repository] Retrieving all users");
        return new ArrayList<>(database.values());
    }
    
    @Override
    public void update(User user) {
        System.out.println("🔄 [Repository] Updating user: " + user.getId());
        database.put(user.getId(), user);
    }
    
    @Override
    public void delete(String id) {
        System.out.println("🗑️ [Repository] Deleting user: " + id);
        database.remove(id);
    }
}
