package Course;

import java.util.Scanner;

public class Exhibition implements BuyTickets{
	public int tickets = 200;
	public int price = 250;
	
	// ����� ���������� �������
	@Override
	public int getTickets() {
		return this.tickets;
	}
	
	// ����� ��������� ������
	@Override
	public int getPrice() {
		return this.price;
	}
}