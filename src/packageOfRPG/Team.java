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
		for (int i = 0; i < number; i++) {
			if (members[i].count % members[i].turn == 0) {
				members[i].user_action(enemy, scanner);
				if(enemy.number == 0) {
					return;
				}
			}
		}
	}

	public void enemy_action(Team ally) {
		for (int i = 0; i < number; i++) {
			if(members[i].iced == true) {
				System.out.println(members[i].name + "被冰凍了無法行動");
				members[i].iced = false;
				continue;
			}
			else if (members[i].count % members[i].turn == 0) {
				members[i].normal(ally, 1);
			}
			members[i].count++;
		}
	}
}
