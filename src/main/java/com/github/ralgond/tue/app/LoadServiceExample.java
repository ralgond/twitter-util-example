package com.github.ralgond.tue.app;

import java.util.Arrays;
import java.util.Collections;
import java.util.ServiceLoader;

import com.twitter.app.LoadService;

import scala.collection.immutable.Seq;

public class LoadServiceExample {

	private interface Iface {
	}

	private static class Impl implements Iface {
	}

	private static class Impl2 implements Iface {
	}

	private interface Apply {
	}

	public static void bindingSingleImplementation() {
		LoadService.Binding<Iface> binding = new LoadService.Binding<>(Iface.class, new Impl());
		System.out.println(binding.implementations());
	}

	public static void bindingMultipleImplementation() {
		LoadService.Binding<Iface> binding = new LoadService.Binding<>(Iface.class,
				Arrays.asList(new Impl2(), new Impl()));
		System.out.println(binding.implementations());
	}

	public static void apply() {
	    Seq<Apply> applys = LoadService.apply(Apply.class);
	    System.out.println(applys);
	}
	
	public static void apply2() {
	    Seq<IApply> applys = LoadService.apply(IApply.class);
	    System.out.println(applys); //empty! is here a bug?
	} 
	
	public static void apply3() {
		ServiceLoader<IApply> loaders = ServiceLoader.load(IApply.class);
		int i = 0;
		for (IApply iface : loaders) {
			i++;
		}
		System.out.println(i);
	}
	
	public static void main(String args[]) {
		bindingSingleImplementation();
		bindingMultipleImplementation();
		apply();
		apply2();
		apply3();
	}
}
