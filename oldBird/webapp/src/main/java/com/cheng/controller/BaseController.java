/*
package com.cheng.controller;


import com.cheng.model.School;
import org.redisson.api.RMapCache;
import org.redisson.api.RedissonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

*/
/**
 * @author cheng_mboy
 *//*

@RestController
@RequestMapping("school")
public class BaseController {

    private final RedissonClient client;

    public BaseController(RedissonClient client) {
        this.client = client;
    }

    @GetMapping
    public School hello(@RequestParam int id) throws InterruptedException {
        RMapCache<Integer, School> schoolCache = client.getMapCache("School");
        School school = schoolCache.computeIfAbsent(id, i -> provider(id));
        return school;
    }

    private School provider(int id) {
        System.out.println("no cache");
        School school = new School();
        school.setId(id);
        school.setName("China School");
        return school;
    }

    @GetMapping("put")
    private void put(@RequestParam int id, @RequestParam String name) {
        School newSchool = new School();
        newSchool.setId(id);
        newSchool.setName(name);
        RMapCache<Integer, School> schoolCache = client.getMapCache("School");
        schoolCache.put(newSchool.getId(), newSchool);
    }
}
*/
