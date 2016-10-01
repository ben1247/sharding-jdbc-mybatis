package org.sjm.service;

import org.sjm.entry.User;

import java.util.List;

/**
 * Component:
 * Description:
 * Date: 16/9/28
 *
 * @author yue.zhang
 */
public interface UserService {

    boolean insert(User u);

    List<User> findAll();

    List<User> findByUserIds(List<Integer> ids);

    void transactionTestSucess();

    void transactionTestFailure() throws IllegalAccessException;

}
