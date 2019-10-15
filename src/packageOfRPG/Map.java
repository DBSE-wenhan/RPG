package packageOfRPG;

import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Map {
	public String name;
	public int limit, a1, b1, a2, b2;
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
					a1 = i;
					b1 = j;
					map[i][j] = 'X';
				} else if (token.equals("Y")) {
					a2 = i;
					b2 = j;
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
		if (a1 + a >= 0 && a1 + a < 10 && b1 + b >= 0 && b1 + b < 10) {
			if (map[a1 + a][b1 + b] == '0') {
				return '0';
			} else {
				char temp = map[a1 + a][b1 + b];
				map[a1][b1] = '1';
				a1 = a1 + a;
				b1 = b1 + b;
				map[a1][b1] = 'X';
				return temp;
			}
		} else {
			return '0';
		}
	}

}
