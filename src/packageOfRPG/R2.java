package packageOfRPG;

public class R2 extends Melee {
	public R2() {
		name = "刺客";
		skill_n = "背刺";
		skill_s = "斬殺";
		hp = 80;
		maxhp = 80;
		mp = 50;
		maxmp = 50;
		mp_costn = 10;
		mp_costs = 25;
		anger = 0;
		turn = 1;
		count = 1;
		type = 2;
		target = -1;
		position = -1;
	}

	public boolean command_test(int skill_type, Team enemy) {
		int mp_cost;
		if (skill_type == 1) {
			mp_cost = mp_costn;
		} else {
			mp_cost = mp_costs;
			if (anger != 10) {
				System.out.println("怒氣不足");
				target = -1;
				return false;
			}
		}
		if (mp < mp_cost) {
			System.out.println("MP不足");
			target = -1;
			return false;
		} else if(skill_type == 2) {
			if (enemy.members[target].hp >= enemy.members[target].maxhp * 0.4 && skill_type == 2) {
				System.out.println("目標血量超過40%");
				target = -1;
				return false;
			}
		}
		return true;
	}

	public void normal_detial(Team enemy) {
		mp = mp - mp_costn;
		int atk = (int) (20 * (buff + anger * 0.05));
		System.out.println(name + "對" + enemy.members[enemy.number - 1].name + "使用" + skill_n);
		if (enemy.members[enemy.number - 1].damage(atk)) {
			enemy.rearrange();
		}
	}

	public void special_detial(Team enemy) {
		mp = mp - mp_costs;
		System.out.println(name + "對" + enemy.members[target].name + "使用" + skill_s);
		if (enemy.members[target].damage(enemy.members[target].hp)) {
			enemy.rearrange();
		}
		target = -1;
		reset_anger();
	}
}
