package packageOfRPG;

public class R1 extends Melee {
	public R1() {
		name = "狂劍士";
		skill_n = "斬擊";
		skill_s = "狂暴打擊";
		hp = 100;
		maxhp = 100;
		mp = 40;
		maxmp = 40;
		mp_costn = 5;
		mp_costs = 25;
		anger = 0;
		turn = 1;
		count = 1;
		type = 1;
		target = -1;
		position = -1;
	}

	public void normal_detial(Team enemy) {
		mp = mp - mp_costn;
		int atk = (int) (15 * (buff + anger * 0.05));
		System.out.println(name + "對" + enemy.members[0].name + "使用" + skill_n);
		if (enemy.members[0].damage(atk)) {
			enemy.rearrange();
		}
	}

	public void special_detial(Team enemy) {
		mp = mp - mp_costs;
		int atk = (int) (25 * (buff + anger * 0.05));
		System.out.println(name + "使用" + skill_s);
		if (enemy.members[0].damage(atk)) {
			enemy.rearrange();
		}
		if (enemy.number > 0) {
			if (enemy.members[0].damage(atk)) {
				enemy.rearrange();
			}
		}
		reset_anger();
	}
}
