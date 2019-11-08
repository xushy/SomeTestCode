package com.xushy.designpatterns.reschain;

public class CChain implements Chain<Child> {

	public Chain<Child> getNext() {
		return null;
	}

	public void execute(Child t) {
		if (t.getAge() >= 18 && t.getAge() < 23) {
			System.out.println(t.getName() + "在上大學,年齡" + t.getAge());
		} else {
			System.out.println(t.getName() + "社会青年，年齡" + t.getAge());
		}
	}

}
