package packageOfRPG;

import java.io.IOException;
import java.util.Scanner;

public class Game {
	
	private Map M;
	private Team ally;
	private String input;
	private Scanner scanner = new Scanner(System.in);

	public void play() {
		select_mission();
		M.print();
		while (true) {
			input = scanner.nextLine();
			String next_step = M.move(input);
			if (next_step.equals("0") || next_step.equals("1")) {
				///撞牆or繼續走
				M.print();
				continue;
			} else if (next_step.equals("Y")) {
				///終點
				break;
			} else if (next_step.equals("f")) {
				///錯誤的輸入
				System.out.println("輸入錯誤");
				continue;
			} else {
				input = next_step;
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

	public void select_mission() {
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
					try {
						M = new Map(tokens[1]);
					}catch(IOException e) {
						System.out.println("找不到該任務");
						continue;
					}
					
					ally = new Team(tokens[2]);
					if (M.limit == ally.number) {
						break;
					} else {
						System.out.println("輸入人數錯誤");
						continue;
					}
				}
			}
		}
	}

	public boolean battle(Team ally, Team enemy) {
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
