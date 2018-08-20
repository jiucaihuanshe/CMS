package aiyunnet.web.custom;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * global exception handler
 */
@ControllerAdvice
public class ServerExceptionHandler
{
	@ExceptionHandler(Exception.class)
	@ResponseBody
	public String handlerException(Exception e)
	{
		//logging the server exception
		return e.getMessage();
	}
}
