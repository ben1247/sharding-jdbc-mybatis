package org.sjm.mapper;

import org.sjm.entry.Student;

import java.util.List;

/**
 * Component:
 * Description:
 * Date: 16/9/28
 *
 * @author yue.zhang
 */
public interface StudentMapper {

    Integer insert(Student student);

    List<Student> findAll();

    List<Student> findByStudentIds(List<Integer> studentIds);

}
