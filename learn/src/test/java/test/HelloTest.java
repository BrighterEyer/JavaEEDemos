package test;


import com.gwc.learn.entity.Person;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.data.mongodb.core.query.Update.update;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring/spring-dao.xml")
public class HelloTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void test() {
        // 先把数据清空
        mongoTemplate.dropCollection(Person.class);

        Person person = new Person("程高伟", 18);
        // 增
        mongoTemplate.insert(person);
        // 查
        Person personFind = mongoTemplate.findById(person.getId(), Person.class);
        logger.info("findById:{}", personFind);
        // 改
        mongoTemplate.updateFirst(new Query(where("name").is("程高伟")), update("age", 20), Person.class);
        // 该之后再查
        personFind = mongoTemplate.findById(person.getId(), Person.class);
        logger.info("修改之后:{}", personFind);
        // 删
        mongoTemplate.remove(person);

        Person anotherPerson = new Person("张三", 18);
        Person anotherPerson1 = new Person("张三", 18);
        mongoTemplate.insert(anotherPerson);
        mongoTemplate.insert(anotherPerson1);
        // 查询所有
        logger.info("findAll:{}", mongoTemplate.findAll(Person.class));
    }

}