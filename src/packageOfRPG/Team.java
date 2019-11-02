package packageOfRPG;

import java.util.Scanner;

public class Team {
	public Chara[] members = new Chara[5];
	public int number;

	public Team(String R) {
		int counter = 0;
		if (R.equals("0")) {
			return;
		}
		String[] tokens = R.split(",");
		for (String token : tokens) {
			if (token.equals("R1")) {
				members[counter] = new R1();
			} else if (token.equals("R2")) {
				members[counter] = new R2();
			} else if (token.equals("R3")) {
				members[counter] = new R3();
			} else if (token.equals("R4")) {
				members[counter] = new R4();
			} else if (token.equals("R5")) {
				members[counter] = new R5();
			} else if (token.equals("M1")) {
				members[counter] = new M1();
			} else if (token.equals("M2")) {
				members[counter] = new M2();
			} else if (token.equals("M3")) {
				members[counter] = new M3();
			}
			members[counter].position = counter;
			members[counter].self_team = this;
			counter++;
		}
		number = counter;
		buffed();
	}

	public void buffed() {
		for (int i = 0; i < number; i++) {
			if (members[i].type == 5 || members[i].type == 8) {
				if (i > 0) {
					members[i - 1].buff = members[i - 1].buff + 0.1;
				}
				if (i < number - 1) {
					members[number - 1].buff = members[number - 1].buff + 0.1;
				}
			}
		}
	}

	public void rearrange() {
		int counter = 0;
		for (int i = 0; i < number; i++) {
			if (members[i].death == false) {
				members[counter] = members[i];
				members[counter].position = counter;
				members[counter].buff = 1;
				counter++;
			}
		}
		number = counter;
		buffed();
	}

	public void ally_action(Team enemy, Scanner scanner) {
		String input;
		for (int i = 0; i < number; i++) {
			if (members[i].count % members[i].turn == 0) {
				//members[i].action();
				while (true) {
					System.out.println("請選擇" + members[i].name + "的行動");
					input = scanner.nextLine();
					//while(!members[i].action()) {}
					input = scanner.nextLine();
					String[] tokens = input.split(" ");
					int temp = 0;
					if (tokens[0].equals("exchange")) {
						if (tokens.length != 2) {
							System.out.println("輸入錯誤");
							continue;
						} else if (tokens[1].equals("1")) {
							temp = 4;
						} else if (tokens[1].equals("2")) {
							temp = 3;
						} else if (tokens[1].equals("3")) {
							temp = 2;
						} else if (tokens[1].equals("4")) {
							temp = 1;
						} else if (tokens[1].equals("5")) {
							temp = 0;
						} else {
							System.out.println("輸入錯誤");
							continue;
						}
						members[i].exchange(temp, this);
					} else if (tokens[0].equals("rest")) {
						members[i].rest();
					} else if (tokens[0].equals("normal")) {
						if (members[i].command_test(1, enemy) == false) {
							continue;
						} else if (members[i].type == 4 || members[i].type == 5) {
							if (tokens.length != 2) {
								System.out.println("輸入錯誤");
								continue;
							} else if (tokens[1].equals("1") || tokens[1].equals("10")) {
								temp = 4;
							} else if (tokens[1].equals("2") || tokens[1].equals("9")) {
								temp = 3;
							} else if (tokens[1].equals("3") || tokens[1].equals("8")) {
								temp = 2;
							} else if (tokens[1].equals("4") || tokens[1].equals("7")) {
								temp = 1;
							} else if (tokens[1].equals("5") || tokens[1].equals("6")) {
								temp = 0;
							}
							members[i].target = temp;
							if (members[i].type == 4) {
								members[i].normal(enemy);
							} else {
								members[i].normal(this);
							}
						} else {
							members[i].normal(enemy);
						}
					} else if (tokens[0].equals("special")) {
						if (members[i].command_test(2, enemy) == false) {
							continue;
						}
					}
				}
			}
		}
	}

	public void enemy_action() {

	}
}
