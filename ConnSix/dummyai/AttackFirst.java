/**
 * detect opponents's threat 
 * @author hanseunghwa
 *
 */
public class AttackFirst {
//2 컴퓨터(나)의 공격 세는 거 
	
	static int num_mine = 0;
	static int num_empty=0;
	
	public static int detectThreatNum(int[][] now) {
		int threatNum = 0;
		int[][] threat = new int[19][19];
		for(int i=0; i<19; i++) {
			for(int j=0; j<19; j++) {
				threat[i][j]=now[i][j];
			}
		}
		
		threatNum = DMT(threat);
		System.out.print(threatNum);
		threatNum+= DMT2(threat);
		System.out.print(" "+threatNum);
		threatNum+= DMT3(threat);
		System.out.print(" "+threatNum);
		threatNum+= DMT4(threat);
		System.out.println(" "+threatNum);
		
//		for (int i = 0; i < threat.length; i++) {
//			for (int j = 0; j < threat[i].length; j++) {
//				System.out.print(threat[i][j] + " ");
//			}
//			System.out.println();
//		}
		
		return threatNum;
	}
	
	public static int[][] detectThreat(int[][] now) {
		int[][] threat = new int[19][19];
		for(int i=0; i<19; i++) {
			for(int j=0; j<19; j++) {
				threat[i][j]=now[i][j];
			}
		}
//		detectMyHorizentalThreat(now, threat);
//		detectMyVerticalThreat(threat);
		DMT_CRI(threat);
		DMT2_CRI(threat);
		DMT3_CRI(threat);
		DMT4_CRI(threat);
		
		for (int i = 0; i < threat.length; i++) {
			for (int j = 0; j < threat[i].length; j++) {
				System.out.print(threat[i][j] + " ");
			}
			System.out.println();
		}
		return threat;
	}
	
	
	public static void detectMyHorizentalThreat(int[][] now, int[][] threat) { // 가로
		
		for (int i = 0; i < 19; i++) {
			int leftWindow = 0;
			int rightWindow = 5;
			int count1 = 0;
			int count0 = 0;
			if (leftWindow == 0) { // 맨 처음일때 (0~5)
				for (int k = leftWindow; k <= rightWindow; k++) {
					if (now[k][i] == 2) {
						threat[k][i] = 2;
					} else if (now[k][i] == 1) {
						count1++;
						threat[k][i] = 1;
					} else if (now[k][i] == 0)
						count0++;
				}
				if ((count1 == 4 && count0 == 2) || (count1 == 5 && count0 == 1) || (count1 == 6 && count0 == 0)) {
					for (int k = leftWindow; k <= rightWindow; k++) {
						if (threat[k][i] <= 0) {
							threat[k][i] -= 1;
						}
					}
				}
			}
			while (leftWindow < 13) { // 그 다음부터
				if (now[leftWindow][i] == 0)
					count0--;
				else if (now[leftWindow][i] == 1)
					count1--;

				if (now[rightWindow + 1][i] == 1) { // 그 다음 오른쪽
					threat[rightWindow + 1][i] = 1;
					count1++;
				} else if (now[rightWindow + 1][i] == 2) {
					threat[rightWindow + 1][i] = 2;
				} else if (now[rightWindow + 1][i] == 0) {
					count0++;
				}

				if ((count1 == 4 && count0 == 2) || (count1 == 5 && count0 == 1) || (count1 == 6 && count0 == 0)) {
					for (int k = leftWindow + 1; k <= rightWindow + 1; k++) {
						if (threat[k][i] <= 0) {
							threat[k][i] -= 1;
						}
					}
				}
				leftWindow++;
				rightWindow++;
			}

		}
	}

	public static void detectMyVerticalThreat(int[][] hori) {
		int[][] verticalResult = new int[19][19];

		for (int i = 0; i < 19; i++) {
			int leftWindow = 0;
			int rightWindow = 5;
			int count1 = 0;
			int count0 = 0;
			if (leftWindow == 0) { // 맨 처음일때 (0~5)
				for (int k = leftWindow; k <= rightWindow; k++) {
					if (hori[i][k] == 2) {
						verticalResult[i][k] = 2;
					} else if (hori[i][k] == 1) {
						count1++;
						verticalResult[i][k] = 1;
					} else if (hori[i][k] <= 0)
						count0++;
				}
				if ((count1 == 4 && count0 == 2) || (count1 == 5 && count0 == 1) || (count1 == 6 && count0 == 0)) {
					for (int k = leftWindow; k <= rightWindow; k++) {
						if (verticalResult[i][k] == 0) {
							hori[i][k] -= 10;
						}
					}
				}
			}
			while (leftWindow < 13) { // 그 다음부터
				if (hori[i][leftWindow] <= 0)
					count0--;
				else if (hori[i][leftWindow] == 1)
					count1--;

				if (hori[i][rightWindow + 1] == 1) { // 그 다음 오른쪽
					verticalResult[i][rightWindow + 1] = 1;
					count1++;
				} else if (hori[i][rightWindow + 1] == 2) {
					verticalResult[rightWindow + 1][i] = 2;
				} else if (hori[i][rightWindow + 1] == 0) {
					count0++;
				}
				if ((count1 == 4 && count0 == 2) || (count1 == 5 && count0 == 1) || (count1 == 6 && count0 == 0)) {
					for (int k = leftWindow + 1; k <= rightWindow + 1; k++) {
						if (verticalResult[i][k] == 0) {
							hori[i][k] -= 10;
						}
					}
				}
				leftWindow++;
				rightWindow++;
			}
		}
		
//		for (int i = 0; i < verticalResult.length; i++) {
//			for (int j = 0; j < verticalResult[i].length; j++) {
//				System.out.print(hori[j][i] + " ");
//			}
//			System.out.println();
//		}
	}

	
	public static void DMT_CRI(int[][] ar) {
		
		int i = 0 ;
		int j = 0 ;
		for( i = 5 ; i < 19 ; i++ ) {	//위쪽 반
			for( j = 0 ; j < i - 4 ; j++ ) {
				check2_1(ar, i - j, j) ;
				if((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					set_weight(ar, i - j, j, 100) ;
				}
			}
		}
		
		for( i = 1; i < 14 ; i++) {	//아래쪽 반. 
			for( j = 0 ; j < 14 - i ; j++) {
				check2_1(ar, 18 - j , i + j) ;
				if(num_mine == 4 && num_empty == 2 || (num_mine == 5 && num_empty == 1)) {
					set_weight(ar, 18 - j, i + j, 100) ;
				}
			}
		}
	}
	
	public static void DMT2_CRI(int[][] ar) {
		int i = 0 ;
		int j = 0 ;
		
		for( i = 1 ; i < 15 ; i++ ) {	//위쪽 반.
			for( j = 0 ; j < i ; j++ ) {
				check2_2(ar, j, 14 - i + j) ;
				if((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					set_weight2(ar, j, 14 - i + j, 1000) ;
				}
			}
		}
		
		for( i = 1; i < 14 ; i++) {	//아래쪽 반. 
			for( j = 0 ; j < 14 - i ; j++) {
				check2_2(ar, i + j , j) ;
				if(num_mine == 4 && num_empty == 2 || (num_mine == 5 && num_empty == 1)) {
					set_weight2(ar, i + j, j, 1000) ;
				}
			}
		}
		
	}
	
	public static void DMT3_CRI(int[][] ar) { // 가로 방향.

		int i = 0;
		int j = 0;

		for (i = 0; i < 19 ; i++) {
			for (j = 0; j < 14 ; j++) {
				check2_3(ar, i, j);
				if ((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					System.out.println("Here works") ;
					set_weight3(ar, i, j, 1);
				}
			}
		}
	}
	
	public static void DMT4_CRI(int[][] ar) { // 세로 방향.

		int i = 0;
		int j = 0;

		for (i = 0; i < 19; i++) {
			for (j = 0; j < 14; j++) {
				check2_4(ar, j, i) ;
				if ((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					set_weight4(ar, j, i, 10);
				}
			}
		}
	}

	public static int DMT(int[][] ar) {
		
		int i = 0 ;
		int j = 0 ;
		int c = 1 ;
		
		for( i = 5 ; i < 19 ; i++ ) {	//위쪽 반.
			for( j = 0 ; j < i - 4 ; j++ ) {
				check2_1(ar, i - j, j) ;
				if((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					int k = set_weight(ar, i - j, j, 100 * c) ;
					c++ ;
					j += k ;
				}
			}
		}
		
		for( i = 1; i < 14 ; i++) {	//아래쪽 반. 
			for( j = 0 ; j < 14 - i ; j++) {
				check2_1(ar, 18 - j , i + j) ;
				if((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					int k = set_weight(ar, 18 - j, i + j, 100 * c) ;
					c++ ;
					j += k ;
				}
			}
		}
		
		return (c-1);
	}
	
	public static int DMT2(int[][] ar) {
		
		int i = 0 ;
		int j = 0 ;
		
		int c = 1 ;
		
		for( i = 1 ; i < 15 ; i++ ) {	//위쪽 반.
			for( j = 0 ; j < i ; j++ ) {
				check2_2(ar, j, 14 - i + j) ;
				if((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					int k = set_weight2(ar, j, 14 - i + j, 1000 * c) ;
					c ++ ;
					j += k ;
				}
			}
		}
		
		for( i = 1; i < 14 ; i++) {	//아래쪽 반. 
			for( j = 0 ; j < 14 - i ; j++) {
				check2_2(ar, i + j , j) ;
				if((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					int k = set_weight2(ar, i + j, j, 1000 * c) ;
					c ++ ;
					j += k ;
				}
			}
		}

		return (c-1);
		
	}
	
	public static int DMT3(int[][] ar) { // 가로 방향.

		int i = 0;
		int j = 0;
		int c = 1;

		for (i = 0; i < 19 ; i++) {
			for (j = 0; j < 14 ; j++) {
				check2_3(ar, i, j);
				if ((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					System.out.println("Here works") ;
					int k = set_weight3(ar, i, j, 1 * c);
					c++;
					j += k;
				}
			}
		}

		return (c-1);
	}
	
	public static int DMT4(int[][] ar) { // 세로 방향.

		int i = 0;
		int j = 0;
		int c = 1;

		for (i = 0; i < 19; i++) {
			for (j = 0; j < 14; j++) {
				check2_4(ar, j, i) ;
				if ((num_mine == 4 && num_empty == 2) || (num_mine == 5 && num_empty == 1)) {
					int k = set_weight4(ar, j, i, 10 * c);
					c++;
					j += k;
				}
			}
		}

		return (c-1);

	}
	
	public static void check2_3(int[][] find_state, int i, int j) {
		reset();

		check(find_state, i, j);
		check(find_state, i, j + 1);
		check(find_state, i, j + 2);
		check(find_state, i, j + 3);
		check(find_state, i, j + 4);
		check(find_state, i, j + 5);
		
	}
	
	public static void check2_4(int[][] find_state, int i, int j) {
		reset();

		check(find_state, i, j);
		check(find_state, i + 1, j);
		check(find_state, i + 2, j);
		check(find_state, i + 3, j);
		check(find_state, i + 4, j);
		check(find_state, i + 5, j);
		
	}
	
	public static int set_weight3(int[][] find_state, int i, int j, int weight) {
		int m_pos = 0;

		if (find_state[i][j] <= 0) {
			find_state[i][j] -= weight ;
		}

		if (find_state[i][j + 1] <= 0) {
			find_state[i][j + 1] -= weight ;
			m_pos = 1;
		}

		if (find_state[i][j + 2] <= 0) {
			find_state[i][j + 2] -= weight;
			m_pos = 2;
		}

		if (find_state[i][j + 3] <= 0) {
			find_state[i][j + 3] -= weight;
			m_pos = 3;
		}

		if (find_state[i][j + 4] <= 0) {
			find_state[i][j + 4] -= weight;
			m_pos = 4;
		}

		if (find_state[i][j + 5] <= 0) {
			find_state[i][j + 5] -= weight;
			m_pos = 5;
		}

		return m_pos;

	}
	
	public static int set_weight4(int[][] find_state, int i, int j, int weight) {
		int m_pos = 0;

		if (find_state[i][j] <= 0) {
			find_state[i][j] -= weight;
		}

		if (find_state[i + 1][j] <= 0) {
			find_state[i + 1][j] -= weight;
			m_pos = 1;
		}

		if (find_state[i + 2][j] <= 0) {
			find_state[i + 2][j] -= weight;
			m_pos = 2;
		}

		if (find_state[i + 3][j] <= 0) {
			find_state[i + 3][j] -= weight;
			m_pos = 3;
		}

		if (find_state[i + 4][j] <= 0) {
			find_state[i + 4][j] -= weight;
			m_pos = 4;
		}

		if (find_state[i + 5][j] <= 0) {
			find_state[i + 5][j] -= weight;
			m_pos = 5;
		}

		return m_pos;

	}
	
	public static void check(int[][] find_state, int i, int j) {

		if(find_state[i][j] == 2) {
			num_mine++ ;
		} else if(find_state[i][j] <= 0) {
			num_empty++ ;
		}
	}
		
	public static void check2_1(int[][] find_state, int i, int j) {
		reset() ;
		
		check(find_state, i, j) ;
		check(find_state, i-1, j+1) ;
		check(find_state, i-2, j+2) ;
		check(find_state, i-3, j+3) ;
		check(find_state, i-4, j+4) ;
		check(find_state, i-5, j+5) ;
		
	}
	
	public static void check2_2(int[][] find_state, int i, int j) {
		reset() ;
		
		check(find_state, i, j) ;
		check(find_state, i+1, j+1) ;
		check(find_state, i+2, j+2) ;
		check(find_state, i+3, j+3) ;
		check(find_state, i+4, j+4) ;
		check(find_state, i+5, j+5) ;
	}
	
	public static void reset() {
		num_mine = 0 ; 
		num_empty = 0 ;
	}
	
	public static int set_weight(int[][] find_state, int i, int j, int weight) {
		int m_pos = 0 ;
		
		if(find_state[i][j] <= 0 ) {
			find_state[i][j] = find_state[i][j] - weight ;
		}
		
		if(find_state[i-1][j+1] <= 0 ) {
			find_state[i-1][j+1] = find_state[i-1][j+1] - weight ;
			m_pos = 1 ;
		}
		
		if(find_state[i-2][j+2] <= 0) {
			find_state[i-2][j+2] = find_state[i-2][j+2] - weight ;
			m_pos = 2 ;
		}
		
		if(find_state[i-3][j+3] <= 0) {
			find_state[i-3][j+3] = find_state[i-3][j+3] - weight ;
			m_pos = 3 ;
		}
		
		if(find_state[i-4][j+4] <= 0) {
			find_state[i-4][j+4] = find_state[i-4][j+4] - weight ;
			m_pos = 4 ;
		}
		
		if(find_state[i-5][j+5] <= 0) {
			find_state[i-5][j+5] = find_state[i-5][j+5] - weight ;
			m_pos = 5 ;
		}
		
		return m_pos;
		
	}
	
	public static int set_weight2(int[][] find_state, int i, int j, int weight) {
		int m_pos = 0;
		
		if(find_state[i][j] <= 0 ) {
			find_state[i][j] = find_state[i][j] - weight ;
		}
		
		if(find_state[i+1][j+1] <= 0 ) {
			find_state[i+1][j+1] = find_state[i+1][j+1] - weight ;
			m_pos = 1 ;
		}
		
		if(find_state[i+2][j+2] <= 0) {
			find_state[i+2][j+2] = find_state[i+2][j+2] - weight ;
			m_pos = 2 ;
		}
		
		if(find_state[i+3][j+3] <= 0) {
			find_state[i+3][j+3] = find_state[i+3][j+3] - weight ;
			m_pos = 3 ;
		}
		
		if(find_state[i+4][j+4] <= 0) {
			find_state[i+4][j+4] = find_state[i+4][j+4] - weight ;
			m_pos = 4 ;
		}
		
		if(find_state[i+5][j+5] <= 0) {
			find_state[i+5][j+5] = find_state[i+5][j+5] - weight ;
			m_pos = 5 ;
		}
		
		return m_pos ;
		
	}
	
}


