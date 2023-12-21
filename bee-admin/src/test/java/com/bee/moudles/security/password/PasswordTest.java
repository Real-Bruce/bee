package com.bee.moudles.security.password;

import com.bee.modules.security.password.PasswordUtils;
import org.junit.Test;

/**
 * @author Bruce
 * @create 2023/12/21
 * @description
 */
public class PasswordTest {

    @Test
    public void testEncodeAndMatches () {

        String str = "admin";
        String password = PasswordUtils.encode(str);

        boolean matchs = PasswordUtils.matchs(str, password);


        System.out.println(password);
        System.out.println(matchs);
    }

}
