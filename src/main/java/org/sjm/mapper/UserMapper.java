package org.sjm.mapper;

import org.sjm.entry.User;

import java.util.List;

/**
 * Component:
 * Description:
 * Date: 16/9/28
 *
 * @author yue.zhang
 */
public interface UserMapper {

    Integer insert(User user);

    List<User> findAll();

    List<User> findByUserIds(List<Integer> userIds);

}
