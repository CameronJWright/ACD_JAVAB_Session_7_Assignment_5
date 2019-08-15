package session8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class WithdrawThread extends Thread {
	int balance;
	File f;

	public WithdrawThread(int balance) {
		// TODO Auto-generated constructor stub
		this.balance = balance;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		balance = withdraw(balance);
		System.out.println("Actual balance: " + balance);
	}

	public int withdraw(int balance) {
		for (int i = 0; i < 10; i++) {
			balance = balance - 50;
		}
		return balance;
	}
}

class EmailThread extends Thread {
	int balance;

	public EmailThread(int balance) {
		this.balance = balance;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		super.run();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Email: Balance has changed!");

	}
}

class SmsThread extends Thread {
	int balance;

	public SmsThread(int balance) {
		this.balance = balance;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("SMS: Balance has changed!");

	}
}

class LogNotificationsThread extends Thread {
	File f;
	BufferedWriter br;

	public LogNotificationsThread(File f2) {
		this.f = f2;// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		super.run();
		try {
			Thread.sleep(2000);
			br = new BufferedWriter(new FileWriter(f, true));
			br.write("SMS notification and Email notification sent\n");
			System.out.println("Written");
			br.close();
		} catch (IOException |InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

public class WithdrawalMain {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int balance = 1000000;
		File f = new File("src\\session8\\notifications\\notifications.txt");
		WithdrawThread wt = new WithdrawThread(balance);
		EmailThread et = new EmailThread(wt.balance);
		SmsThread st = new SmsThread(wt.balance);
		LogNotificationsThread lnt = new LogNotificationsThread(f);
		wt.start();
		et.start();
		st.start();
		lnt.start();

	}

}
