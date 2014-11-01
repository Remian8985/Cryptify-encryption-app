/*
 * Author: Tasdiq Ameem 
 * Date: 7 January, 2014
 *
 * 
 */
package com.tasdeeq.cryptify;

import java.util.Locale;


public class Decipher
{
	final String REF =				 " ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789.,!?;:'\"@$%^&*()<>-+/={}[]`~";
	private final String GENERATED = ")!ZM <?0NA>;1OB-:2PC+'3QD/\"4RE=@5SF{$6TG}%7UH[^8VI]&9WJ`*.XK~(,YL";

	private final int LEN;
	private final int PASS1;		// Original password
	private  int pass2;				// Coprime password
	private final int BLANK_INDEX;
	private final String input;


	public Decipher(String in, int passOne)
	{
		BLANK_INDEX = 0;
		input = use_padding(in.toUpperCase(Locale.ENGLISH));
		PASS1 = passOne;
		pass2 = passOne;
		LEN = input.length();
		while (!isCoprime(pass2,LEN+1))
			pass2--;
	}


	public String decipher_this()
	{
		String output = unshift();
		if (output.equals("LINE_BREAK_ERROR"))
			return output;
		return unscramble(output);
	}


	private String unscramble(String interInput)
	{
		String output = "";
		int [] refIndex = new int[LEN];

		for (int i = 0; i < LEN; i++) {
			int temp = ((i+1) * pass2) % (LEN+1);
			refIndex[temp - 1] = GENERATED.indexOf(interInput.charAt(i));;
		}
		for (int i=0; i<LEN; i++)
			output = output + REF.charAt(refIndex[i]);

		return output;
	}


	private String unshift()
	{
		String output = "";
		for (int i = 0; i < LEN; i++) {
			char focus = input.charAt(i);
			int initPos = REF.indexOf(focus);
			if (initPos < 0)
				initPos = BLANK_INDEX;
			initPos++;
			output = output + REF.charAt(unshift_pos(i+1, initPos));
		}
		return output;
	}

	
	private int unshift_pos(int p, int s) { // ONLY FOR UNSHIFTING
		final int REF_SIZE = REF.length();
		int q = s - 3 * p - PASS1;
		while (q <= 0) {
			q = REF_SIZE + q;
		}
		return q - 1;
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