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
				///����or�~��
				M.print();
				continue;
			} else if (next_step.equals("Y")) {
				///���I
				break;
			} else if (next_step.equals("f")) {
				///���~����J
				System.out.println("��J���~");
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
			System.out.println("��J���ȻP�s��");
			input = scanner.nextLine();
			if (input.equals("exit")) {
				return;
			} else {
				String[] tokens = input.split(" ");
				if (tokens.length != 3 || !tokens[0].equals("select")) {
					System.out.println("��J���~");
					continue;
				} else {
					try {
						M = new Map(tokens[1]);
					}catch(IOException e) {
						System.out.println("�䤣��ӥ���");
						continue;
					}
					
					ally = new Team(tokens[2]);
					if (M.limit == ally.number) {
						break;
					} else {
						System.out.println("��J�H�ƿ��~");
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
