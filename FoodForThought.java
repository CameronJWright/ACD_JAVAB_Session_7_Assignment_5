package session8;

import java.util.Scanner;

class ThreadFood extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("FOOD");

	}

}

class ThreadFish extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("FISH");

	}

}

class ThreadFoodForFish extends Thread {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("FOOD FOR FISH");

	}

}

class ThreadNumber extends Thread {
	int number;

	public ThreadNumber(int number) {
		// TODO Auto-generated constructor stub
		this.number = number;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println(number);
	}

}

public class FoodForThought {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int n;
		Scanner scan = new Scanner(System.in);
		Thread t1,t2,t3,t4 = null;

		//n = scan.nextInt();

		for (int i = 1; i < 11; i++) {

			if (i % 2 == 0) {
				t1 = new ThreadFood();
				t1.start();
				try {
					t1.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			} else if (i % 3 == 0) {
				t2 = new ThreadFish();
				t2.start();
				try {
					t2.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else if (i % 5 == 0) {
				t3 = new ThreadFoodForFish();
				t3.start();
				try {
					t3.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				t4 = new ThreadNumber(i);
				t4.start();
				try {
					t4.join();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

	}

}
