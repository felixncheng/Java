package com.chengmboy.mysql;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import com.chengmboy.Application;
import com.chengmboy.app.model.People;
import com.chengmboy.app.mybatis.PeopleMapper;
import com.sun.org.apache.xpath.internal.SourceTree;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import tk.mybatis.mapper.entity.Condition;

/**
 * @author cheng_mboy
 */
@SpringBootTest(classes = Application.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class IndexTest {

    public static char[] alphabets = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z',
            'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};
    @Autowired
    PeopleMapper peopleMapper;

    public static char next() {
        Random random = new Random();
        int i = random.nextInt(52);
        return alphabets[i];
    }

    public static String next(int size) {
        StringBuilder result = new StringBuilder();
        while (size > 0) {
            result.append(next());
            size--;
        }
        return result.toString();
    }

    @Test
    public void insertBitch() {
        long cost = 0;
        for (int i = 1; i > 0; i--) {
            List<People> list = new ArrayList<>();
            int sum = 10000;
            while (sum > 0) {
                People people = new People();
                Random random = new Random();
                people.setAge((short) random.nextInt(99));
                people.setHeight(random.nextInt(1000000));
                people.setWeight(random.nextInt(1000000));
                people.setName(next(10));
                people.setAddress(next(12));
                list.add(people);
                sum--;
            }
            long startTime = System.nanoTime();
            peopleMapper.insertList(list);
            long endTime = System.nanoTime();
            cost += (endTime - startTime);
        }
        System.out.println(cost);
    }

    @Test
    public void deleteAll() {
        int sum = 100 * 100000;
        while (sum > 0) {
            sum -= 1000;
            Condition condition = new Condition(People.class);
            condition.createCriteria().andGreaterThan("id", sum);
            peopleMapper.deleteByCondition(condition);
        }
    }
}
