
package online.shixun.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class ATMSystem {

	private static Scanner scanner = new Scanner(System.in);
	private static String[][] accounts = new String[3][3];
	private static String[] menus = { "1������ѯ", "2�����", "3��ȡ��", "4��ת��", "5���޸�����", "0���˳�" };
	private static int j = 0;

	public static void main(String[] args) {
		initAccounts();
		welcome();
		login();

		while (true) {
			println("**********");
			for (String M : menus) {
				print(M);
			}
			println("");
			println("**********");

			int menuID = scanner.nextInt();
			switch (menuID) {
			case 1:
				queryBalance();
				break;
			case 2:
				deposit();
				break;
			case 3:
				withdraw();
				break;
			case 4:
				transfer();
				break;
			case 5:
				changePassword();
				break;
			case 0:
				println("ϵͳ�˳��ɹ�!");
				System.exit(0);
				break;

			default:
				println("�Ƿ�����");
				break;
			}
		}
	}

	public static void initAccounts() {

		accounts[0] = new String[] { "wanli", "123", "500" };
		accounts[1] = new String[] { "xiangzi", "321", "200" };
		accounts[2] = new String[] { "lingda", "456", "300" };

	}

	public static void login() {
		Date d = new Date();
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 12Сʱ��
		int c = 1;
		Lv: for (;;) {
			println("�������˺ţ�");
			String name = scanner.next();
			println("���������룺");
			String pwd = scanner.next();

			if (c == 5) {
				println("�����Ѿ�����5�Σ�����ϵ���й�����Ա��");
				System.exit(0);
			}

			for (; j < accounts.length; j++) {
				for (int m = 0; m < accounts[j].length; m++) {
					if (name.equals(accounts[j][0]) && pwd.equals(accounts[j][1])) {
						println("--------------------------------------------------------");
						println("�˺����ƣ�" + name + "��½�ɹ��ˣ���¼ʱ���ǣ�" + ss.format(d));
						println("--------------------------------------------------------");
						break Lv;
					}
				}
			}
			println("�˺����ƻ��˺�����������������룬����ʣ�ࣺ" + (5 - c) + "�ε�¼����");
			c++;
			j = 0;
		}

	}

	public static void welcome() {

		print("###### ��ӭ����ʵѵ���� ######");
		println("");
		print("####�밴�չ��ܲ˵����в��� ##");
		println(" ");

	}

	private static void queryBalance() {
		println(accounts[j][2]);
	}

	private static void deposit() {
		for (;;) {
			println("���������");
			int g = scanner.nextInt();
			if (g % 100 != 0) {
				println("����������100Ԫ��������");
			}
			if (g > 1000 || g < 0) {
				println("���Ĵ������˶�ȣ������´�");
			}
			if (g % 100 == 0 && g <= 1000&& g>0) {
				int number = Integer.valueOf(accounts[j][2]);
				number += g;
				accounts[j][2] = Integer.toString(number);
				println("���ɹ����Ϊ" + accounts[j][2]);

				break;
			}

		}
	}

	private static void withdraw() {
		for (;;) {
			println("������ȡ���");
			int g1 = scanner.nextInt();
			if (g1 % 100 != 0) {
				println("ȡ���������100Ԫ��������");
			}
			if (g1 > 1000 || g1 < 0) {
				println("����ȡ������˶�ȣ������´�");
			}
			if (g1 < Integer.valueOf(accounts[j][2])) {
				if (g1 % 100 == 0 && g1 <= 1000&& g1>0) {
					int number = Integer.valueOf(accounts[j][2]);
					number -= g1;
					accounts[j][2] = Integer.toString(number);
					println("ȡ��ɹ���ʣ����Ϊ��" + accounts[j][2]);
					break;

				}
			} else {
				println("���㣬���������룺");
			}
		}
	}

	private static void transfer() {
		int x = 0;
		Lv1:
		for(;;) {
			println("������Է��˻����ƣ�");
			String Ohtername = scanner.next();
				for(;x < accounts.length;x++) {
					for(int f = 0;f < accounts[x].length;f++) {
						if (Ohtername.equals(accounts[x][0])) {
							while(true) {
							println("������ת���");
							int k = scanner.nextInt();
							if (k >= 1000||k < 0||k > Integer.valueOf(accounts[j][2])) {
								println("ת����ܴ���1000Ԫ����Ϊ�����Ǵ������н�����������");
							}else {
							int number = Integer.valueOf(accounts[j][2]);
							number -= k;
							accounts[j][2] = Integer.toString(number);
							int number1 = Integer.valueOf(accounts[x][2]);
							number1 += k; 
							accounts[x][2] = Integer.toString(number1);
							break Lv1;
							}
						}
					} 
							
						
				}
						
		}
				println("�޸��û������������룡");	
				x = 0;
		}
		println("ת��ɹ�����ǰ���Ϊ"+accounts[j][2]+",�Է��˻����Ϊ��"+accounts[x][2]);
		println("------------------------------------------------");
	}

	private static void changePassword() {
		boolean info = false;
		scanner.nextLine();
		Lv2: while (true) {
			println("����ԭ���룺");
			String pwd = scanner.nextLine();
			if (pwd.equals(accounts[j][1])) {
				while (true) {
					println("���������룺");
					String pwdnew = scanner.nextLine();
					println("�ظ�һ�������룺");
					String pwdnew1 = scanner.nextLine();
					if (pwdnew.equals(pwdnew1)) {
						accounts[j][1] = pwdnew;
						println("�����޸ĳɹ���");
						info = true;
						break Lv2;
					} else {
						println("�����������벻һ�����������룺");
					}
				}
			} else {
				if (info) {
					println("���벻��ȷ���������룺");
				}

			}
		}

	}

	public static void println(String message) {
		System.out.println(message);
	}

	public static void print(String message) {
		System.out.print(message);

	}
}
