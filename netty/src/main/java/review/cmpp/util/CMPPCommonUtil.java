package review.cmpp.util;

public final class CMPPCommonUtil {
	
	public static byte[] ensureLength(byte array[], int minLength, int padding) {
	    //		Preconditions.checkArgument(minLength >= 0, "Invalid minLength: %s", new Object[] { Integer.valueOf(minLength) });
        // 		Preconditions.checkArgument(padding >= 0, "Invalid padding: %s", new Object[] { Integer.valueOf(padding) });
	    if (minLength < 0) {
            throw new IllegalArgumentException(String.format("Invalid minLength: %s", minLength));
        }
        if (padding < 0) {
            throw new IllegalArgumentException(String.format("Invalid padding: %s", padding));
        }
		if (array.length == minLength) {
            return array;
        }
		return array.length > minLength ? copyOf(array, minLength) : copyOf(array, minLength + padding);
	}

	private static byte[] copyOf(byte original[], int length) {
		byte copy[] = new byte[length];
		System.arraycopy(original, 0, copy, 0, Math.min(original.length, length));
		return copy;
	}

//	public static SmsTextMessage buildTextMessage(String text){
//
//			return new SmsTextMessage(text);
//
//	}

}
