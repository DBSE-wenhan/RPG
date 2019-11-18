package packageOfRPG;

public class M2 extends Chara {
	public M2() {
		name = "術士";
		skill_n = "蝕魂";
		skill_s = "none";
		hp = 80;
		maxhp = 80;
		mp = 0;
		maxmp = 0;
		mp_costn = 0;
		mp_costs = 0;
		turn = 2;
		count = 1;
		type = 7;
		target = -1;
		position = -1;
	}

	public void special_detial(Team ally) {
	}

	public void normal_detial(Team ally) {
		int atk = (int) (15 * (buff * 0.05));
		int hp_r = 0;
		System.out.println(name + "使用" + skill_n);
		if (position < 4) {
			if (atk > ally.members[0].hp) {
				hp_r = ally.members[0].hp;
			} else {
				hp_r = atk;
			}
			ally.members[0].damage(atk);
			if (position < 3 && ally.number > 1) {
				if (atk > ally.members[1].hp) {
					hp_r = hp_r + ally.members[1].hp;
				} else {
					hp_r = hp_r + atk;
				}
				ally.members[1].damage(atk);
			}
			ally.rearrange();
			heal(hp_r, 0);
		} else {
			System.out.println(name + "什麼都沒打到");
		}

	}

}
