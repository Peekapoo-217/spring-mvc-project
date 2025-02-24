/*
 * package com.hungdev.repositories.inmemory;
 * 
 * import java.util.ArrayList; import java.util.Arrays; import java.util.List;
 * import java.util.Optional;
 * 
 * import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
 * import org.springframework.security.crypto.password.PasswordEncoder; import
 * org.springframework.stereotype.Repository;
 * 
 * import com.hungdev.entities.User; import com.hungdev.entities.UserRole;
 * import com.hungdev.repositories.UserRepository;
 * 
 * @Repository public class InMemoryUserRepository implements UserRepository {
 * private static List<User> storedUsers;
 * 	
 * public InMemoryUserRepository() { PasswordEncoder passwordEncoder = new
 * BCryptPasswordEncoder(); storedUsers = new ArrayList<>( Arrays.asList(new
 * User(1, "hungdev", passwordEncoder.encode("password"), UserRole.ADMIN), new
 * User(2, "cfundev", passwordEncoder.encode("password"), UserRole.USER))); }
 * 
 * @Override public Optional<User> findByUsername(String username) { for (User
 * user : storedUsers) { if (username.equals(user.getUsername())) { return
 * Optional.of(user); } } return Optional.empty(); }
 * 
 * @Override public List<User> findPaged(int pageIndex, int pageSize) { // TODO
 * Auto-generated method stub return null; }
 * 
 * 
 * 
 * }
 */