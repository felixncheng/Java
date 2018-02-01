package com.chengmboy.controller.google.authenticator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cheng_mboy
 */
@RestController
@RequestMapping("google")
public class GoogleAuthenticatorController {

    private final String secret = "LIXS7JCCINANMYBL";

    @GetMapping("auth")
    public String auth(@RequestParam int googleCode) {
        GoogleAuthenticator g = new GoogleAuthenticator();
        boolean auth = g.check_code(secret, googleCode, System.currentTimeMillis());
        if (auth) {
            return "认证通过!";
        } else {
            return "认证失败!";
        }
    }
}
