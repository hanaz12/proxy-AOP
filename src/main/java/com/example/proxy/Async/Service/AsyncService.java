package com.example.proxy.Async.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import java.util.concurrent.CompletableFuture;


@Service
public class AsyncService {

    @Async
public void longRunningTask(){
    System.out.println("Async thread +" + Thread.currentThread().getName());
    try{
        Thread.sleep(10000);
    }catch (InterruptedException e){
        e.printStackTrace();
    }
        System.out.println("Async thread is done "+Thread.currentThread().getName());
}

@Async
public CompletableFuture<String> asyncWithREsult(){
    System.out.println("working in thread "+Thread.currentThread().getName());
    try{
        Thread.sleep(3000);
    }catch (InterruptedException e){
        e.printStackTrace();
    }
    return CompletableFuture.completedFuture("Result is ready");
}
@Async
    public CompletableFuture<String> asyncWithError(){
        try{
            if (true) throw new RuntimeException("error");
        }catch (Exception e){
            return CompletableFuture.failedFuture(e);
        }
        return CompletableFuture.completedFuture("Success");
}
@Async
public void runAsync(){
    System.out.println("Run Async : "+Thread.currentThread().getName());
}
public void callFromInside(){
        System.out.println("callFromInside "+Thread.currentThread().getName());
        this.runAsync();  // won't create a new thread as we call the method by real object not a proxy one

}

    @Async
    public void runAsyncWithCustomExecutor() {
        System.out.println(" runAsyncWithCustomExecutor(): " + Thread.currentThread().getName());
    }

}
