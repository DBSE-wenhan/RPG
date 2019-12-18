package packageOfRPG;

import java.util.Scanner;

public abstract class Chara {
	public int hp, maxhp, mp, maxmp, mp_costn, mp_costs;
	public int turn, count, type, target, position;
	public double buff = 1;
	public String name, skill_n, skill_s;
	public boolean iced = false, death = false;
	public Team self_team;

	public void exchange(int num) {
		self_team.members[num].position = this.position;
		this.position = num;
		Chara temp = self_team.members[num];
		self_team.members[num] = this;
		self_team.members[self_team.members[num].position] = temp;
		System.out.println(name + "和" + self_team.members[num].name + "交換位置");
	}

	public boolean command_test(int skill_type, Team team) {
		int mp_cost;
		if (skill_type == 1) {
			mp_cost = mp_costn;
		} else {
			mp_cost = mp_costs;
		}
		if (mp < mp_cost) {
			System.out.println("MP不足");
			return false;
		} else {
			return true;
		}
	}

	public boolean normal(Team team) {
		if(command_test(1, team)) {
			normal_detial(team);
			return true;
		}else {
			return false;
		}
	}
	
	public abstract void normal_detial(Team team);

	public boolean special(Team team) {
		if(command_test(2, team)) {
			special_detial(team);
			return true;
		}else {
			return false;
		}
	}
	
	public abstract void special_detial(Team team);

	public void heal(int hp_r, int mp_r) {
		if (hp_r > 0) {
			hp = hp + hp_r;
			if (hp > maxhp) {
				System.out.println(name + "恢復" + (hp_r - (hp - maxhp)) + "點hp");
				hp = maxhp;
			} else {
				System.out.println(name + "恢復" + hp_r + "點hp");
			}
		}
		if (mp_r > 0) {
			mp = mp + mp_r;
			if (mp > maxmp) {
				System.out.println(name + "恢復" + (mp_r - (mp - maxmp)) + "點mp");
				mp = maxmp;
			} else {
				System.out.println(name + "恢復" + mp_r + "點mp");
			}
		}
	}

	public void rest() {
		System.out.println(name + "休息一回合");
		heal(10, 10);
	}

	public boolean damage(int hurt) {
		hp = hp - hurt;
		System.out.println(name + "受到" + hurt + "點傷害");
		if (hp <= 0) {
			death = true;
			System.out.println(name + "倒下了");
			return true;
		} else {
			return false;
		}
	}

	public void user_action(Team enemy, Scanner scanner) {
		String input;
		while (true) {	
			System.out.println("請選擇" + this.name + "的行動");
			input = scanner.nextLine();
			String[] tokens = input.split(" ");
			int action_target = -1;
			///input 長度檢查
			if(tokens.length == 2) {
				if (tokens[1].equals("1") || tokens[1].equals("10")) {
					action_target = 4;
				} else if (tokens[1].equals("2") || tokens[1].equals("9")) {
					action_target = 3;
				} else if (tokens[1].equals("3") || tokens[1].equals("8")) {
					action_target = 2;
				} else if (tokens[1].equals("4") || tokens[1].equals("7")) {
					action_target = 1;
				} else if (tokens[1].equals("5") || tokens[1].equals("6")) {
					action_target = 0;
				}
				this.target = action_target;
			}else if(tokens.length > 2) {
				System.out.println("輸入錯誤");
				continue;
			}
			///input 種類檢查
			if (tokens[0].equals("exchange")) {
				this.exchange(action_target);
			} else if (tokens[0].equals("rest")) {
				this.rest();
			} else if (tokens[0].equals("normal")) {
				if(!this.normal(enemy)) {
					continue;
				}
			} else if (tokens[0].equals("special")) {
				if(!this.special(enemy)) {
					continue;
				}
			} else {
				System.out.println("輸入錯誤");
				continue;
			}
			break;
		}
		this.count++;
	}

	public void input_transfer() {
		
	}
	
	//public void position_transfer
}
