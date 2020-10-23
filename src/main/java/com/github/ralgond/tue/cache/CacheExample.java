package com.github.ralgond.tue.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.twitter.cache.ConcurrentMapCache;
import com.twitter.cache.FutureCache;
import com.twitter.cache.Refresh;
import com.twitter.util.Await;
import com.twitter.util.Duration;
import com.twitter.util.Function;
import com.twitter.util.Future;

import scala.Function0;
import scala.Function1;

public class CacheExample {

	public static void fromMap() throws Exception {
		ConcurrentMap<String, Future<String>> map = new ConcurrentHashMap<>();

		Function1<String, Future<String>> fn = new Function<String, Future<String>>() {
			public Future<String> apply(String s) {
				return Future.value(s);
			}
		};
		
		Function1<String, Future<String>> fromMap = FutureCache.fromMap(fn, map);
		
		System.out.println(Await.result(fromMap.apply("1")));
	}
	
	public static void standard() throws Exception {
		ConcurrentMap<String, Future<String>> map = new ConcurrentHashMap<>();
	    ConcurrentMapCache<String, String> mapCache = new ConcurrentMapCache<>(map);

	    Function1<String, Future<String>> fn = new Function<String, Future<String>>() {
	      public Future<String> apply(String s) {
	        return Future.value(s);
	      }
	    };
	    
	    Function1<String, Future<String>> std = FutureCache.standard(fn, mapCache);
	    System.out.println(Await.result(std.apply("1")));
	}
	
	public static void fresh() throws Exception {
		AtomicInteger i = new AtomicInteger(0);
		Function0<Future<Integer>> provider = new Function0<Future<Integer>>() {
			public Future<Integer> apply() {
				i.addAndGet(1);
				return Future.value(1);
			}
		};
		
		Function0<Future<Integer>> memoized = Refresh.every(Duration.fromSeconds(1), provider);
		
		Await.result(memoized.apply()).intValue();
		Await.result(memoized.apply()).intValue();
		Thread.sleep(2000);
		Await.result(memoized.apply()).intValue();
		
		System.out.println(i.get());
	}

	public static void main(String args[]) throws Exception {
		fromMap();
		standard();
		fresh();
	}
}
