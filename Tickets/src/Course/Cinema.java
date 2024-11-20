package Course;

import java.util.Scanner;

public class Cinema implements BuyTickets{
	public int tickets = 200;
	public int price = 150;

	// Вывод количества билетов
	@Override
	public int getTickets() {
		return this.tickets;
	}
	
	// Вывод стоимости билета
	@Override
	public int getPrice() {
		return this.price;
	}
}