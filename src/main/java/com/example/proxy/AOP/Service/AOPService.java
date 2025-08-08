package com.example.proxy.AOP.Service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AOPService {

    public String doSomething() {
        System.out.println("doSomething method ");
    return "doSomething method";
    }

    public String doSomethingWithException(boolean throwException) {
        if (throwException==true) {
            throw new RuntimeException("Exception thrown in doSomethingWithException method ");
        }
     return "doSomethingWithException method";
    }
}
