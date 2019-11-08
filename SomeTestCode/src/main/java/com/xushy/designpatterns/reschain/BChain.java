package com.xushy.designpatterns.reschain;

public class BChain implements Chain<Child> {

	private static final Chain<Child> next = new CChain();

	public Chain<Child> getNext() {
		return next;
	}

	public void execute(Child t) {
		if (t.getAge() > 14 && t.getAge() < 18) {
			System.out.println(t.getName() + "在上初中,年齡" + t.getAge());
		} else {
			this.getNext().execute(t);
		}
	}

}
