package packageOfRPG;

public class R5 extends Chara {
	public R5() {
		name = "牧師";
		skill_n = "治療";
		skill_s = "全體治療";
		hp = 60;
		maxhp = 60;
		mp = 100;
		maxmp = 100;
		mp_costn = 10;
		mp_costs = 30;
		turn = 3;
		count = 1;
		type = 5;
		target = -1;
		position = -1;
	}

	public void normal(Team enemy) {
		mp = mp - mp_costn;
		System.out.println(name + "對" + self_team.members[target].name + "使用" + skill_n);
		self_team.members[target].heal(20, 15);
		target = -1;
	}

	public void special(Team enemy) {
		mp = mp - mp_costs;
		System.out.println(name + "使用" + skill_s);
		for (int i = 0; i < self_team.number; i++) {
			if (i != position) {
				self_team.members[i].heal((int) (self_team.members[i].maxhp * 0.5), 0);
			}
		}
	}
}
