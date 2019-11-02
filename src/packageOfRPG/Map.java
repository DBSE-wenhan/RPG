package packageOfRPG;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Map {
	public String name;
	public int limit, a2, b2;
	private int now_x, now_y, goal_x, goal_y;
	public char[][] map;

	Map(String M) throws IOException {
		if (M.equals("0")) {
			return;
		}
		map = new char[10][10];
		String s;
		FileReader fr = new FileReader(M + ".txt");
		BufferedReader br = new BufferedReader(fr);
		name = br.readLine();
		limit = br.read() - 48;
		s = br.readLine();//³B²z´«¦æ
		int i = 0, j = 0;
		while (br.ready()) {
			s = br.readLine();
			String[] tokens = s.split(",");
			for (String token : tokens) {
				if (token.equals("0")) {
					map[i][j] = '0';
				} else if (token.equals("1")) {
					map[i][j] = '1';
				} else if (token.equals("A")) {
					map[i][j] = 'A';
				} else if (token.equals("B")) {
					map[i][j] = 'B';
				} else if (token.equals("C")) {
					map[i][j] = 'C';
				} else if (token.equals("D")) {
					map[i][j] = 'D';
				} else if (token.equals("E")) {
					map[i][j] = 'E';
				} else if (token.equals("X")) {
					now_y = i;
					now_x = j;
					map[i][j] = 'X';
				} else if (token.equals("Y")) {
					goal_y = i;
					goal_x = j;
					map[i][j] = 'Y';
				} else {

				}
				j++;
			}
			j = 0;
			i++;
		}
		fr.close();
	}

	public void print() {
		for (char[] tokens : map) {
			for (char token : tokens) {
				System.out.print(token + " ");
			}
			System.out.print("\n");
		}
	}

	public char move(String s) {
		int a, b;
		if (s.equals("U") || s.equals("u")) {
			a = -1;
			b = 0;
		} else if (s.equals("D") || s.equals("d")) {
			a = 1;
			b = 0;
		} else if (s.equals("L") || s.equals("l")) {
			a = 0;
			b = -1;
		} else if (s.equals("R") || s.equals("r")) {
			a = 0;
			b = 1;
		} else {
			return 'f';
		}
		if (now_y + a >= 0 && now_y + a < 10 && now_x + b >= 0 && now_x + b < 10) {
			if (map[now_y + a][now_x + b] == '0') {
				return '0';
			} else {
				char temp = map[now_y + a][now_x + b];
				map[now_y][now_x] = '1';
				now_y = now_y + a;
				now_x = now_x + b;
				map[now_y][now_x] = 'X';
				return temp;
			}
		} else {
			return '0';
		}
	}

}
