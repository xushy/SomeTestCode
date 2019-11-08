package com.xushy.designpatterns.reschain;

import java.util.Random;

public class Test {
	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		Child child = new Child();
		child.setName("张二狗");
		child.setAge(new Random().nextInt(50));
		Chain<Child> chain = new AChain();
		chain.execute(child);

		Child child1 = new Child();
		child1.setName("小明");
		child1.setAge(new Random().nextInt(50));
		chain.execute(child1);

		Child child2 = new Child();
		child2.setName("小紅");
		child2.setAge(new Random().nextInt(50));
		chain.execute(child2);
	}
}
