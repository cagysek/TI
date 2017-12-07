package semestralka;

import java.util.*;


public class Main {
	
	private static int sumaM = 0;
	private static int[] M = {1,2,5,10,20,50};
	private static Scanner sc = new Scanner(System.in);
	private static boolean minceVhozeny = false;
	
	
	private static int b01_cena = 10;
	private static int b02_cena = 33;
	private static int b03_cena = 28;
	
	private boolean m01_stav = false;
	private boolean m02_stav = false;
	private boolean m03_stav = false;
	
	private boolean c01_stav = false;
	
	
	private static boolean timerRunning = false;
	
	public static void main(String[] args) {
		
		
		System.out.println("Vítejte");
		
		while(true){
			String input = sc.nextLine();
			
		//	System.out.println(Arrays.toString(M));
			String[] line = input.split("-");
			
			chooseStartAction(line[1].charAt(0));
			
			while(minceVhozeny) {
				
					
				String[] parameter = line[1].split("=");
				int value;
				
				try {
					
					
					
					value = Integer.parseInt(parameter[1]);
					
					switch(parameter[0]) {
					case "M":
						sumaM += value;
						System.out.println("Soucet: "+sumaM);
						break;
					case "T":
						if(checkCena(value)==true) {
							start_timer_and_motor();
							
					     
					 					        
						}
						break;
				}
				
			//	System.out.println(Arrays.toString(money));
			//	System.out.println(1);

			//	minceVhozeny = false;	
				
				System.out.println("TEST END");
				if(timerRunning == false) {
				input = sc.nextLine();
					
					//	System.out.println(Arrays.toString(M));
				line = input.split("-");
				
				}
					
				}catch(Exception ex) {
					System.out.println("Špatná hodnota parametru "+parameter[0]+", správný zápis je "+parameter[0]+"=x, kde x je "
							+ "celé číslo");
					break;	
				
				}	
				
				
				
			}
			
			
		}

	}
	
	
	private static void start_timer_and_motor() {
		int n = 0;
		boolean vyhodnoceni = cidloSenzor();
		System.out.println(vyhodnoceni);
		do {
			System.out.println(n);
			n++;
		}while(n<100000 && (vyhodnoceni==false));
		System.out.println("after");
		return;
	}
	
	private static boolean cidloSenzor() {
		Random random = new Random();
	    return random.nextBoolean();
	}
	
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
	
	private static void chooseStartAction(char action) {
		try {
			switch(action) {
			
				case 'M':
					minceVhozeny = true;
					break;
					
				case 'T':
					System.out.println("Nejprve vhoďte mince");
					break;
				case 'v':
					System.out.println("parametr -M=x, kde za \"x\" dosadíš hodnotu mince");
					System.out.println("parametr -T=x, kde za \"x\" dosadíš číslo tlačítka\"");
					System.out.println("parametr -C pokud si přeješ vrátit mince");
				default:
					break;
				
					
				
			}
		}catch(Exception ex) {
			System.out.println("Špatný formát vstupu. Pro nápovědu zadejde -v");
		}
		
	}
	

}
