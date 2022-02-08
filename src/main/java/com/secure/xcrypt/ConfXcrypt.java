package com.secure.xcrypt;

import com.secrets.xkeys.Keys;

public class ConfXcrypt extends Keys {
	
	protected static String getKey() throws Exception {
		String result = new String();
		char[] charArray = Keys.privatekey.toCharArray();
		for(int i = 0; i < charArray.length; i=i+2) {
			String st = ""+charArray[i]+""+charArray[i+1];
			char ch = (char)Integer.parseInt(st, 16);
			result = result + ch;
		}

		return result;

	}

}
