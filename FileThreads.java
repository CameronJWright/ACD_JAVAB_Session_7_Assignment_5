package session8;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

class FileWaiting implements Runnable {
	File f;
	BufferedWriter br;

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			Thread.sleep(10000);
			f = new File("src\\session8\\files\\file.txt");
			if (!f.exists()) {
				br = new BufferedWriter(new FileWriter(f, true));
				br.write("It's my program and I want it in 123 seconds!");
				br.close();
			}
			System.out.println("File Created");
		} catch (InterruptedException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

class FileReading implements Runnable {
	File f, vowel, consonant, other;
	BufferedWriter bWrite;
	Reader bRead;

	Thread t;

	StringBuilder sbVowel = new StringBuilder();
	StringBuilder sbConsonant = new StringBuilder();
	StringBuilder sbOther = new StringBuilder();

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			f = new File("src\\session8\\files\\file.txt");
			bRead = new FileReader(f);
			int c = 0;
			while ((c = bRead.read()) != -1) {
				if (isVowel((char) c))
					sbVowel.append((char) c);
				else if (isConsonant((char) c))
					sbConsonant.append((char) c);
				else {
					sbOther = new StringBuilder(sbOther.toString().trim());
					sbOther.append((char) c);
				}
			}
			t = new Thread(new FileWriting(sbVowel.toString(), 0));
			t.start();
			t = new Thread(new FileWriting(sbConsonant.toString(), 1));
			t.start();
			t = new Thread(new FileWriting(sbOther.toString(), 2));
			t.start();

			System.out.println("File Read");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	boolean isVowel(char c) {
		return "AEIOUaeiou".indexOf(c) != -1;
	}

	boolean isConsonant(char c) {
		return "BCDFGHJKLMNPQRSTVWXYZbcdfghjklmnpqrstvwxyz".indexOf(c) != -1;
	}

}

class FileWriting implements Runnable {
	String str;
	int type;
	File f;
	BufferedWriter br;

	public FileWriting(String str, int type) {
		// TODO Auto-generated constructor stub
		this.str = str;
		this.type = type;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			if (type == 0)
				f = new File("src\\session8\\files\\vowels.txt");
			if (type == 1)
				f = new File("src\\session8\\files\\consonants.txt");
			if (type == 2)
				f = new File("src\\session8\\files\\others.txt");
			if (!f.exists()) {
				br = new BufferedWriter(new FileWriter(f, true));
				br.write(str);
				br.close();
			}
			System.out.println("File type " + type + " Created");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

public class FileThreads {

	public static void main(String[] args) {
		Thread t1 = new Thread(new FileWaiting());
		Thread t2 = new Thread(new FileReading());

		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		t2.start();

	}
}
