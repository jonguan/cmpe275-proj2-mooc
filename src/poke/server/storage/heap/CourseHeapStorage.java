package poke.server.storage.heap;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;
import com.oracle.javafx.jmx.json.JSONReader;
import org.codehaus.jackson.map.util.JSONPObject;
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
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by jsx039 on 12/7/15.
 */
public class CourseHeapStorage implements CourseStorage {
    protected static Logger logger = LoggerFactory.getLogger("storage");
    private HashMap<String, ParseCourse> spaces = new HashMap<String, ParseCourse>();
    List coursesList = new ArrayList<>();

    public CourseHeapStorage(ServerConf conf) {
        String filename = conf.getClasslist();
        // expecting filename to be the complete file address
        URL url = getClass().getResource(filename);
        File coursefile = new File(url.getPath());
//        File coursefile = new File(Paths.get(filename));
        Gson mygson = new Gson();

        try{
            JsonReader reader = new JsonReader(new InputStreamReader(new FileInputStream(coursefile)));
            JsonParser jsonParser = new JsonParser();
            JsonArray courseArray = jsonParser.parse(reader).getAsJsonArray();
            for ( JsonElement jsonCourse : courseArray ) {
                logger.debug("course is " + jsonCourse);

                ParseCourse course = mygson.fromJson(jsonCourse, ParseCourse.class);
                this.addCourse(course);
            }
            logger.debug("courses are " + spaces);
        } catch (Exception e) {
            logger.error("Error: " + e);
        }

    }

    @Override
    public boolean addCourse(ParseCourse course) {
        spaces.put(course.getCourseid(), course);
        return true;

    }

    @Override
    public boolean updateCourse(ParseCourse course) {
        spaces.put(course.getCourseid(), course);
        return true;
    }

    @Override
    public boolean removeCourse(String courseId) {
        spaces.remove(courseId);
        return true;
    }

    @Override
    public ParseCourse findCourse(String courseId) {
        return spaces.get(courseId);
    }

    public class ParseCourse{
        public String coursename;
        public String courseid;
        public String coursedescription;

        public String getCourseid() {
            return courseid;
        }
        public String getCoursename() {
            return coursename;
        }
        public String getCoursedescription() {
            return coursedescription;
        }
    }

}
