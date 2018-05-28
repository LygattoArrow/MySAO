
package online.shixun.project;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.jar.Attributes.Name;

public class ATMSystem {

	private static Scanner scanner = new Scanner(System.in);
	private static String[][] accounts = new String[3][3];
	private static String[] menus = { "1：余额查询", "2：存款", "3：取款", "4：转账", "5：修改密码", "0：退出" };
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
				println("系统退出成功!");
				System.exit(0);
				break;

			default:
				println("非法操作");
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
		SimpleDateFormat ss = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");// 12小时制
		int c = 1;
		Lv: for (;;) {
			println("请输入账号：");
			String name = scanner.next();
			println("请输入密码：");
			String pwd = scanner.next();

			if (c == 5) {
				println("密码已经错误5次，请联系银行工作人员！");
				System.exit(0);
			}

			for (; j < accounts.length; j++) {
				for (int m = 0; m < accounts[j].length; m++) {
					if (name.equals(accounts[j][0]) && pwd.equals(accounts[j][1])) {
						println("--------------------------------------------------------");
						println("账号名称：" + name + "登陆成功了，登录时间是：" + ss.format(d));
						println("--------------------------------------------------------");
						break Lv;
					}
				}
			}
			println("账号名称或账号密码错误，请重新输入，您还剩余：" + (5 - c) + "次登录机会");
			c++;
			j = 0;
		}

	}

	public static void welcome() {

		print("###### 欢迎来到实训银行 ######");
		println("");
		print("####请按照功能菜单进行操作 ##");
		println(" ");

	}

	private static void queryBalance() {
		println(accounts[j][2]);
	}

	private static void deposit() {
		for (;;) {
			println("请输入存款金额：");
			int g = scanner.nextInt();
			if (g % 100 != 0) {
				println("存款金额必须是100元的整数倍");
			}
			if (g > 1000 || g < 0) {
				println("您的存款金额超过了额度，请重新存款：");
			}
			if (g % 100 == 0 && g <= 1000&& g>0) {
				int number = Integer.valueOf(accounts[j][2]);
				number += g;
				accounts[j][2] = Integer.toString(number);
				println("存款成功金额为" + accounts[j][2]);

				break;
			}

		}
	}

	private static void withdraw() {
		for (;;) {
			println("请输入取款金额：");
			int g1 = scanner.nextInt();
			if (g1 % 100 != 0) {
				println("取款金额必须是100元的整数倍");
			}
			if (g1 > 1000 || g1 < 0) {
				println("您的取款金额超过了额度，请重新存款：");
			}
			if (g1 < Integer.valueOf(accounts[j][2])) {
				if (g1 % 100 == 0 && g1 <= 1000&& g1>0) {
					int number = Integer.valueOf(accounts[j][2]);
					number -= g1;
					accounts[j][2] = Integer.toString(number);
					println("取款成功，剩余金额为：" + accounts[j][2]);
					break;

				}
			} else {
				println("余额不足，请重新输入：");
			}
		}
	}

	private static void transfer() {
		int x = 0;
		Lv1:
		for(;;) {
			println("请输入对方账户名称：");
			String Ohtername = scanner.next();
				for(;x < accounts.length;x++) {
					for(int f = 0;f < accounts[x].length;f++) {
						if (Ohtername.equals(accounts[x][0])) {
							while(true) {
							println("请输入转款金额：");
							int k = scanner.nextInt();
							if (k >= 1000||k < 0||k > Integer.valueOf(accounts[j][2])) {
								println("转款金额不能大于1000元或者为负或是大于已有金额，请重新输入");
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
				println("无该用户，请重新输入！");	
				x = 0;
		}
		println("转款成功，当前金额为"+accounts[j][2]+",对方账户金额为："+accounts[x][2]);
		println("------------------------------------------------");
	}

	private static void changePassword() {
		boolean info = false;
		scanner.nextLine();
		Lv2: while (true) {
			println("输入原密码：");
			String pwd = scanner.nextLine();
			if (pwd.equals(accounts[j][1])) {
				while (true) {
					println("输入新密码：");
					String pwdnew = scanner.nextLine();
					println("重复一次新密码：");
					String pwdnew1 = scanner.nextLine();
					if (pwdnew.equals(pwdnew1)) {
						accounts[j][1] = pwdnew;
						println("密码修改成功！");
						info = true;
						break Lv2;
					} else {
						println("两次密码输入不一样，重新输入：");
					}
				}
			} else {
				if (info) {
					println("密码不正确，重新输入：");
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
