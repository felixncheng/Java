package com.chengmboy.controller.google.authenticator;

import com.chengmboy.other.google.authenticator.GoogleAuthenticator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 谷歌身份验证器简单web实现
 *
 * @author cheng_mboy
 */
@RestController
@RequestMapping("google")
public class GoogleAuthenticatorController {

    /**
     * 谷歌身份验证器密钥，客户端需要输入的密钥，与服务器端相同。
     * 使用GoogleAuthenticator.generateSecretKey()生成，base32编码，
     * 总长度为SECRET_SIZE*8/5
     */
    private final String secret = "LIXS7JCCINANMYBL";

    @GetMapping("auth")
    public String auth(@RequestParam int googleCode) {
        GoogleAuthenticator g = new GoogleAuthenticator();
        boolean auth = g.checkCode(secret, googleCode, System.currentTimeMillis());
        if (auth) {
            return "认证通过!";
        } else {
            return "认证失败!";
        }
    }
}
