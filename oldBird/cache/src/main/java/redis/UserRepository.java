package redis;

import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author cheng_mboy
 * @create 2017-06-03-23:39
 */
@CacheConfig(cacheNames = "user")
public interface UserRepository extends JpaRepository<User,Long> {
    @Cacheable
    User findByName(String name);

    User findByNameAndAge(String name, int age);

    @CachePut
    User save(User user);

    @Query(value = "select id from user" ,nativeQuery = true)
    Long[] findIds();
}
