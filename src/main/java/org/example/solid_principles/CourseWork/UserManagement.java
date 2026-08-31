package org.example.solid_principles.CourseWork;

public interface UserManagement {

    void updateUserProfile(Customer customer);
    void changePassword(Customer customer, String newPassword);

}
