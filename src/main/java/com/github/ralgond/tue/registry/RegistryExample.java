package com.github.ralgond.tue.registry;


import com.twitter.util.registry.Entry;
import com.twitter.util.registry.GlobalRegistry;
import com.twitter.util.registry.Registry;

import scala.collection.Iterator;

/**
 * The registry is a hierarchical key/value store, where all keys are sequences
 * of Strings, and values are Strings.
 *
 * Keys and values must be non-control ascii.  If you pass in a key or value
 * with an invalid character, the character will silently be removed.  If this
 * makes your key clash with another key, it will overwrite.
 */
public class RegistryExample {

	public static void main(String args[]) {
		Registry registry = GlobalRegistry.get();
	    registry.put("foo", "bar", "baz");
	    registry.put("foo", "qux");
	    registry.put("baz");
	    
	    Iterator<Entry> it = registry.iterator();
	    while (it.hasNext()) {
	    	Entry e = it.next();
	    	System.out.println(e);
	    }
	}
}
