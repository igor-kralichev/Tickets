package Course;

import java.util.Scanner;

public class Cinema implements BuyTickets{
	public int tickets = 200;
	public int price = 150;

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