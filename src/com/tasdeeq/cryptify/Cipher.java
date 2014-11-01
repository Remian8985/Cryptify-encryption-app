/*
 * Author: Tasdiq Ameem 
 * Date: 7 January, 2014
 *
 * 
 */
package com.tasdeeq.cryptify;
import java.util.Locale;


public class Cipher
{
	final String REF =				 " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?;:'\"@$%^&*()<>-+/={}[]`~";
	private final String GENERATED = ")!ZM <?0NA>;1OB-:2PC+'3QD/\"4RE=@5SF{$6TG}%7UH[^8VI]&9WJ`*.XK~(,YL";

	private final int LEN;
	private final int BLANK_INDEX;
	private final int PASS1;		// Original password
	private  int pass2;				// Coprime password

	private final String input;


	public Cipher(String in, int passOne)
	{
		input = use_padding(in.toUpperCase(Locale.ENGLISH));
		BLANK_INDEX = 0;
		PASS1 = passOne;
		pass2 = passOne;
		LEN = input.length();
		while (!isCoprime(pass2,LEN+1))
			pass2--;	
	}
	

	public String cipher_this()
	{
		String output = scramble();
		return shift(output);
	}


	private String scramble()
	{
		String output ="";
		int [] refIndex = new int[LEN];
		for (int i=0; i < LEN; i++){
			char focus = input.charAt(i);
			refIndex[i] = REF.indexOf(focus);
			if (refIndex[i] < 0)
				refIndex[i] = BLANK_INDEX;
		}
		for (int i = 0; i < LEN; i++) {
			int pos = ((i+1) * pass2 ) % (LEN+1);
			char addThis = GENERATED.charAt(refIndex[pos-1]);
			output = output + addThis;
		}
		return output;
	}

	
	private String shift(String interInput){
		String output ="";
		for (int i=0; i< LEN; i++){	
			int factor = REF.indexOf(interInput.charAt(i)) + 1;
			output = output + REF.charAt(get_final_pos(i+1, factor));
		}
		return output;
	}

	
	private int get_final_pos(int p, int q) { // ONLY FOR SHIFTING
		final int REF_SIZE = REF.length();
		int shift = (3 * p + PASS1 + q) % REF_SIZE;
		if (shift == 0)
			shift = REF_SIZE;
		return (shift -1);
	}

	
	private boolean isCoprime(int a, int b)
	{
		return (gcd(a,b) == 1);
	}
	
	private int gcd(int a, int b)
	{
		if (a== 1 || b== 1)			// base case
			return 1;
		else if (a==b)				// base case
			return a;
			
		else if (a>b)
			return gcd(a-b,b);
		else 
			return gcd(a,b-a);
	}
	
	
	private String use_padding(String str)
	{
		int gap = 24 - str.length();
		while (gap>0){
			str = str + " ";
			gap--;
		}
		return str;
	}
}