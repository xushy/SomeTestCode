package com.xushy.designpatterns.reschain;

public class AChain implements Chain<Child> {
	private static Chain<Child> next = new BChain();

	public Chain<Child> getNext() {
		return next;
	}

	public void execute(Child t) {
		if (t.getAge() < 8) {
			System.out.println(t.getName() + "还没上学，年齡" + t.getAge());
		} else if (14 >= t.getAge() && t.getAge() >= 8) {
			System.out.println(t.getName() + "在上小学，年齡" + t.getAge());
		} else {
			this.getNext().execute(t);
		}
	}

}
