package Course;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class App {
	//Цвета символов в консоли
	public static final String ANSI_RESET = "\u001B[0m";
	public static final String ANSI_BLACK = "\u001B[30m";
	public static final String ANSI_RED = "\u001B[31m";
	public static final String ANSI_GREEN = "\u001B[32m";
	public static final String ANSI_YELLOW = "\u001B[33m";
	public static final String ANSI_BLUE = "\u001B[34m";
	public static final String ANSI_PURPLE = "\u001B[35m";
	public static final String ANSI_CYAN = "\u001B[36m";
	public static final String ANSI_WHITE = "\u001B[37m";

	// Метод подсчёта стоимости билетов после ввода наличия/отсутствия абонемента
	public static int makePurchase(int buyChoice, int finalPrice, String discount, int price) {
		if (discount.equalsIgnoreCase("n")) {
			finalPrice = finalPrice + buyChoice * price;
		} else if (discount.equalsIgnoreCase("y")) {
			finalPrice = 0;
		}
		return finalPrice;
	}

	public static void main(String[] args) {
		// Приветствие и предложение действий

		// Объявление переменных
		int choice = 1; // Выбор мероприятия
		int buyChoice; // Выбор количества билетов
		int buyStatisticCinema = 0; // Статистика покупки билетов
		int buyStatisticTheatre = 0;
		int buyStatisticMC = 0;
		int buyStatisticExhibition = 0;
		String discount; //Наличие абонемента
		int finalPriceCinema = 0; // Окончательная цена
		int finalPriceTheatre = 0;
		int finalPriceMC = 0;
		int finalPriceExhibition = 0;
		
		// Количество мест в зале и стоимость билетов по мероприятиям 
		// Поведенческий паттерн "стратегия"
		Helper helper = new Helper();
		// Киносеанс
		helper.getTickets(new Cinema());
		int buyCinema = helper.tickets();
		int priceCinema = helper.price();
		// Выставка
		helper.getTickets(new Exhibition());
		int buyExhibition = helper.tickets();
		int priceExhibition = helper.price();
		// Спектакль
		helper.getTickets(new Theatre());
		int buyTheatre = helper.tickets();
		int priceTheatre = helper.price();
		// Мастер-класс
		helper.getTickets(new MasterClass());
		int buyMC = helper.tickets();
		int priceMC = helper.price();

		// Паттерн проектирования "строитель"
		// Приветствие
		StringBuilder builderHello = new StringBuilder();
		builderHello.append("Вас приветствует программа покупки билетов!\n");
		builderHello.append("Для завершения работы программы введите 0.\n");
		builderHello.append("Для продолжения выберите мероприятие:\n");
		builderHello.append("1:Кино\n");
		builderHello.append("2:Спектакль\n");
		builderHello.append("3:Мастер-класс\n");
		builderHello.append("4:Выставка\n");
		builderHello.append("Для просмотра билетов нажмите 5.\n");
		builderHello.append(ANSI_RESET);

		// Успешная оплата
		StringBuilder builderSuccess = new StringBuilder();
		builderSuccess.append(ANSI_GREEN + "Билет успешно оплачен. Заказ можно посмотреть по коду 5.\n" + ANSI_RESET);
		builderSuccess.append("Выберите следующее действие или выключите программу.\n");

		// Выбор действия и проверка правильности ввода
		try (Scanner in = new Scanner(System.in)) {
			// Цикл выборки
			while (choice != 0) {
				System.out.println(builderHello.toString());
				choice = in.nextInt();
				// Выборка выбора
				switch (choice) {
				// Покупка билета в кино
				case 1:
					System.out.println("Билетов в наличии на Киносеанс:" + buyCinema + " шт.\n" + "Стоимость билета:"
							+ priceCinema + " руб.\n" + "Какое кол-во билетов желаете приобрести?");
					buyChoice = in.nextInt();
					in.nextLine();
					if (buyChoice < 0) {
						System.err.println(
								"Число билетов не может быть отрицательным!\n" + "Выберите другое мероприятие.\n");
					} else if (buyChoice < buyCinema) {
						buyCinema = buyCinema - buyChoice;
						System.out.println("У Вас есть абонемент на мероприятие?(y-Да/n-Нет)");
						discount = in.nextLine();
						if (discount.equalsIgnoreCase("y") == false && discount.equalsIgnoreCase("n") == false) {
							buyCinema = buyCinema + buyChoice;
							System.err.println("Ответ неверный! Повторите попытку покупки ещё раз!\n");
							break;
						} else {
							finalPriceCinema = makePurchase(buyChoice, finalPriceCinema, discount, priceCinema);
							buyStatisticCinema = buyStatisticCinema + buyChoice;
							System.out.println("Оплата билета...\n" + "Программа продолжит выполнение через 3 сек.\n");
							// Остановка выполнения на 3 секунды (нужно для разделения оплаты и продолжения
							// работы)
							TimeUnit.SECONDS.sleep(3);
							System.out.println(builderSuccess.toString());
						}
					} else {
						System.err.println(
								"На данное мероприятие нет столько билетов!\n" + "Выберите другое мероприятие.\n");
					}
					break;

				// Покупка билета в театр
				case 2:
					System.out.println("Билетов в наличии на Спектакль:" + buyTheatre + " шт.\n" + "Стоимость билета:"
							+ priceTheatre + " руб.\n" + "Какое кол-во билетов желаете приобрести?");
					buyChoice = in.nextInt();
					in.nextLine();
					if (buyChoice < buyTheatre) {
						buyTheatre = buyTheatre - buyChoice;
						System.out.println("У Вас есть абонемент на мероприятие?(Y-Да/N-Нет)");
						discount = in.nextLine();
						if (discount.equalsIgnoreCase("y") == false && discount.equalsIgnoreCase("n") == false) {
							buyTheatre = buyTheatre + buyChoice;
							System.err.println("Ответ неверный! Повторите попытку покупки ещё раз!\n");
							break;
						}
						finalPriceTheatre = makePurchase(buyChoice, finalPriceTheatre, discount, priceTheatre);
						buyStatisticTheatre = buyStatisticTheatre + buyChoice;
						System.out.println("Оплата билета...\n" + "Программа продолжит выполнение через 3 сек.\n");
						// Остановка выполнения на 3 секунды (нужно для разделения оплаты и продолжения
						// работы)
						TimeUnit.SECONDS.sleep(3);
						System.out.println(builderSuccess.toString());
					} else {
						System.err.println(
								"На данное мероприятие нет столько билетов!\n" + "Выберите другое мероприятие.\n");
					}
					break;

				// Покупка билета на мастер-класс
				case 3:
					System.out.println("Билетов в наличии на Мастер-класс:" + buyMC + " шт.\n" + "Стоимость билета:"
							+ priceMC + " руб.\n" + "Какое кол-во билетов желаете приобрести?");
					buyChoice = in.nextInt();
					in.nextLine();
					if (buyChoice < buyMC) {
						buyMC = buyMC - buyChoice;
						System.out.println("У Вас есть абонемент на мероприятие?(Y-Да/N-Нет)");
						discount = in.nextLine();
						if (discount.equalsIgnoreCase("y") == false && discount.equalsIgnoreCase("n") == false) {
							buyMC = buyMC + buyChoice;
							System.err.println("Ответ неверный! Повторите попытку покупки ещё раз!\n");
							break;
						}
						finalPriceMC = makePurchase(buyChoice, finalPriceMC, discount, priceMC);
						buyStatisticMC = buyStatisticMC + buyChoice;
						System.out.println("Оплата билета...\n" + "Программа продолжит выполнение через 3 сек.\n");
						// Остановка выполнения на 3 секунды (нужно для разделения оплаты и продолжения
						// работы)
						TimeUnit.SECONDS.sleep(3);
						System.out.println(builderSuccess.toString());
					} else {
						System.err.println(
								"На данное мероприятие нет столько билетов!\n" + "Выберите другое мероприятие.\n");
					}
					break;

				// Покупка билета на выставку
				case 4:
					System.out.println("Билетов в наличии на Выставку:" + buyExhibition + " шт.\n" + "Стоимость билета:"
							+ priceExhibition + " руб.\n" + "Какое кол-во билетов желаете приобрести?");
					buyChoice = in.nextInt();
					in.nextLine();
					if (buyChoice < buyMC) {
						buyExhibition = buyExhibition - buyChoice;
						System.out.println("У Вас есть абонемент на мероприятие?(Y-Да/N-Нет)");
						discount = in.nextLine();
						if (discount.equalsIgnoreCase("y") == false && discount.equalsIgnoreCase("n") == false) {
							buyExhibition = buyExhibition + buyChoice;
							System.err.println("Ответ неверный! Повторите попытку покупки ещё раз!\n");
							break;
						}
						finalPriceExhibition = makePurchase(buyChoice, finalPriceExhibition, discount, priceExhibition);
						buyStatisticExhibition = buyStatisticExhibition + buyChoice;
						System.out.println("Оплата билета...\n" + "Программа продолжит выполнение через 3 сек.\n");
						// Остановка выполнения на 3 секунды (нужно для разделения оплаты и продолжения
						// работы)
						TimeUnit.SECONDS.sleep(3);
						System.out.println(builderSuccess.toString());
					} else {
						System.err.println(
								"На данное мероприятие нет столько билетов!\n" + "Выберите другое мероприятие.\n");
					}
					break;

				case 5:
					// Сбор статистики
					StringBuilder builderStatistics = new StringBuilder();
					// Билеты в кино
					builderStatistics.append("Вы приобрели билетов на Киносеанс:" + buyStatisticCinema + " шт.\n");
					builderStatistics.append("Общая сумма:" + finalPriceCinema + " руб.\n\n");
					// Билеты в театр
					builderStatistics.append("Вы приобрели билетов на Спектакль:" + buyStatisticTheatre + " шт.\n");
					builderStatistics.append("Общая сумма:" + finalPriceTheatre + " руб.\n\n");
					// Билеты на Мастер-класс
					builderStatistics.append("Вы приобрели билетов на Мастер-класс:" + buyStatisticMC + " шт.\n");
					builderStatistics.append("Общая сумма:" + finalPriceMC + " руб.\n\n");
					// Билеты на выставку
					builderStatistics.append("Вы приобрели билетов на Выставку:" + buyStatisticExhibition + " шт.\n");
					builderStatistics.append("Общая сумма:" + finalPriceExhibition + " руб.\n\n");

					// Вывод статистики
					System.out.println(builderStatistics.toString());
					break;

					// Выход из системы
				case 0:
					System.err.println("Завершение работы...");
					System.exit(0);
				}

				// Проверка на наличие действия
				if (choice < 0 || choice > 5) {
					System.err.println("Выберите цифру от 0 до 5!");
				}
			}
		}

		// Обработка исключения ввода числа
		catch (Exception e) {
			System.err.println("Вводить можно только цифры.\n" + "Перезапустите программу для продолжения.");

		}
	}
}