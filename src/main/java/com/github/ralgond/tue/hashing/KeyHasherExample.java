package com.github.ralgond.tue.hashing;

import com.twitter.hashing.*;

public class KeyHasherExample {

	public static void  main(String[] args) {
		KeyHasher kh = KeyHashers.byName("fnv");
		System.out.println(kh.hashKey(new String("abc").getBytes()));
	}
}
