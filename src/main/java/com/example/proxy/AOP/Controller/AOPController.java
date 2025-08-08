package com.example.proxy.AOP.Controller;

import com.example.proxy.AOP.Service.AOPService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("aop")
@RequiredArgsConstructor
public class AOPController {
    private final AOPService aopService;

    @GetMapping("/test")
    public String test() {
        return aopService.doSomething();
    }

    @GetMapping("/testEx/{bool}")
    public String testEx(@PathVariable boolean bool) {
        return aopService.doSomethingWithException(bool);
    }
}
