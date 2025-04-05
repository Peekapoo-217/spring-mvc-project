/*
 * package com.hungdev.repositories;
 * 
 * import java.util.List; import java.util.Optional;
 * 
 * import org.springframework.stereotype.Repository;
 * 
 * import com.hungdev.entities.User;
 * 
 * @Repository public interface UserRepository { List<User> findPaged(int
 * pageIndex, int pageSize, int userId); Optional<User> findByUsername(String
 * username); void save(User user); void update(User user); void delete(String
 * username); }
 */
package com.hungdev.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hungdev.entities.User;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.id <> :userId")
    List<User> findPaged(int userId, org.springframework.data.domain.Pageable pageable);

    void deleteByUsername(String username);
}
