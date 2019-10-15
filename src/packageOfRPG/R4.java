package packageOfRPG;

public class R4 extends Chara {
	public R4() {
		name = "天弓射手";
		skill_n = "破甲箭";
		skill_s = "箭雨";
		hp = 60;
		maxhp = 60;
		mp = 80;
		maxmp = 80;
		mp_costn = 10;
		mp_costs = 25;
		turn = 2;
		count = 1;
		type = 4;
		target = -1;
		position = -1;
	}

	public void normal(Team enemy) {
		mp = mp - mp_costn;
		int atk = (int) (35 * buff);
		System.out.println(name + "使用" + skill_n);
		if (enemy.members[target].damage(atk)) {
			enemy.rearrange();
		}
		target = -1;
	}

	public void special(Team enemy) {
		mp = mp - mp_costs;
		int atk = (int) (15 * buff);
		System.out.println(name + "使用" + skill_s);
		for (int i = 0; i < enemy.number; i++) {
			enemy.members[i].damage(atk);
		}
		enemy.rearrange();
	}
}
