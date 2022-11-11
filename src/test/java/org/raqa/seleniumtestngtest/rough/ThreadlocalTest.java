package org.raqa.seleniumtestngtest.rough;

public class ThreadlocalTest {
	public static void main(String[] args) {
		Thread t1 = new Thread(new MyRunnable(), "first");
		Thread t2 = new Thread(new MyRunnable(), "second");
		Thread t3 = new Thread(new MyRunnable(), "third");
		Thread t4 = new Thread(new MyRunnable(), "four");
		Thread t5 = new Thread(new MyRunnable(), "five");
		Thread t6 = new Thread(new MyRunnable(), "six");
		t1.start();
		t2.start();
		t3.start();
		t4.start();
		t5.start();
		t6.start();
	}

	public static class MyRunnable implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			//ThreadLocal<String> val = new ThreadLocal<String>();
String val2 = Thread.currentThread().getName();
			//val.set(Thread.currentThread().getName());
			System.out.println(Thread.currentThread().getName() + " " + val2);
		}

	}

}
