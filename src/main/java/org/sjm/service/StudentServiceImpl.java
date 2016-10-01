package org.sjm.service;

import org.sjm.entry.Student;
import org.sjm.mapper.StudentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Component:
 * Description:
 * Date: 16/9/28
 *
 * @author yue.zhang
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    public boolean insert(Student student) {
        return studentMapper.insert(student) > 0;
    }
}
