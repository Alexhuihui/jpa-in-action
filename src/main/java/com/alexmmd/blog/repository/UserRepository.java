package com.alexmmd.blog.repository;

import com.alexmmd.blog.domain.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * description:
 * 用户资源接口
 *
 * @author 清风与明月
 * @create 2019-01-14 10:16
 */
public interface UserRepository extends CrudRepository<User, Long> {
}
