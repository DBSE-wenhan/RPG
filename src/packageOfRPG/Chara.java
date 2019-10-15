package packageOfRPG;

public abstract class Chara {
	public int hp, maxhp, mp, maxmp, mp_costn, mp_costs;
	public int turn, count, type, target, position;
	public double buff = 1;
	public String name, skill_n, skill_s;
	public boolean iced = false, death = false;

	public void exchange(int num, Team team) {
		team.members[num].position = this.position;
		this.position = num;
		Chara temp = team.members[num];
		team.members[num] = this;
		team.members[team.members[num].position] = temp;
		System.out.println(name + "和" + team.members[num].name + "交換位置");
		for(int i=0;i<3;i++) {
			System.out.println(team.members[i].name);
		}
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

	public abstract void normal(Team team);

	public abstract void special(Team team);

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
				System.out.println(name + "恢復" + (maxmp - mp - mp_r) + "點mp");
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
}
