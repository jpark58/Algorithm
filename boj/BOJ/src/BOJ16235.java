import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

class Tree implements Comparable<Tree> {
	int x;
	int y;
	int age;

	Tree(int x, int y, int age) {
		this.x = x;
		this.y = y;
		this.age = age;
	}

	// 나이 오름차순으로 정렬
	@Override
	public int compareTo(Tree t) {
		return this.age - t.age;
	}
}

public class BOJ16235 {
	static int N, M, K;
	static int[][] A;
	static int[][] map;
	static ArrayList<Tree> trees = new ArrayList<>();
	static ArrayList<Tree> liveTrees;
	static ArrayList<Tree> deadTrees;
	static int[] dx = { -1, -1, -1, 0, 0, 1, 1, 1 };
	static int[] dy = { -1, 0, 1, -1, 1, -1, 0, 1 };

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());

		map = new int[N][N];
		A = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = 5;
				A[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine());
			trees.add(new Tree(Integer.parseInt(st.nextToken()) - 1, Integer.parseInt(st.nextToken()) - 1,
					Integer.parseInt(st.nextToken())));

		}

		while (K > 0) {
			// 구분 초기화
			liveTrees = new ArrayList<>();
			deadTrees = new ArrayList<>();
			// 정렬
			Collections.sort(trees);
			spring();
			summer();
			fall();
			winter();
			K--;
		}
		System.out.println(trees.size());
	}

	// 봄
	static void spring() {
		for (int i = 0; i < trees.size(); i++) {
			Tree t = trees.get(i);
			if (t.age > map[t.x][t.y]) {
				deadTrees.add(t);
			} else {
				map[t.x][t.y] -= t.age;
				t.age += 1;
				liveTrees.add(t);
			}
		}
		// 나무 리스트 리셋 후 살아있는 나무로 초기화
		trees.clear();
		trees.addAll(liveTrees);
	}

	// 여름
	static void summer() {
		for (int i = 0; i < deadTrees.size(); i++) {
			Tree t = deadTrees.get(i);
			map[t.x][t.y] += t.age / 2;
		}
	}

	// 가을
	static void fall() {
		for (int i = 0; i < trees.size(); i++) {
			Tree t = trees.get(i);
			if (t.age % 5 == 0) {
				for (int j = 0; j < 8; j++) {
					int px = t.x + dx[j];
					int py = t.y + dy[j];
					if (0 <= px && px < N && 0 <= py && py < N) {
						trees.add(new Tree(px, py, 1));
					}
				}
			}
		}
	}

	// 겨울
	static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				map[i][j] += A[i][j];
			}
		}
	}

	static void print() {
		for (int i = 0; i < trees.size(); i++) {
			System.out.println(trees.get(i).x + " " + trees.get(i).y + " " + trees.get(i).age);
		}
	}

	static void printMap() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print(map[i][j] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}
}