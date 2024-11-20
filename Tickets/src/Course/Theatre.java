package Course;

import java.util.Scanner;

public class Theatre implements BuyTickets{
	public int tickets = 200;
	public int price = 300;
	
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