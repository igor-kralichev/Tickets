package Course;

import java.util.Scanner;

public class MasterClass implements BuyTickets{
	public int tickets = 200;
	public int price = 500;

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