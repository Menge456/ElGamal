import java.math.BigInteger;
import java.util.Random;
import java.util.Scanner;
public class EGDriver {
	static Random rnd = new Random();
	static User alex = new User();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		
		//User billie = new User();
		
		boolean testing;
		int tests = 0;
		
		//test of encryption and decryption
		do {
			testing = test2();
			if(testing == true)
				tests++;
			
		}while(testing == true && tests < 300);
		System.out.println(tests);
	}
	
	//testing whether or not the encrypt and decrypt work
	public static boolean test1() {
		BigInteger a;
		BigInteger test = new BigInteger("-1");
		
		//making sure the numbers work 
		do {
			a = new BigInteger(20, rnd);

		}while(!a.isProbablePrime(3000));
		
		BigInteger[] b = alex.encrypt(a);
		test = alex.decrypt(b);
		
		return a.toString().equals(test.toString());
	}
	
	//testing multiplying the things
	public static boolean test2() {
		BigInteger a;
		BigInteger b;
		BigInteger[] c;
		BigInteger[] d;
		
		//making sure of working numbers
		do {
			a = new BigInteger(20, rnd);

		}while(!a.isProbablePrime(30));
		
		do {
			b = new BigInteger(20, rnd);

		}while(!b.isProbablePrime(30));
		//Doing the encryption * each other
		
		c = alex.encrypt(a);
		d = alex.encrypt(b);
		for(int i = 0; i < 2; i++)
			c[i] = alex.multiply(c[i], d[i]);
			
		d[0] = alex.decrypt(c);
		a = alex.multiply(a,b);
		
		String ret = d[0].toString();
		String ret2 = a.toString();
		
		return ret.equals(ret2);
		
	}

}
