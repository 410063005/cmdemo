package com.sunmoonblog.cmdemo;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.plugins.RxJavaPlugins;
import io.reactivex.schedulers.Schedulers;

import static io.reactivex.internal.functions.Functions.emptyConsumer;

public class UndeliverableExceptionTest {

    @Test
    public void disposeBeforeExceptionOccurredWithoutErrorHandler() throws InterruptedException {
        RxJavaPlugins.setErrorHandler(null);

        // 让当前线程不要过早退出
        final CountDownLatch keepRunning = new CountDownLatch(1);

        final CountDownLatch mainThreadLock = new CountDownLatch(1);
        final CountDownLatch workerThreadLock = new CountDownLatch(1);

        Disposable disposable = Observable.fromCallable(
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        mainThreadLock.countDown();
                        workerThreadLock.await();
                        throw new Exception("后台线程有异常, 会被 RxJava 吞掉么");
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(emptyConsumer(),
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                System.err.println("I'm Local Exception Handler");
                                throwable.printStackTrace();
                            }
                        });

        mainThreadLock.await();
        disposable.dispose();
        workerThreadLock.countDown();

        keepRunning.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void notDispose() throws InterruptedException {
        RxJavaPlugins.setErrorHandler(null);

        // 让当前线程不要过早退出
        final CountDownLatch keepRunning = new CountDownLatch(1);

        final CountDownLatch mainThreadLock = new CountDownLatch(1);
        final CountDownLatch workerThreadLock = new CountDownLatch(1);

        Disposable disposable = Observable.fromCallable(
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        mainThreadLock.countDown();
                        workerThreadLock.await();
                        throw new Exception("后台线程有异常, 会被 RxJava 吞掉么");
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(emptyConsumer(),
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                System.err.println("I'm Local Exception Handler");
                                throwable.printStackTrace();
                            }
                        });

        mainThreadLock.await();
        // disposable.dispose();
        workerThreadLock.countDown();

        keepRunning.await(5, TimeUnit.SECONDS);
    }

    @Test
    public void disposeBeforeExceptionOccurredWithErrorHandler() throws InterruptedException {

        RxJavaPlugins.setErrorHandler(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {
                System.err.println("I'm Global Exception Handler");
                throwable.printStackTrace();
            }
        });

        // 让当前线程不要过早退出
        final CountDownLatch keepRunning = new CountDownLatch(1);

        final CountDownLatch mainThreadLock = new CountDownLatch(1);
        final CountDownLatch workerThreadLock = new CountDownLatch(1);

        Disposable disposable = Observable.fromCallable(
                new Callable<Integer>() {
                    @Override
                    public Integer call() throws Exception {
                        mainThreadLock.countDown();
                        workerThreadLock.await();
                        throw new Exception("后台线程有异常, 会被 RxJava 吞掉么");
                    }
                })
                .subscribeOn(Schedulers.io())
                .subscribe(emptyConsumer(),
                        new Consumer<Throwable>() {
                            @Override
                            public void accept(Throwable throwable) throws Exception {
                                System.err.println("I'm Local Exception Handler");
                                throwable.printStackTrace();
                            }
                        });

        mainThreadLock.await();
        disposable.dispose();
        workerThreadLock.countDown();

        keepRunning.await(5, TimeUnit.SECONDS);
    }
}
