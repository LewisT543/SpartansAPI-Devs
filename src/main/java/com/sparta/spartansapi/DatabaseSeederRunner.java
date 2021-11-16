package com.sparta.spartansapi;

import com.sparta.spartansapi.mongodb.repos.CourseRepository;
import com.sparta.spartansapi.mongodb.repos.SpartanRepository;
import com.sparta.spartansapi.mongodb.repos.StreamRepository;
import com.sparta.spartansapi.utils.MongoSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

@Component
@Order(1)
public class DatabaseSeederRunner implements CommandLineRunner {

    private MongoTemplate mongoTemplate;
    private SpartanRepository spartanRepository;
    private CourseRepository courseRepository;
    private StreamRepository streamRepository;

    public DatabaseSeederRunner(MongoTemplate mongoTemplate,
                                SpartanRepository spartanRepository,
                                CourseRepository courseRepository,
                                StreamRepository streamRepository) {
        this.mongoTemplate = mongoTemplate;
        this.spartanRepository = spartanRepository;
        this.courseRepository = courseRepository;
        this.streamRepository = streamRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        empty();
        seed();
    }

    private void seed() {
        MongoSeeder seeder = new MongoSeeder(mongoTemplate);
        seeder.addDocuments();
    }

    private void empty() {
        this.spartanRepository.deleteAll();
        this.courseRepository.deleteAll();
        this.streamRepository.deleteAll();
    }
}
