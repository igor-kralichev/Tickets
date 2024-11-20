package Course;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {
	//����� �������� � �������
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// ����� �������� ��������� ������� ����� ����� �������/���������� ����������
	public static int makePurchase(int buyChoice, int finalPrice, String discount, int price) {
		if (discount.equalsIgnoreCase("n")) {
			finalPrice = finalPrice + buyChoice * price;
		} else if (discount.equalsIgnoreCase("y")) {
			finalPrice = 0;
		}
		return finalPrice;
	}

	public static void main(String[] args) {
		// ����������� � ����������� ��������

		// ���������� ����������
		int choice = 1; // ����� �����������
		int buyChoice; // ����� ���������� �������
		int buyStatisticCinema = 0; // ���������� ������� �������
		int buyStatisticTheatre = 0;
		int buyStatisticMC = 0;
		int buyStatisticExhibition = 0;
		String discount; //������� ����������
		int finalPriceCinema = 0; // ������������� ����
		int finalPriceTheatre = 0;
		int finalPriceMC = 0;
		int finalPriceExhibition = 0;
		
		// ���������� ���� � ���� � ��������� ������� �� ������������ 
		// ������������� ������� "���������"
		Helper helper = new Helper();
		// ���������
		helper.getTickets(new Cinema());
		int buyCinema = helper.tickets();
		int priceCinema = helper.price();
		// ��������
		helper.getTickets(new Exhibition());
		int buyExhibition = helper.tickets();
		int priceExhibition = helper.price();
		// ���������
		helper.getTickets(new Theatre());
		int buyTheatre = helper.tickets();
		int priceTheatre = helper.price();
		// ������-�����
		helper.getTickets(new MasterClass());
		int buyMC = helper.tickets();
		int priceMC = helper.price();

		// ������� �������������� "���������"
		// �����������
		StringBuilder builderHello = new StringBuilder();
		builderHello.append("��� ������������ ��������� ������� �������!\n");
		builderHello.append("��� ���������� ������ ��������� ������� 0.\n");
		builderHello.append("��� ����������� �������� �����������:\n");
		builderHello.append("1:����\n");
		builderHello.append("2:���������\n");
		builderHello.append("3:������-�����\n");
		builderHello.append("4:��������\n");
		builderHello.append("��� ��������� ������� ������� 5.\n");
		builderHello.append(ANSI_RESET);

		// �������� ������
		StringBuilder builderSuccess = new StringBuilder();
		builderSuccess.append(ANSI_GREEN + "����� ������� �������. ����� ����� ���������� �� ���� 5.\n" + ANSI_RESET);
		builderSuccess.append("�������� ��������� �������� ��� ��������� ���������.\n");

		// ����� �������� � �������� ������������ �����
		try (Scanner in = new Scanner(System.in)) {
			// ���� �������
			while (choice != 0) {
				System.out.println(builderHello.toString());
				choice = in.nextInt();
				// ������� ������
				switch (choice) {
				// ������� ������ � ����
				case 1:
					System.out.println("������� � ������� �� ���������:" + buyCinema + " ��.\n" + "��������� ������:"
							+ priceCinema + " ���.\n" + "����� ���-�� ������� ������� ����������?");
					buyChoice = in.nextInt();
					in.nextLine();
					if (buyChoice < 0) {
						System.err.println(
								"����� ������� �� ����� ���� �������������!\n" + "�������� ������ �����������.\n");
					} else if (buyChoice < buyCinema) {
						buyCinema = buyCinema - buyChoice;
						System.out.println("� ��� ���� ��������� �� �����������?(y-��/n-���)");
						discount = in.nextLine();
						if (discount.equalsIgnoreCase("y") == false && discount.equalsIgnoreCase("n") == false) {
							buyCinema = buyCinema + buyChoice;
							System.err.println("����� ��������! ��������� ������� ������� ��� ���!\n");
							break;
						} else {
							finalPriceCinema = makePurchase(buyChoice, finalPriceCinema, discount, priceCinema);
							buyStatisticCinema = buyStatisticCinema + buyChoice;
							System.out.println("������ ������...\n" + "��������� ��������� ���������� ����� 3 ���.\n");
							// ��������� ���������� �� 3 ������� (����� ��� ���������� ������ � �����������
							// ������)
							TimeUnit.SECONDS.sleep(3);
							System.out.println(builderSuccess.toString());
						}
					} else {
						System.err.println(
								"�� ������ ����������� ��� ������� �������!\n" + "�������� ������ �����������.\n");
					}
					break;

				// ������� ������ � �����
				case 2:
					System.out.println("������� � ������� �� ���������:" + buyTheatre + " ��.\n" + "��������� ������:"
							+ priceTheatre + " ���.\n" + "����� ���-�� ������� ������� ����������?");
					buyChoice = in.nextInt();
					in.nextLine();
					if (buyChoice < buyTheatre) {
						buyTheatre = buyTheatre - buyChoice;
						System.out.println("� ��� ���� ��������� �� �����������?(Y-��/N-���)");
						discount = in.nextLine();
						if (discount.equalsIgnoreCase("y") == false && discount.equalsIgnoreCase("n") == false) {
							buyTheatre = buyTheatre + buyChoice;
							System.err.println("����� ��������! ��������� ������� ������� ��� ���!\n");
							break;
						}
						finalPriceTheatre = makePurchase(buyChoice, finalPriceTheatre, discount, priceTheatre);
						buyStatisticTheatre = buyStatisticTheatre + buyChoice;
						System.out.println("������ ������...\n" + "��������� ��������� ���������� ����� 3 ���.\n");
						// ��������� ���������� �� 3 ������� (����� ��� ���������� ������ � �����������
						// ������)
						TimeUnit.SECONDS.sleep(3);
						System.out.println(builderSuccess.toString());
					} else {
						System.err.println(
								"�� ������ ����������� ��� ������� �������!\n" + "�������� ������ �����������.\n");
					}
					break;

				// ������� ������ �� ������-�����
				case 3:
					System.out.println("������� � ������� �� ������-�����:" + buyMC + " ��.\n" + "��������� ������:"
							+ priceMC + " ���.\n" + "����� ���-�� ������� ������� ����������?");
					buyChoice = in.nextInt();
					in.nextLine();
					if (buyChoice < buyMC) {
						buyMC = buyMC - buyChoice;
						System.out.println("� ��� ���� ��������� �� �����������?(Y-��/N-���)");
						discount = in.nextLine();
						if (discount.equalsIgnoreCase("y") == false && discount.equalsIgnoreCase("n") == false) {
							buyMC = buyMC + buyChoice;
							System.err.println("����� ��������! ��������� ������� ������� ��� ���!\n");
							break;
						}
						finalPriceMC = makePurchase(buyChoice, finalPriceMC, discount, priceMC);
						buyStatisticMC = buyStatisticMC + buyChoice;
						System.out.println("������ ������...\n" + "��������� ��������� ���������� ����� 3 ���.\n");
						// ��������� ���������� �� 3 ������� (����� ��� ���������� ������ � �����������
						// ������)
						TimeUnit.SECONDS.sleep(3);
						System.out.println(builderSuccess.toString());
					} else {
						System.err.println(
								"�� ������ ����������� ��� ������� �������!\n" + "�������� ������ �����������.\n");
					}
					break;

				// ������� ������ �� ��������
				case 4:
					System.out.println("������� � ������� �� ��������:" + buyExhibition + " ��.\n" + "��������� ������:"
							+ priceExhibition + " ���.\n" + "����� ���-�� ������� ������� ����������?");
					buyChoice = in.nextInt();
					in.nextLine();
					if (buyChoice < buyMC) {
						buyExhibition = buyExhibition - buyChoice;
						System.out.println("� ��� ���� ��������� �� �����������?(Y-��/N-���)");
						discount = in.nextLine();
						if (discount.equalsIgnoreCase("y") == false && discount.equalsIgnoreCase("n") == false) {
							buyExhibition = buyExhibition + buyChoice;
							System.err.println("����� ��������! ��������� ������� ������� ��� ���!\n");
							break;
						}
						finalPriceExhibition = makePurchase(buyChoice, finalPriceExhibition, discount, priceExhibition);
						buyStatisticExhibition = buyStatisticExhibition + buyChoice;
						System.out.println("������ ������...\n" + "��������� ��������� ���������� ����� 3 ���.\n");
						// ��������� ���������� �� 3 ������� (����� ��� ���������� ������ � �����������
						// ������)
						TimeUnit.SECONDS.sleep(3);
						System.out.println(builderSuccess.toString());
					} else {
						System.err.println(
								"�� ������ ����������� ��� ������� �������!\n" + "�������� ������ �����������.\n");
					}
					break;

				case 5:
					// ���� ����������
					StringBuilder builderStatistics = new StringBuilder();
					// ������ � ����
					builderStatistics.append("�� ��������� ������� �� ���������:" + buyStatisticCinema + " ��.\n");
					builderStatistics.append("����� �����:" + finalPriceCinema + " ���.\n\n");
					// ������ � �����
					builderStatistics.append("�� ��������� ������� �� ���������:" + buyStatisticTheatre + " ��.\n");
					builderStatistics.append("����� �����:" + finalPriceTheatre + " ���.\n\n");
					// ������ �� ������-�����
					builderStatistics.append("�� ��������� ������� �� ������-�����:" + buyStatisticMC + " ��.\n");
					builderStatistics.append("����� �����:" + finalPriceMC + " ���.\n\n");
					// ������ �� ��������
					builderStatistics.append("�� ��������� ������� �� ��������:" + buyStatisticExhibition + " ��.\n");
					builderStatistics.append("����� �����:" + finalPriceExhibition + " ���.\n\n");

					// ����� ����������
					System.out.println(builderStatistics.toString());
					break;

					// ����� �� �������
				case 0:
					System.err.println("���������� ������...");
					System.exit(0);
				}

				// �������� �� ������� ��������
				if (choice < 0 || choice > 5) {
					System.err.println("�������� ����� �� 0 �� 5!");
				}
			}
		}

		// ��������� ���������� ����� �����
		catch (Exception e) {
			System.err.println("������� ����� ������ �����.\n" + "������������� ��������� ��� �����������.");

		}
	}
}