package test;

public class GS {

	public static void main(String[] args) {/*
		String key = "98123456789987654323456789765432112352462317876544567898624862";
		String message = "Raajan Ashish Pal Anuj Kumar Saran Ashish Kumar Manish Agrahari";
		System.out.println(encode(message, key));
		System.out.println(decode(encode(message, key), key));
	*/
	
	}

	public static String encode(String message, String key) {
		StringBuilder output = new StringBuilder();
		int loopCount = key.length() <= message.length() ? key.length() : message.length();
		for (int i = 0; i < loopCount; i++) {
			int charRepeatCount = Integer.parseInt(String.valueOf(key.charAt(i)));
			char chartoAppend = message.charAt(i);
			apeend(output, charRepeatCount, chartoAppend);
		}
		output.append(message.substring(loopCount, message.length()));
		return output.toString();
	}

	public static void apeend(StringBuilder output, int charRepeatCount, char chartoAppend) {
		while (charRepeatCount > 0) {
			output.append(Character.valueOf(chartoAppend));
			charRepeatCount--;
		}
	}

	public static String decode(String message, String key) {
		StringBuilder output = new StringBuilder();
		int loopCount = key.length() <= message.length() ? key.length() : message.length();
		int startIndex = 0;
		for (int i = 0; i < loopCount && startIndex < message.length(); i++) {
			int charRepeatCount = Integer.parseInt(String.valueOf(key.charAt(i)));
			char chartoDelete = message.charAt(startIndex + charRepeatCount - 1);
			startIndex = startIndex + charRepeatCount;
			output.append(String.valueOf(chartoDelete));
		}
		output.append(message.substring(startIndex, message.length()));
		return output.toString();
	}

}
