package com.user.repository;


import com.user.entity.User;
import com.user.projection.UserDetailsProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findByUsername(String username);
    @Query(value = "select ID,USERNAME,EMAIL from user_accounts where ID=:id",nativeQuery = true)
    Optional<UserDetailsProjection> findUserDetailsById(Long id);
}
