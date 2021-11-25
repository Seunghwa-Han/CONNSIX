import java.awt.Point;
import java.util.ArrayList;

public class NormAttack {
	
	static int put_count=0;
	static int num_enemy=0;
	static int num_mine=0;
	static int num_empty=0;
	static int num_brick=0;
	
	public static void putStone(int[][] result, ArrayList<Point> vcCom, ArrayList<Integer> coor) {
		int[][] ar = new int[19][19];
		for(int i=0; i<19; i++) {
			for(int j=0; j<19; j++)
				ar[i][j] = result[i][j];
		}
		
		norm_attack1(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		norm_attack2(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		norm_attack3(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
		norm_attack4(ar, vcCom, result, coor);
		if(put_count==2) {
			put_count=0;
			return;
		}
	}
	
	public static void norm_attack1(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 가로 방향.
		if (put_count == 2)
			return;

		int i = 0;
		int j = 0;
		int k = 0;

		Loop1: for (i = 0; i < 19; i++) {
			Loop2: for (j = 0; j < 13; j++) {
				if (ar[i][j] == 1 || ar[i][j] == 3) { // 왼쪽이 차 있을 때.
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, i, j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						int cp = put_count;
						for (k = 0; k < 7; k++) {
							if (put_count > cp)
								break Loop2;
							fill_2x2(ar, i, j + k, vcCom, result, coor);
							if (put_count == 2)
								break Loop1;
						}
					}
				} else if (ar[i][j + 6] == 1 || ar[i][j + 6] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, i, j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						for (k = 0; k < 7; k++) {
							if (ar[i][j + k] == 2) {
								fill_2x2(ar, i, j + k - 1, vcCom, result, coor);
								if (put_count == 2)
									break Loop1;
								break Loop2 ;
							}
						}
					}
				}
			}
		}
	}

	public static void norm_attack2(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 세로 방향.
		if (put_count == 2)
			return;

		int i = 0;
		int j = 0;
		int k = 0;

		Loop1: for (i = 0; i < 19; i++) {
			Loop2: for (j = 0; j < 13; j++) {
				if (ar[j][i] == 1 || ar[j][i] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, j + k, i);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						int cp = put_count; // 0 or 1
						for (k = 0; k < 7; k++) {
							if (put_count > cp)
								break Loop2;
							fill_2x2(ar, j + k, i, vcCom, result, coor);
							if (put_count == 2)
								break Loop1;
						}
					}
				} else if (ar[j + 6][i] == 1 || ar[j + 6][i] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, j + k, i);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						for (k = 0; k < 7; k++) {
							if (ar[j + k][i] == 2) {
								fill_2x2(ar, j + k - 1, i, vcCom, result, coor);
								if (put_count == 2)
									break Loop1;
								break Loop2;
							}
						}
					}
				}
			}
		}
	}

	public static void norm_attack3(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 세로 방향.
		if (put_count == 2)
			return;

		int i = 0;
		int j = 0;
		int k = 0;

		Loop1: for (i = 6; i < 19; i++) {
			Loop2: for (j = 0; j < i - 5; j++) {
				if (ar[i - j][j] == 1 || ar[i - j][j] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, i - j - k, j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						int cp = put_count; // 0 or 1
						for (k = 0; k < 7; k++) {
							if (put_count > cp)
								break Loop2;
							fill_2x2(ar, i - j - k, j + k, vcCom, result, coor);
							if (put_count == 2)
								break Loop1;
						}
					}
				} else if(ar[i - j - 6][j + 6] == 1 || ar[i - j - 6][j + 6] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, i - j - k, j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						for (k = 0; k < 7; k++) {
							if (ar[i - j - k][j + k] == 2) {
								fill_2x2(ar, i - j - k + 1, j + k - 1, vcCom, result, coor);
								if (put_count == 2)
									break Loop1;
								break Loop2;
							}
						}
					}
				}
			}
		}
		Loop1: for (i = 1; i < 13; i++) {
			Loop2: for (j = 0; j < 13 - i; j++) {
				if (ar[18 - i][i + j] == 1 || ar[18 - i][i + j] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, 18 - i - k, i + j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						int cp = put_count; // 0 or 1
						for (k = 0; k < 7; k++) {
							if (put_count > cp)
								break Loop2;
							fill_2x2(ar, 18 - i - k, i + j + k, vcCom, result, coor);
							if (put_count == 2)
								break Loop1;
						}
					}
				} else if(ar[18 - i - 6][i + j + 6] == 1 || ar[18 - i - 6][i + j + 6] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, 18 - i - k, i + j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						for (k = 0; k < 7; k++) {
							if (ar[18 - i - k][i + j + k] == 2) {
								fill_2x2(ar, 18 - i - k + 1, i + j + k - 1, vcCom, result, coor);
								if (put_count == 2)
									break Loop1;
								break Loop2;
							}
						}
					}
				}
			}
		}
	}

	public static void norm_attack4(int[][] ar, ArrayList<Point> vcCom, int[][] result, ArrayList<Integer> coor) { // 세로 방향.
		if (put_count == 2)
			return;

		int i = 0;
		int j = 0;
		int k = 0;

		Loop1: for (i = 2; i < 15; i++) {
			Loop2: for (j = 0; j < i - 1; j++) {
				if (ar[j][14 - i + j] == 1 || ar[j][14 - i + j] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, j + k, 14 - i + j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						int cp = put_count; // 0 or 1
						for (k = 0; k < 7; k++) {
							if (put_count > cp)
								break Loop2;
							fill_2x2(ar, j + k, 14 - i + j + k, vcCom, result, coor);
							if (put_count == 2)
								break Loop1;
						}
					}
				} else if(ar[j + 6][14 - i + j + 6] == 1 || ar[j + 6][14 - i + j + 6] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, j + k, 14 - i + j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						for (k = 0; k < 7; k++) {
							if (ar[j + k][14 - i + j + k] == 2) {
								fill_2x2(ar, j + k - 1, 14 - i + j + k - 1, vcCom, result, coor);
								if (put_count == 2)
									break Loop1;
								break Loop2;
							}
						}
					}
				}
			}
		}
		Loop1: for (i = 1; i < 13; i++) {
			Loop2: for (j = 0; j < 13 - i; j++) {
				if (ar[i + j][j] == 1 || ar[i + j][j] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, i + j + k, j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						int cp = put_count; // 0 or 1
						for (k = 0; k < 7; k++) {
							if (put_count > cp)
								break Loop2;
							fill_2x2(ar, i + j + k, j + k, vcCom, result, coor);
							if (put_count == 2)
								break Loop1;
						}
					}
				} else if(ar[i + j + 6][j + 6] == 1 || ar[i + j + 6][j + 6] == 3) {
					reset();
					for (k = 0; k < 7; k++) {
						check_2x2(ar, i + j + k, j + k);
					}
					if ((num_enemy + num_brick == 1) && num_mine > 0) {
						for (k = 0; k < 7; k++) {
							if (ar[i + j + k][j + k] == 2) {
								fill_2x2(ar, i + j + k - 1, j + k - 1, vcCom, result, coor);
								if (put_count == 2)
									break Loop1;
								break Loop2;
							}
						}
					}
				}
			}
		}
	}
	public static void fill_2x2(int[][] ar, int i, int j, ArrayList<Point> vcCom, int[][] resultt, ArrayList<Integer> coor) {
		if (ar[i][j] <= 0) {
			put_count++ ;
			ar[i][j] = 2;
			resultt[i][j] = 2;
			coor.add(i); coor.add(j);
			vcCom.add(new Point((int) (j * 41.7 + 26), (int) (i * 41.6 + 28)));
		}
	}
	
	public static void reset() {
		num_mine = 0;
		num_enemy = 0;
		num_empty = 0;
		num_brick = 0;
	}
	
	public static void check_2x2(int[][] ar, int i, int j) {
		if (ar[i][j] == 1)
			num_enemy++;
		if (ar[i][j] == 2)
			num_mine++;
		if (ar[i][j] == 0)
			num_empty++;
		if (ar[i][j] == 3)
			num_brick++;
	}
}
