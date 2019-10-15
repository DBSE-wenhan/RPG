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

	public void normal(Team ally) {
		mp = mp - mp_costn;
		System.out.println(name + "對" + ally.members[target].name + "使用" + skill_n);
		ally.members[target].heal(20, 15);
		target = -1;
	}

	public void special(Team ally) {
		mp = mp - mp_costs;
		System.out.println(name + "使用" + skill_s);
		for (int i = 0; i < ally.number; i++) {
			if (i != position) {
				ally.members[i].heal((int) (ally.members[i].maxhp * 0.5), 0);
			}
		}
	}
}
