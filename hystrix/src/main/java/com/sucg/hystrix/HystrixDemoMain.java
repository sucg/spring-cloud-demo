package com.sucg.hystrix;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import rx.Observable;
import rx.functions.Action1;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class HystrixDemoMain {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String s1 = new CommandHelloWorld("Bob").execute();
        System.out.println(s1);
        Future<String> s2 = new CommandHelloWorld("Bob").queue();
        System.out.println(s2.get());
        Observable<String> s3 = new CommandHelloWorld("Bob").observe();
        s3.subscribe(new Action1<String>() {
            @Override
            public void call(String s) {
                System.out.println("ssss:" + s);
            }
        });
        System.out.println(s3.toBlocking().toFuture().get());
    }

    static class CommandHelloWorld extends HystrixCommand<String> {

        private final String name;

        public CommandHelloWorld(String name) {
            super(HystrixCommandGroupKey.Factory.asKey("ExampleGroup"));
            this.name = name;
        }

        @Override
        protected String run() {
            return "Hello " + name + "!";
        }
    }
    
}
