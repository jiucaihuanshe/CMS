package aiyunnet.utils;

import java.security.MessageDigest;

public class EncryUtil
{
	public static String md5Encrypt (String inputStr)
	{
		try
		{
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(inputStr.getBytes());
			StringBuffer buffer = new StringBuffer();
			for (byte b : result)
			{
				int number = b & 0xff;
				String str = Integer.toHexString(number);
				if (str.length() == 1)
				{
					buffer.append("0");
				}
				buffer.append(str);
			}

			return buffer.toString();
		} catch (Exception e)
		{
			e.printStackTrace();
			return "";
		}

	}
}
