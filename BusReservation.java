package session8;

class Bus {
	int routeNo;
	final Seat[] seatsAvailable = new Seat[100];
	int count = 0;

	public Bus() {
		// TODO Auto-generated constructor stub
	}

	public Bus(int routeNo) {
		this.routeNo = routeNo;
	}

	synchronized void reserveSeat(String passengerName) {
		if (count < 100) {
			seatsAvailable[count] = new Seat(passengerName);
			count++;
			System.out.println("Seat Reserved!");
		} else
			System.out.println("No seats available!");
	}

	synchronized void reserveSeat(String passengerName, int amt) {
		for (int i = 0; i < amt; i++) {
			if (count < 100) {
				seatsAvailable[count] = new Seat(passengerName);
				count++;
			} else
				System.out.println("No seats available!");
		}
	}

	void display() {
		for (int i = 0; i < count; i++)
			System.out.println(seatsAvailable[i].passengerName + " ");
	}

}

class Seat {
	String passengerName;

	public Seat(String passengerName) {
		this.passengerName = passengerName;
	}
}

class BusThread extends Thread {
	Bus b;

	public BusThread(Bus b) {
		this.b = b;
	}

	@Override
	public void run() {
		b.reserveSeat("Jim", 20);
	}
}

public class BusReservation {

	public static void main(String[] args) {
		Bus b = new Bus(001);

		BusThread b1 = new BusThread(b);
		BusThread b2 = new BusThread(b);
		BusThread b3 = new BusThread(b);
		b1.start();
		b2.start();
		b3.start();

		try {
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// b.display();
		System.out.println("Seats booked: " + b.count);
	}

}
