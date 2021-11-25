import java.awt.Point;
import java.util.ArrayList;
import java.util.Scanner;

public class DummyAI {
	static ArrayList<Point> vc = new ArrayList<>(); // 서버 돌 
	static ArrayList<Point> vcCom = new ArrayList<>(); // AI 돌 
	static ArrayList<Point> dont = new ArrayList<>(); // 착수 금지점
	static int[][] result = new int[19][19]; // 게임 돌 현황
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Input the ip address > ");
		String ip = scanner.nextLine();
		System.out.print("Input the port number > ");
		int port = Integer.parseInt(scanner.nextLine());
		System.out.print("Input the color > ");
		String color = scanner.nextLine();
		
		ConnectSix conSix = new ConnectSix(ip, port, color);
		System.out.println("Red Stone positions are " + conSix.redStones);
		
		//result에 적돌 표현 , dont에 추가 
		ArrayList<Integer> red = Util.setRedStone(conSix.redStones);
		for(int i=0; i<red.size()/2; i++) {
			result[red.get(i)][red.get(i+1)] = 3;
			dont.add(new Point((int) (red.get(i+1) * 41.7 + 26), (int) (red.get(i) * 41.6 + 28)));
		}
		
		if (color.toLowerCase().compareTo("black") == 0) {
			String first = conSix.drawAndRead("K10"); //K10보내고 흰돌 2개 읽음 
			System.out.println("send: K10");
			// result에 AI 돌 1개 표현, vcCom에 추가 
			result[9][9] = 2;
			vcCom.add(new Point((int) (9 * 41.7 + 26), (int) (9 * 41.6 + 28)));
			// result에 서버돌 2개 표현, vc에 추가 
			ArrayList<Integer> white = Util.mkCoor(first);
			result[white.get(0)][white.get(1)]= 1;
			result[white.get(2)][white.get(3)]= 1;
			vc.add(new Point((int) (white.get(1) * 41.7 + 26), (int) (white.get(0) * 41.6 + 28)));
			vc.add(new Point((int) (white.get(3) * 41.7 + 26), (int) (white.get(2) * 41.6 + 28)));
		} else if (color.toLowerCase().compareTo("white") == 0) {
			String first = conSix.drawAndRead("");  //empty보내고 K10읽음 
			System.out.println("send: empty");
			// result에 서버돌 1개 표현, vc에 추가 
			result[9][9] = 1;
			vc.add(new Point((int) (9 * 41.7 + 26), (int) (9 * 41.6 + 28)));
		}
		
		while (true) {
			/*
			char alpha1 = (char) ((Math.random() * 19) + 'A');
			int num1 = (int)( Math.random() * 19) + 1;
			char alpha2 = (char) ((Math.random() * 19) + 'A');
			int num2 = (int)( Math.random() * 19) + 1;
			
			String draw = String.format("%c%02d:%c%02d", alpha1, num1, alpha2, num2);
			
			String read = conSix.drawAndRead(draw);  */
			
			String draw = DummyAI.play();
			System.out.println("-send: "+draw);
			String read = conSix.drawAndRead(draw);
			// 여기에 읽은거 vc에 넣고, result에 표시해야 함 
			System.out.println("read: "+read);
			ArrayList<Integer> tmp = Util.mkCoor(read);
			result[tmp.get(0)][tmp.get(1)]= 1;
			result[tmp.get(2)][tmp.get(3)]= 1;
			vc.add(new Point((int) (tmp.get(1) * 41.7 + 26), (int) (tmp.get(0) * 41.6 + 28)));
			vc.add(new Point((int) (tmp.get(3) * 41.7 + 26), (int) (tmp.get(2) * 41.6 + 28)));
			
			if(read.compareTo("WIN") == 0 || read.compareTo("LOSE") == 0 || read.compareTo("EVEN") == 0) {
				 break;
			}
		}

	}
	
	public static String play() { // draw String 반환 
		String draw;
		ArrayList<Integer> coor = new ArrayList<>();
		
		if(vcCom.size()==1) { //여기는 검정 2번째 수 인 경우 
			draw = Util.comBlackSecond(vcCom, result, dont);
			//repaint();
			return draw;
		}
		
		int winNum = AttackFirst.detectThreatNum(result);     //이길 수 있으면 이기면 됨 
		System.out.println("winNum "+winNum);  
		if(winNum==1) { // 공2개 써야함 
			int[][] threat = AttackFirst.detectThreat(result);
			for(int m=0; m<19; m++) {
				for(int n=0; n<19; n++) {
					if(threat[m][n]<0) {
						vcCom.add(new Point((int) (n * 41.7 + 26), (int) (m * 41.6 + 28)));
						result[m][n] = 2;
						//decideWinnerCom(m,n);
						coor.add(m); coor.add(n);
						if(coor.size()==4) {
							draw = Util.mkCoordAL(coor);
							return draw;
						}
					}
				}
			}
			//return ;
		}
		else if(winNum>=2) {
			int[][] threat = AttackFirst.detectThreat(result);
			int maxM = 0, maxN = 0;
			for (int m = 0; m < 19; m++) {
				for (int n = 0; n < 19; n++) {
					if (threat[maxM][maxN] > threat[m][n]) {
						maxM = m;
						maxN = n;
					}
				}
			}
			System.out.println("maxM, maxN, value "+maxM+" "+maxN+" "+threat[maxM][maxN]);
			//int a =0;
			for (int m = 0; m < 19; m++) {
				for (int n = 0; n < 19; n++) {
					if (threat[maxM][maxN] == threat[m][n]) {
						//a++;
						vcCom.add(new Point((int) (n * 41.7 + 26), (int) (m * 41.6 + 28)));
						result[m][n] = 2;
						coor.add(m); coor.add(n);
						//decideWinnerCom(m,n);
						//if(a==2) break;
						if(coor.size()==4) {
							draw = Util.mkCoordAL(coor);
							return draw;
						}
					}
				}
			}
			//draw = Util.mkCoordAL(coor);
			//return draw;
		}
		
		//수비하고 공격  
		int threatNum = Threats.detectThreatNum(result);
		System.out.println("threat: " + threatNum);
		if (threatNum == 2) {  //2개이면 2개 다 막기 
			int[][] threat = Threats.detectThreat(result);
			int maxM = 0, maxN = 0;
			for (int m = 0; m < 19; m++) {
				for (int n = 0; n < 19; n++) {
					if (threat[maxM][maxN] > threat[m][n]) {
						maxM = m;
						maxN = n;
					}
				}
			}
			vcCom.add(new Point((int) (maxN * 41.7 + 26), (int) (maxM * 41.6 + 28)));
			result[maxM][maxN] = 2;
			coor.add(maxM); coor.add(maxN);
			System.out.println();
			threat = Threats.detectThreat(result);
			maxM = 0;
			maxN = 0;
			for (int m = 0; m < 19; m++) {
				for (int n = 0; n < 19; n++) {
					if (threat[maxM][maxN] > threat[m][n]) {
						maxM = m;
						maxN = n;
					}
				}
			}
			vcCom.add(new Point((int) (maxN * 41.7 + 26), (int) (maxM * 41.6 + 28)));
			result[maxM][maxN] = 2;
			coor.add(maxM); coor.add(maxN);
			//repaint();
			draw = Util.mkCoordAL(coor);
			return draw;
		} else if (threatNum == 1) {  //threat 1개. 돌 1개 남음 
			
			int[][] threat = Threats.detectThreat(result);
			int maxM = 0, maxN = 0;
			for (int m = 0; m < 19; m++) {
				for (int n = 0; n < 19; n++) {
					if (threat[maxM][maxN] > threat[m][n]) {
						maxM = m;
						maxN = n;
					}
				}
			}
			vcCom.add(new Point((int) (maxN * 41.7 + 26), (int) (maxM * 41.6 + 28)));
			result[maxM][maxN] = 2;
			coor.add(maxM); coor.add(maxN);
			
			PrimaryAttack.put_count=1;   //최우선 공격하고 돌 남으면 밑에거 하기 
			PrimaryAttack.detectPrimaryThreat(vcCom, result, coor);
			
			if(PrimaryAttack.put_count == 1) { // PrimaryAttack에서 add안된경우 
				PrimaryAttack.put_count=0;
				Attack.put_count=1;
				Attack.putStone(result, vcCom, coor);
				if(Attack.put_count==1) {  // Attack에서 add안된 경우 //3개 포함 공백도 없고, 2개 포함 공백도 없음 
					//예측막기 
					Protect3.put_count=1;
					Protect3.protectThree(result, vcCom, coor);
					if(Protect3.put_count==1) {  //Protect3에서 add안된 경우  //33막기 없음 
						NormAttack.put_count=1;
						NormAttack.putStone(result, vcCom, coor);
						if(NormAttack.put_count==1) {
							Util.putStoneRandomly1(vcCom, result, coor);  //랜덤 1개 두기 
						}
						else System.out.println("6.5");
					}
					else System.out.println("6");
				}
				else if(Attack.put_count==0){  //공백 3개 또는 공백 2개 처리함 
					System.out.println("6_");
				}
			}
			//repaint();
			draw = Util.mkCoordAL(coor);
			return draw;
		}
		else if(threatNum==0) {  //threat 개수 0개이면 
			
			int size1 = vcCom.size();
			PrimaryAttack.put_count=0;   //최우선 공격하고 돌 남으면 밑에거 하기 
			PrimaryAttack.detectPrimaryThreat(vcCom, result, coor);
			
			if(PrimaryAttack.put_count==1) { //돌 1개 남음 
				PrimaryAttack.put_count = 0;
				Attack.put_count=1;
				Attack.putStone(result, vcCom, coor);  
				if(Attack.put_count==1) {  //돌 1개 남음 
					
					//예측막기
					PrimaryProtect.put_count=1;
					PrimaryProtect.detectPrimaryProtect(vcCom, result, coor);
					
					if(PrimaryProtect.put_count==1) {
						Protect3.put_count =1;
						Protect3.protectThree(result, vcCom, coor);
						if(Protect3.put_count==1) {  //33막기 없음. 돌 1개 남음 
							NormAttack.put_count=1;
							NormAttack.putStone(result, vcCom, coor);
							if(NormAttack.put_count==1) {  
								Util.putStoneRandomly1(vcCom, result, coor);  //랜덤 1개 두기 
							}else System.out.println("7.5");
						}
						else System.out.println("7");
					}
					else System.out.println("0.7"); 
	
				}
			}
			else if(PrimaryAttack.put_count==0) {
				if(vcCom.size()>size1) {  //돌 2개 다 썼음 끝. 
					System.out.println("5");
				}else {  //돌 하나도 안썼음. 돌2개 남음 
					int sizeBeforeAttack = vcCom.size();
					Attack.put_count=0;
					Attack.putStone(result, vcCom, coor);  
					if(Attack.put_count==1) {  //빈칸포함3, 빈칸포함2 공격 1개 쓰고 1개 남음. 
						//예측막기
						PrimaryProtect.put_count=1;
						PrimaryProtect.detectPrimaryProtect(vcCom, result, coor);
						
						if(PrimaryProtect.put_count==1) {
							Protect3.put_count = 1;
							Protect3.protectThree(result, vcCom, coor);
							if(Protect3.put_count==1) {  //33막기 없음. 돌1개 남음 
								NormAttack.put_count=1;
								NormAttack.putStone(result, vcCom, coor);
								if(NormAttack.put_count==1) {  //돌 1개 남음 
									Util.putStoneRandomly1(vcCom, result, coor);  //랜덤두기 
								}
								else System.out.println("8.5");
							}else System.out.println("8");
						}
						else System.out.println("0.8");
						
					}
					else if(Attack.put_count==0) {
						if(vcCom.size()>sizeBeforeAttack) { // 돌 2개 다 썼음. 끝 
							System.out.println("3");
						}
						else { //빈칸 포함 3개 없음, 빈칸 포함 2개 없음. 돌 2개 남음 
							
							//예측막기
							PrimaryProtect.put_count=0;
							PrimaryProtect.detectPrimaryProtect(vcCom, result, coor);
							
							if(PrimaryProtect.put_count==1) {  //돌 1개 남음 
								Protect3.put_count=1;
								Protect3.protectThree(result, vcCom, coor);
								if(Protect3.put_count==1) {  //돌 1개 남음 
									NormAttack.put_count=1;
									NormAttack.putStone(result, vcCom, coor);
									if(NormAttack.put_count==1) {
										Util.putStoneRandomly1(vcCom, result, coor);  //랜덤두기 
									}else 
										System.out.println("4");
								}else 
									System.out.println("11");
							}
							else if(PrimaryProtect.put_count==0) { //돌 2개 남음 
								int sizeBeforeProtect = vcCom.size();
								Protect3.put_count=0;
								Protect3.protectThree(result, vcCom, coor);
								if(Protect3.put_count==1) { //돌 1개 남음 
									NormAttack.put_count=1;
									NormAttack.putStone(result, vcCom, coor);
									if(NormAttack.put_count==1) {
										Util.putStoneRandomly1(vcCom, result, coor);  //랜덤두기 
									}else 
										System.out.println("4");
								}
								else if(Protect3.put_count==0) {
									if(vcCom.size()>sizeBeforeProtect) {  //돌 2개 다 썼음. 끝 
										System.out.println("9");
									}else {  //돌 2개 남음 (33없음)
										int sizeBeforeNA = vcCom.size();
										NormAttack.put_count=0;
										NormAttack.putStone(result, vcCom, coor);
										if(NormAttack.put_count==1) {  //돌 1개 남음 
											//랜덤 두기 
											Util.putStoneRandomly1(vcCom, result, coor);
											System.out.println("9.5");
											
										}else if(NormAttack.put_count==0) {
											if(vcCom.size()>sizeBeforeNA) { //돌 다 씀.
												System.out.println("10");
											}else {  //돌 2개 남음 
												//랜덤 두기 
												Util.putStoneRandomly2(vcCom, result, coor);
												System.out.println("10.5");
											}
										}
									}
								}
								
							}
							
							
						}
					}
				}
			}
			
			//repaint();
			draw = Util.mkCoordAL(coor);
			return draw;
		}
		else {  // threatNum>2 ...
			//JOptionPane.showConfirmDialog(null, "threat3개 이상이어서 유저가 이김 ...", "Lose", JOptionPane.WARNING_MESSAGE);
			Util.putStoneRandomly2(vcCom, result, coor);
			draw = Util.mkCoordAL(coor);
			return draw;
		}
	}
}
