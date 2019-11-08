package com.xushy.designpatterns.reschain;

public interface Chain<T> {

	Chain<T> getNext();

	void execute(T t);
}
