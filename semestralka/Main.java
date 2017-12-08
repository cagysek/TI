package semestralka;

import java.util.*;


public class Main {
	
	private static int sumaM = 0;
	private static Integer[] M = new Integer[] {1,2,5,10,20,50};
	private static Scanner sc = new Scanner(System.in);
	private static boolean minceVhozeny = false;
	
	
	private static int b01_cena = 10;
	private static int b02_cena = 33;
	private static int b03_cena = 28;
	
	public static void main(String[] args) {
		
		//nabidka
		System.out.println("Vitejte");
		System.out.println("=========Nabidka============");
		System.out.println("tlac.     akt.poloz.	cena");
		System.out.println("T=1	  Susenka	10kc");
		System.out.println("T=2	  PepsiCola	33kc");
		System.out.println("T=3	  Bramburky	28kc");
		
		
		//hlavni cyklus
		while(true){
			String input = sc.nextLine();
			if(!input.equals("")){				
						String[] line = input.split("-");
						try{
							//kontrola jestli jsou vhozeny mince, logicky nejde bez toho pokracovat
							chooseStartAction(line[1].charAt(0));
							
							//smycka pro spravne chovani automatu
							while(minceVhozeny) {
								if(!input.equals("")){
								try {	
									String[] parameter = line[1].split("=");
									int value;		
									
									
									switch(parameter[0]) {
									
									//pokud hatim dalsi mince
									case "M":
										try {
											value = Integer.parseInt(parameter[1]);
											if(Arrays.asList(M).indexOf(value) != -1){ //kontrola jestli je z povoleneho intervalu
												sumaM += value;
												System.out.println("Soucet: "+sumaM);
											}else{
												System.out.println("Spatne formatovany vstup. Akceptovany je format -M=x, kde x je z intervalu {1,2,5,10,20,50}");
											}
											break;
										} catch (Exception e) {
											System.out.println("Spatne formatovany vstup. Akceptovany je format -M=x, kde x je z intervalu {1,2,5,10,20,50}");
											break;
										}
									
									//pokud stisknu tlacitko pro vyber
									case "T":
										try {
											value = Integer.parseInt(parameter[1]);
											if(value>0 && value<4){ //kontrola rozsahu tlacitek
												if(checkCena(value)==true) {//kontrola dostatecneho konta
													if(simulationOfDelivery()==true){//generovani sepnuti pohyboveho cidla
														substractMoney(value);
														getBackMoney();
														System.out.println("Vyzvednete si zbozi a nezapomente penize");
													}else{
														System.out.println("Zbozi nevypadlo, zvolte znovu nebo ukoncete nakup");
													}
												}else{
													System.out.println("Nedostatek penez,akci nelze dokoncit");
												}
											}else{
												System.out.println("Zvolene neplatne tlacitko. Rozsah tlacitek je 1-3");
											}
											break;
										} catch (Exception e) {
											System.out.println("Spatne formatovany vstup. Akceptovany je format -T=x, kde x je cele cislo");
											break;
										}
										
									//pokud stisknu cancel
									case "C":
										getBackMoney();
										break;
									
									//pro napovedu
									case "v":
										System.out.println("parametr -M=x, kde za \"x\" dosadte hodnotu mince z intervalu {1,2,5,10,20,50} pro navýšení konta");
										System.out.println("parametr -T=x, kde za \"x\" dosadte cislo tlacitka");
										System.out.println("parametr -C, pro preruseni nakupu a vraceni minci");
										break;
										
									//pokud nastane nejaka neocekavana akce
									default:
										System.out.println("Akce nenalezena, pro napovedu pouzijte -v");
										break;
										
								}
							}catch(Exception ex) {
								System.out.println("Spatna hodnota vstupu ");						
							}	
						}
						//nacteni dalsi akce	
						input = sc.nextLine();
						if(!input.equals("")){
							line = input.split("-");	
						}		
					}
				}catch(Exception exception){
					System.out.println("Akce nenalezena, pro napovedu pouzijte -v");
				}					
			}
		}

	}
	
	//metoda pro vypsani poctu minci co se ma vratit
	private static void getBackMoney() {
		// TODO Auto-generated method stub
		int back = sumaM;
		int num50 = back / 50;
		back -= num50 * 50;
	    int num20= back / 20;
	    back -= num20*20;
	    int num10  = back / 10;
	    back -= num10*10;
	    int num5 = back /5;
	    back -= num5*5;
	    int num2 = back /2;
	    back -= num2*2;
	    int num1 = back /1;
	    back -= num1*1;
	    
	    System.out.println("k vraceni: "+sumaM);
	    System.out.println("50: "+num50 + "x");
	    System.out.println("20: "+ num20+ "x");
	    System.out.println("10: "+num10 + "x");
	    System.out.println("5: "+ num5+ "x");
	    System.out.println("2: "+num2 + "x");
	    System.out.println("1: "+ num1+ "x");
	    
	    sumaM = 0;
	    minceVhozeny = false;
	    
	}

	//metoda pro odecitani ceny od vhozenych minci
	private static void substractMoney(int value) {
		// TODO Auto-generated method stub
		switch(value) {
		case 1:
			sumaM -= b01_cena;
			break;
		case 2:
			sumaM -= b02_cena;
			break;			
		case 3:
			sumaM -= b03_cena;
			break;	
		}
	}

	//metoda pro simulaci pohyboveho cidla
	private static boolean simulationOfDelivery() throws InterruptedException {
		System.out.println("Vydavam....");
		Random r = new Random();
		int Low = 0;
		int High = 10;
		int Result = r.nextInt(High-Low) + Low;
		if(Result > 3){
			Thread.sleep(2000); //thread sleep pro simulaci vydavani
			return true;
			
		}
		Thread.sleep(6000); //thread sleep pro simulaci vydavani
		return false;
		
	}
	
	
	//kontrola jestli je na konte dostatek penez
	private static boolean checkCena(int value) {
		switch(value) {
			case 1:
				if(sumaM >= b01_cena) {
					return true;
				}else return false;
			case 2:
				if(sumaM >= b02_cena) {
					return true;
				}else return false;
			case 3:
				if(sumaM >= b03_cena) {
					return true;
				}else return false;
			default: return false;
		}
	}
	
	//kontrola zacinajici akce
	//jako prvni se musi vhodit penize jinak nejde nic vykonavat
	private static void chooseStartAction(char action) {
		try {
			switch(action) {
			
				case 'M':
					minceVhozeny = true;
					break;
					
				case 'T':
					System.out.println("Nejprve vhodte mince");
					break;
				case 'C':
					System.out.println("Nejprve vhodte mince");
					break;
				case 'v':
					System.out.println("parametr -M=x, kde za \"x\" dosadte hodnotu mince z intervalu {1,2,5,10,20,50} pro navýšení konta");
					System.out.println("parametr -T=x, kde za \"x\" dosadte cislo tlacitka");
					System.out.println("parametr -C, pro preruseni nakupu a vraceni minci");
				default:
					System.out.println("Akce nenalezena, pro napovedu pouzijte -v");
					break;			
			}
		}catch(Exception ex) {
			System.out.println("Spatny format vstupu. Pro napovedu zadejde -v");
		}
		
	}
	

}
