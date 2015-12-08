package poke.server.storage;

import poke.comm.App.Course;
import poke.server.storage.heap.CourseHeapStorage;

import java.util.List;

/**
 * Created by jsx039 on 12/7/15.
 */
public interface CourseStorage {

    boolean addCourse(CourseHeapStorage.ParseCourse course);

    boolean updateCourse(CourseHeapStorage.ParseCourse course);

    boolean removeCourse(String courseId);

    CourseHeapStorage.ParseCourse findCourse(String courseId);

}
