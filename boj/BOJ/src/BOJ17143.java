import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BOJ17143 {
	static int[] dx = {-1,1,0,0};
	static int[] dy = {0,0,1,-1};
	static int r,c,m;
	static int[][] map_count;
	static shark[][] map;
	static ArrayList<shark> sharks;
	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	
		String[] str = br.readLine().split(" ");
		r = Integer.parseInt(str[0]);
		c = Integer.parseInt(str[1]);
		m = Integer.parseInt(str[2]);
		
		map = new shark[r][c];
		map_count = new int[r][c];
		sharks = new ArrayList<shark>();
		
		for(int i=0; i<m; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken()) -1;
			int y = Integer.parseInt(st.nextToken()) -1;
			int s = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int z = Integer.parseInt(st.nextToken());
			
			map[x][y] = new shark(x,y,s,d,z);
			map_count[x][y]++;
			sharks.add(map[x][y]);
		}
		for(int i=0; i<c; i++) {
			map = new shark[r][c];
			
			for(int j=0; j<sharks.size(); j++) {
				int x = sharks.get(j).x;
				int y = sharks.get(j).y;
				map[x][y] = sharks.get(j);
			}
			
			simulate(i);
		}
		
		System.out.println(count);
	}
	
	public static void simulate(int index) {
		loop: for(int i=0; i<r; i++) {
			if(map[i][index] != null) {
				count += map[i][index].z;
				for(int j=0; j<sharks.size(); j++) {
					if(sharks.get(j).z == map[i][index].z) {
						map_count[sharks.get(j).x][sharks.get(j).y]--;
						sharks.remove(j);
						break loop;
					}
				}
			}
		}
		move();
	}
	
	public static void move() {
		int origin_x = 0;
		int origin_y = 0;
		int new_x = 0;
		int new_y = 0;
		
		if(sharks.size() != 0 ) {
			for(int q=0; q<sharks.size(); q++) {
				shark s = sharks.get(q);
				origin_x = s.x;
				origin_y = s.y;
				new_x = s.x;
				new_y = s.y;
				
				for(int i=0; i<s.s; i++) {
					new_x = new_x + dx[s.d-1];
					new_y = new_y + dy[s.d-1];
					
					if(new_x < 0 || new_x >= r || new_y < 0 || new_y >=c) {
						int tmp = s.d;
						switch(tmp) {
						case 1:
							s.d = 2;
							break;
						case 2:
							s.d = 1;
							break;
						case 3:
							s.d = 4;
							break;
						case 4:
							s.d = 3;
							break;
						}
						
						new_x = new_x + 2*dx[s.d-1];
						new_y = new_y + 2*dy[s.d-1];
					}
				}
				if(map_count[origin_x][origin_y] != 0) {
					map_count[origin_x][origin_y]--;
				}
				map_count[new_x][new_y]++;
				
				s.x = new_x;
				s.y = new_y;
				
			}
			
			for(int i=0; i<r; i++) {
				for(int j=0; j<c; j++) {
					if(map_count[i][j] >= 2) {
						ArrayList<shark> one = new ArrayList<shark>();
						
						for(int k=0; k<sharks.size(); k++) {
							if(sharks.get(k).x == i && sharks.get(k).y == j) {
								one.add(sharks.get(k));
							}
						}
						
						Collections.sort(one, new Comparator<shark>() {
							
							@Override
							public int compare(shark s1, shark s2) {
								// TODO Auto-generated method stub
								return s2.z - s1.z;
							}
							
						});
						
						map[i][j] = one.get(0);
						
						for(int k=1; k<one.size(); k++) {
							for(int l=0; l<sharks.size(); l++) {
								if(sharks.get(l).z == one.get(k).z) {
									map_count[i][j]--;
									sharks.remove(l);
								}
							}
						}
					}
				}
			}
			
		}
	}
	
}

class shark {
	int x;
	int y;
	int s;
	int d;
	int z;
	
	public shark(int x, int y, int s,int d,int z) {
		this.x = x;
		this.y =y;
		this.s = s;
		this.d = d;
		this.z = z;
	}
}