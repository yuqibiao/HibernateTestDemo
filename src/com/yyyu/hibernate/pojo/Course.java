package com.yyyu.hibernate.pojo;

import java.util.HashSet;
import java.util.Set;

/**
 * 功能：对于course表
 *
 * @author yu
 * @date 2017/7/12.
 */
public class Course {
    private int cId;
    private String cName;

    private Set<Student> students = new HashSet<>();

    public int getcId() {
        return cId;
    }

    public void setcId(int cId) {
        this.cId = cId;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
