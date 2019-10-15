package packageOfRPG;

public class R3 extends Chara {
	public R3() {
		name = "元素法師";
		skill_n = "火球術";
		skill_s = "暴風雪";
		hp = 50;
		maxhp = 50;
		mp = 100;
		maxmp = 100;
		mp_costn = 15;
		mp_costs = 20;
		turn = 2;
		count = 1;
		type = 3;
		target = -1;
		position = -1;
	}

	public void normal(Team enemy) {
		mp = mp - mp_costn;
		int atk = (int) (30 * buff);
		System.out.println(name + "使用" + skill_n);
		if (position < 4) {
			enemy.members[0].damage(atk);
			if (position < 3 && enemy.number > 1) {
				enemy.members[1].damage(atk);
			}
			enemy.rearrange();
		} else {
			System.out.println(name + "什麼都沒打到");
		}
	}

	public void special(Team enemy) {
		mp = mp - mp_costs;
		int atk = (int) (25 * buff);
		System.out.println(name + "使用" + skill_s);
		if (position < 4) {
			if (enemy.members[0].damage(atk) == false && enemy.members[0].iced == false) {
				enemy.members[0].iced = true;
				//enemy.members[0].count--;
				System.out.println(enemy.members[0].name + "被冰凍了");
			}
			if (position < 3 && enemy.number > 1) {
				if (enemy.members[1].damage(atk) == false && enemy.members[1].iced == false) {
					enemy.members[1].iced = true;
					//enemy.members[1].count--;
					System.out.println(enemy.members[1].name + "被冰凍了");
				}
			}
			enemy.rearrange();
		} else {
			System.out.println(name + "什麼都沒打到");
		}
	}
}
