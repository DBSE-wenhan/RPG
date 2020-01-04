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

	public void special_detial(Team ally) {
	}

	public void normal_detial(Team ally) {
		boolean success = true;
		System.out.println(name + "使用" + skill_n);
		if (self_team.number >= 5) {
			success = false;
		} else {
			for (int i = 0; i < self_team.number; i++) {
				if (self_team.members[i].type == 6) {
					if (((M1) (self_team.members[i])).mother == this) {
						success = false;
						break;
					}
				}
			}
		}
		if (success) {
			self_team.members[self_team.number] = new M1();
			self_team.members[self_team.number].count = 0;
			((M1) (self_team.members[self_team.number])).mother = this;
			self_team.number++;
		} else {
			System.out.println(skill_n + "失敗了");
		}
	}
}
