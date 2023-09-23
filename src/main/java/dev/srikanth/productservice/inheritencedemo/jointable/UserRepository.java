package dev.srikanth.productservice.inheritencedemo.jointable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("jt_ur")
public interface UserRepository extends JpaRepository<User, Long> {
    <S extends Mentor> S save(S entity);
}
