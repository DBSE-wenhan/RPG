package packageOfRPG;

public class M3 extends Chara {
	public M3() {
		name = "女巫";
		skill_n = "穢土轉生";
		skill_s = "none";
		hp = 80;
		maxhp = 80;
		mp = 0;
		maxmp = 0;
		mp_costn = 0;
		mp_costs = 0;
		turn = 3;
		count = 1;
		type = 8;
		target = -1;
		position = -1;
	}

	public void special(Team enemy) {
	}

	public void normal(Team enemy) {
		boolean success = true;
		System.out.println(name + "使用" + skill_n);
		if (enemy.number >= 5) {
			success = false;
		} else {
			for (int i = 0; i < enemy.number; i++) {
				if (enemy.members[i].type == 6) {
					if (((M1) (enemy.members[i])).mother == this) {
						success = false;
						break;
					}
				}
			}
		}
		if (success) {
			enemy.members[enemy.number] = new M1();
			enemy.members[enemy.number].count = 0;
			((M1) (enemy.members[enemy.number])).mother = this;
			enemy.number++;
		} else {
			System.out.println(skill_n + "失敗了");
		}
	}
}
