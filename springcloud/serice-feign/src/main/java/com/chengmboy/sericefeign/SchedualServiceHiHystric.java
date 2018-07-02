package com.chengmboy.sericefeign;

import org.springframework.stereotype.Component;

/**
 * @author cheng_mboy
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi {
    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry "+name;
    }
}