import java.math.BigInteger;
import java.util.Scanner;


public class User {
	private BigInteger a;

	
	//[0] is p, [1] is g, [2] is h
	private static BigInteger[] pKey = new BigInteger[3];
	//x is a random number
	private static BigInteger[] sKey = new BigInteger[1];
	public User() {
		if(pKey[0] == null) {
			pKey[0] = new BigInteger("-1");
			pKey[1] = new BigInteger("-1");
			pKey[2] = new BigInteger("-1");
			sKey[0] = new BigInteger("-1");
			
		}
		Scanner sc = new Scanner(System.in);
		
		while(pKey[0].longValue() < 0) {
			System.out.print("Choose a prime (p). ");
			pKey[0] = new BigInteger(sc.nextLine());
			System.out.println();
		}
		
		while(pKey[1].longValue() < 0 || pKey[1].longValue() > pKey[0].longValue()){
			System.out.print("Choose a number less than p (g). ");
			pKey[1] = new BigInteger(sc.nextLine());
			System.out.println();
		}
		
		if(sKey[0].longValue() == -1) {
			
			sKey[0] =  new BigInteger(((int)(Math.random() * pKey[0].longValue()) + ""));
			pKey[2] = pKey[1].modPow(sKey[0], pKey[0]);
			
		}
		
		
	}
	
	public BigInteger[] encrypt(int message) {
		BigInteger[] ret = new BigInteger[2];
		
		BigInteger msg = new BigInteger(message + "");
		
		BigInteger y = new BigInteger(((int)(Math.random() * pKey[0].longValue()) + ""));
		
		ret[0] = pKey[1].modPow(y, pKey[0]);
		ret[1] = pKey[2].modPow(y, pKey[0]);
		ret[1] = ret[1].multiply(msg);
		ret[1] = ret[1].mod(pKey[0]);
			
		
		return ret;
	}
	
	public BigInteger[] encrypt(BigInteger msg) {
		BigInteger[] ret = new BigInteger[2];
		
		BigInteger y = new BigInteger(((int)(Math.random() * pKey[0].longValue()) + ""));
		
		ret[0] = pKey[1].modPow(y, pKey[0]);
		ret[1] = pKey[2].modPow(y, pKey[0]);
		ret[1] = ret[1].multiply(msg);
		ret[1] = ret[1].mod(pKey[0]);
			
		
		return ret;
	}
	
	
	//decrypts the size 2 array of BigInteger
	public BigInteger decrypt(BigInteger[] in) {
		BigInteger ret = new BigInteger("-1");
		if(in.length != 2)
			throw new IndexOutOfBoundsException("Need a size 2 array");
		else {
			in[0] = in[0].modPow(sKey[0], pKey[0]);
			
			in[0] = euAlg(in[0], pKey[0]);
			
			ret = in[0].multiply(in[1]);
			
			//don't know if I need this
			ret = ret.mod(pKey[0]);
		}
		return ret;
		
	}
	
	
	private BigInteger euAlg(BigInteger a, BigInteger m) {

	        BigInteger m0 = m;
	        BigInteger y = new BigInteger("0");
	        BigInteger x = new BigInteger("1");
	 
	        if (m.longValue() == 1)
	            return new BigInteger("0");
	 
	        if(a.longValue() > m.longValue()) {
	        	BigInteger temp = new BigInteger(a.longValue() + "");
	        	a = m;
	        	m = temp;
	        	
	        }
	        
	        
	        while (a.longValue() > 1 ) {
	            // q is quotient
	            BigInteger q = a.divide(m);
	            BigInteger t = m;
	            // m is remainder now, process
	            // same as Euclid's algo
	            m = a.mod(m);
	            a = t;
	            t = y;
	 
	            // Update x and y
	            y = x.subtract( q.multiply(y));
	            x = t;
	        }
	 
	        // Make x positive
	        if (x.longValue() < 0)
	            x = x.add(m0);
	 
	        return x;
	}
	public BigInteger multiply(BigInteger a, BigInteger b) {
		a = a.multiply(b);
		return a.mod(pKey[0]);
	}
	
}
