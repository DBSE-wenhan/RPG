package packageOfRPG;

public abstract class Melee extends Chara {
	protected int anger;

	//protected abstract void normal_detial(Team team);

	public boolean command_test(int skill_type, Team team) {
		int mp_cost;
		if (skill_type == 1) {
			mp_cost = mp_costn;
		} else {
			mp_cost = mp_costs;
			if (anger != 10) {
				System.out.println("怒氣不足");
				return false;
			}
		}
		if (mp < mp_cost) {
			System.out.println("MP不足");
			return false;
		} else {
			return true;
		}
	}

	public boolean damage(int hurt) {
		hp = hp - hurt;
		if (anger < 10) {
			anger = anger + 1;
		}
		System.out.println(name + "受到" + hurt + "點傷害");
		if(hp<=0) {
			death = true;
			System.out.println(name + "倒下了");
			return true;
		}else {
			System.out.println(name + "怒氣+1");
			return false;
		}
	}

	public boolean normal(Team team) {
		if(command_test(1, team)) {
			normal_detial(team);
			if (anger < 10) {
				anger = anger + 1;
				System.out.println(name + "怒氣+1");
			}
			return true;
		}else {
			return false;
		}
	}

	public void reset_anger() {
		anger = 0;
		System.out.println(name + "怒氣歸零");
	}

}
