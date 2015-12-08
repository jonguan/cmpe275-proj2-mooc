package poke.server.storage.heap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import poke.comm.App;
import poke.comm.App.Course;
import poke.server.conf.JsonUtil;
import poke.server.conf.ServerConf;
import poke.server.storage.CourseStorage;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jsx039 on 12/7/15.
 */
public class CourseHeapStorage implements CourseStorage {
    protected static Logger logger = LoggerFactory.getLogger("storage");
    private HashMap<Integer, Course> spaces = new HashMap<Integer, Course>();

    public CourseHeapStorage(ServerConf conf) {
        String filename = conf.getClasslist();
        // expecting filename to be the complete file address
        File coursefile = new File(filename);
        byte[] raw = new byte[(int) coursefile.length()];
        BufferedInputStream br = new BufferedInputStream(new FileInputStream(coursefile));
        br.read(raw);
        conf = JsonUtil.decode(new String(raw), ServerConf.class);
    }

    @Override
    public boolean addCourse(Course course) {
        spaces.put(course.getCourseId(), course);
        return true;

    }

    @Override
    public boolean updateCourse(Course course) {
        spaces.put(course.getCourseId(), course);
        return true;
    }

    @Override
    public boolean removeCourse(Integer courseId) {
        spaces.remove(courseId);
        return true;
    }

    @Override
    public Course findCourse(Integer courseId) {
        return spaces.get(courseId);
    }

}
