package com.sparta.spartansapi.utils;

import com.sparta.spartansapi.mongodb.models.Course;
import com.sparta.spartansapi.mongodb.models.Stream;
import com.sparta.spartansapi.mongodb.models.Spartan;
import org.bson.types.ObjectId;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.index.TextIndexDefinition;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MongoSeeder {
    private MongoTemplate mongoTemplate;

    public MongoSeeder(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    public void addDocuments() {
        List<Spartan> spartans = getJsonArrayToSpartans();
        List<Course> courses = getJsonArrayToCourses();
        List<Stream> streams = getJsonArrayToStreams();

        mongoTemplate.insertAll(spartans);
        mongoTemplate.insertAll(courses);
        mongoTemplate.insertAll(streams);
    }

    public void indexFields() {
        TextIndexDefinition textIndex = new TextIndexDefinition.TextIndexDefinitionBuilder()
                .onFields("firstName", "lastName", "middleName").build();
        mongoTemplate.indexOps(Spartan.class).ensureIndex(textIndex);
    }

    public List<Spartan> getJsonArrayToSpartans() {
        JSONParser parser = new JSONParser();
        List<Spartan> spartans = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader("src/main/java/com/sparta/spartansapi/data/spartans.json"));
            for (Object o : array) {
                JSONObject spartan = (JSONObject) o;

                JSONObject course = (JSONObject) spartan.get("course");
                Course newCourse = new Course((String)course.get("name"));

                JSONObject stream = (JSONObject) spartan.get("stream");
                Stream newStream = new Stream((String)stream.get("name"), (Long)stream.get("duration"));

                Date startDate = Utilities.stringToDate((String)spartan.get("startDate"));

                JSONObject id = (JSONObject) spartan.get("_id");
                String idString = (String)id.get("$oid");

                Spartan newSpartan = new Spartan(
                        idString,
                        (String)spartan.get("firstName"),
                        (String)spartan.get("middleName"),
                        (String)spartan.get("lastName"),
                        startDate,
                        newCourse,
                        newStream,
                        (String)spartan.get("email"),
                        Utilities.calculateEndDate(startDate, newStream)
                );
                spartans.add(newSpartan);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return spartans;
    }

    public List<Course> getJsonArrayToCourses() {
        JSONParser parser = new JSONParser();
        List<Course> courses = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader("src/main/java/com/sparta/spartansapi/data/courses.json"));
            for (Object o : array) {
                JSONObject course = (JSONObject) o;
                Course newCourse = new Course(
                        (String)course.get("name"));
                courses.add(newCourse);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return courses;
    }

    public List<Stream> getJsonArrayToStreams() {
        JSONParser parser = new JSONParser();
        List<Stream> streams = new ArrayList<>();
        try {
            JSONArray array = (JSONArray) parser.parse(new FileReader("src/main/java/com/sparta/spartansapi/data/streams.json"));
            for (Object o : array) {
                JSONObject stream = (JSONObject) o;
                Stream newStream = new Stream(
                        (String)stream.get("name"),
                        (Long)stream.get("duration"));
                streams.add(newStream);
            }
        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }
        return streams;
    }
}
