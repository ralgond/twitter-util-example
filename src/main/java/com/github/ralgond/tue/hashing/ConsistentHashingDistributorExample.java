package com.github.ralgond.tue.hashing;

import java.util.Arrays;
import java.util.List;

import com.twitter.hashing.*;

import scala.collection.JavaConverters;
import scala.collection.immutable.Seq;


public class ConsistentHashingDistributorExample {

	public static void main(String args[]) {
		List<HashNode<Integer>> nodeList = Arrays.asList(
				new HashNode<>("10.0.1.1", 600, 1),
				new HashNode<>("10.0.1.2", 300, 2),
				new HashNode<>("10.0.1.3", 200, 3),
				new HashNode<>("10.0.1.4", 350, 4),
				new HashNode<>("10.0.1.5", 1000, 5),
				new HashNode<>("10.0.1.6", 800, 6),
				new HashNode<>("10.0.1.7", 950, 7),
				new HashNode<>("10.0.1.8", 100, 8));
		Seq<HashNode<Integer>> nodes = JavaConverters.asScalaIteratorConverter(nodeList.iterator()).asScala().toSeq();
		
		ConsistentHashingDistributor<Integer> ketamaDistributor = new ConsistentHashingDistributor<Integer>(nodes, 160, false);
		
		System.out.println(ketamaDistributor.nodeForHash(-166124121512512L));
		System.out.println(ketamaDistributor.nodeForHash(8796093022208L));
		System.out.println(ketamaDistributor.nodeForHash(4312515125124L));
		System.out.println(ketamaDistributor.nodeForHash(-8192481414141L));
		System.out.println(ketamaDistributor.nodeForHash(-9515121512312L));
	}
}
