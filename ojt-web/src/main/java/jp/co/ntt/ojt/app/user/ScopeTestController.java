package jp.co.ntt.ojt.app.user;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ScopeTestController {
    private int num = 0;

    @RequestMapping("/testScope")
    public void testScope() {
        System.out.println(++num);
    }

    @RequestMapping("/testScope2")
    public void testScope2() {
        System.out.println(++num);
    }
}
