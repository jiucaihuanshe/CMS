package aiyunnet.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * Spring 整合jackson以后，在将一个对象转换json串时，假如
 * 有日期类型，默认会将日期类型转换为长整型的字符串，当我们
 * 需要一个具体的日期格式字符串时，可以自己写个类继承
 * JsonSerializer然后写serialize方法，在此方法完成
 * 日期对象的转换，具体应用还需要在实体对象的get方法上
 * 借助@JsonSerializer注解使用此类
 */
public class DateTimeFormatConverter extends JsonSerializer<Date>
{
	/**
	 * 负责相关对象的转换操作
	 * @param value 表示要转换的数据
	 * @param generator 负责将转换的数据写到json串中
	 */
	@Override
	public void serialize(Date value, JsonGenerator generator, SerializerProvider serializer) throws IOException
	{
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String dateStr=sdf.format(value);
		generator.writeString(dateStr);		
	}

}
