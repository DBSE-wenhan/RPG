package packageOfRPG;

import java.util.Scanner;
import java.io.IOException;

public class Game {

	public static void main(String[] args) throws IOException {
		Map M;
		Team ally;
		Scanner scanner = new Scanner(System.in);
		String input;
		while (true) {
			System.out.println("輸入任務與編隊");
			input = scanner.nextLine();
			// scanner.close();
			if (input.equals("exit")) {
				// scanner.close();
				return;
			} else {
				String[] tokens = input.split(" ");
				if (tokens.length != 3 || !tokens[0].equals("select")) {
					System.out.println("輸入錯誤");
					continue;
				} else {
					// M = null;
					// ally = null;
					M = new Map(tokens[1]);
					ally = new Team(tokens[2]);
					if (M.limit == ally.number) {
						// scanner.close();
						break;
					} else {
						System.out.println("輸入錯誤");
						continue;
					}
				}
			}
		}
		M.print();
		while (true) {
			input = scanner.nextLine();
			char c = M.move(input);
			if (c == '0' || c == '1') {
				M.print();
				continue;
			} else if (c == 'A') {
				input = "M1";
				//////////////////
			} else if (c == 'B') {
				input = "M1,M1,M2";
			} else if (c == 'C') {
				input = "M1,M1,M2,M3";
			} else if (c == 'D') {
				input = "M1,M1,M3,M3";
			} else if (c == 'E') {
				input = "M1,M1,M2,M2,M3";
			} else if (c == 'Y') {
				break;
			} else if (c == 'f') {
				System.out.println("輸入錯誤");
				continue;
			}
			Team enemy = new Team(input);
			show(ally, enemy);
			if(battle(ally, enemy)) {
				M.print();
				continue;
			}
			else {
				System.out.println("GAME OVER");
				return;
			}
		}
		System.out.println(M.limit + "\n" + M.name);
		System.out.println("over");
		scanner.close();
	}

	public static boolean select_mission(Map M, Team T) throws IOException {
		while (true) {
			System.out.println("輸入任務與編隊");
			Scanner scanner = new Scanner(System.in);
			String input = scanner.nextLine();
			// scanner.close();
			if (input.equals("exit")) {
				scanner.close();
				return true;
			} else {
				String[] tokens = input.split(" ");
				if (tokens.length != 3 || !tokens[0].equals("select")) {
					System.out.println(tokens.length + tokens[0] + "輸入錯誤1");
					continue;
				} else {
					M = null;
					T = null;
					M = new Map(tokens[1]);
					T = new Team(tokens[2]);
					if (M.limit == T.number) {
						scanner.close();
						return false;
					} else {
						System.out.println("輸入錯誤2");
						continue;
					}
				}
			}
		}
	}

	public static boolean battle(Team ally, Team enemy) throws IOException {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			if (enemy.number == 0) {
				//scanner.close();
				return true;
			} else if (ally.number == 0) {
				//scanner.close();
				return false;
			} else {
				//Scanner scanner = new Scanner(System.in);
				String input;
				show(ally, enemy);
				for (int i = 0; i < ally.number; i++) {
					if (ally.members[i].count % ally.members[i].turn == 0) {
						while (true) {
							System.out.println("請選擇"+ally.members[i].name+"的行動");
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
								ally.members[i].exchange(temp, ally);
							} else if (tokens[0].equals("rest")) {
								ally.members[i].rest();
							} else if (tokens[0].equals("normal")) {
								if (ally.members[i].command_test(1, enemy) == false) {
									continue;
								} else if (ally.members[i].type == 4 || ally.members[i].type == 5) {
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
									ally.members[i].target = temp;
									if (ally.members[i].type == 4) {
										ally.members[i].normal(enemy);
									} else {
										ally.members[i].normal(ally);
									}
								} else {
									ally.members[i].normal(enemy);
								}
							} else if (tokens[0].equals("special")) {
								if (ally.members[i].command_test(2, enemy) == false) {
									continue;
								} else if (ally.members[i].type == 2) {
									if (tokens.length != 2) {
										System.out.println("輸入錯誤");
										continue;
									} else if (tokens[1].equals("10")) {
										temp = 4;
									} else if (tokens[1].equals("9")) {
										temp = 3;
									} else if (tokens[1].equals("8")) {
										temp = 2;
									} else if (tokens[1].equals("7")) {
										temp = 1;
									} else if (tokens[1].equals("6")) {
										temp = 0;
									}
									ally.members[i].target = temp;
									if (((R2) (ally.members[i])).command_test2(2, enemy) == false) {
										continue;
									} else {
										ally.members[i].special(enemy);
									}
								} else if (ally.members[i].type == 5) {
									ally.members[i].special(ally);
								} else {
									ally.members[i].special(enemy);
								}
							} else {
								System.out.println("輸入錯誤");
								continue;
							}
							break;
						}
					}
					ally.members[i].count++;
				}

				//scanner.close();
				for (int i = 0; i < enemy.number; i++) {
					if(enemy.members[i].iced == true) {
						System.out.println(enemy.members[i].name + "被冰凍了無法行動");
						enemy.members[i].iced = false;
						continue;
					}
					else if (enemy.members[i].count % enemy.members[i].turn == 0) {
						enemy.members[i].normal(ally);
					}
					enemy.members[i].count++;
				}
			}
		}
	}

	public static void show(Team ally, Team enemy) {
		for (int i = 4; i >= 0; i--) {
			if (ally.members[i] == null) {
				System.out.print("\t");
			} else if (ally.members[i].death) {
				System.out.print("\t");
			} else {
				System.out.print(ally.members[i].name + "\t");
			}
		}
		for (int i = 0; i < enemy.number; i++) {
			if (enemy.members[i] == null) {
				System.out.print("\t");
			} else if (enemy.members[i].death) {
				System.out.print("\t");
			} else {
				System.out.print(enemy.members[i].name + "\t");
			}
		}
		System.out.println("\n1\t2\t3\t4\t5\t6\t7\t8\t9\t10");
	}

}
