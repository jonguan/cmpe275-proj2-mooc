package poke.server.storage;

import poke.comm.App.Course;

import java.util.List;

/**
 * Created by jsx039 on 12/7/15.
 */
public interface CourseStorage {

    boolean addCourse(Course course);

    boolean updateCourse(Course course);

    boolean removeCourse(Integer courseId);

    Course findCourse(Integer courseId);

}
