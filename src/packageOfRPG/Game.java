package packageOfRPG;

import java.util.Scanner;
import java.io.IOException;

public class Game {
	
	private Map M;
	private Team ally;
	private String input;
	private Scanner scanner = new Scanner(System.in);

	public void play() throws IOException {
		select_mission();
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
			if(battle(ally, enemy)) {
				M.print();
				continue;
			}
			else {
				System.out.println("GAME OVER");
				return;
			}
		}
		System.out.println(M.name + ", over");
		scanner.close();
	}

	public void select_mission() throws IOException {
		while (true) {
			System.out.println("輸入任務與編隊");
			input = scanner.nextLine();
			if (input.equals("exit")) {
				return;
			} else {
				String[] tokens = input.split(" ");
				if (tokens.length != 3 || !tokens[0].equals("select")) {
					System.out.println("輸入錯誤");
					continue;
				} else {
					M = new Map(tokens[1]);
					ally = new Team(tokens[2]);
					if (M.limit == ally.number) {
						break;
					} else {
						System.out.println("輸入錯誤");
						continue;
					}
				}
			}
		}
	}

	public boolean battle(Team ally, Team enemy) throws IOException {
		while (true) {
			if (enemy.number == 0) {
				return true;
			} else if (ally.number == 0) {
				return false;
			} else {
				show(ally, enemy);
				ally.ally_action(enemy, scanner);
				enemy.enemy_action(ally);
			}
		}
	}

	public void show(Team ally, Team enemy) {
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
