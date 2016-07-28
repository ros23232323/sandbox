package ie.ll.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HelloWorldService {

	@Value("${name:World}")
	private String name;

	public String getHelloMessage() {
		return "Hello ****** " + this.name;
	}

	public String throwExceptionTest() {
		int i = new Random().nextInt() % 3;
		System.out.println("**************value == " + i);
		switch (i){
			case 0:throw new NullPointerException("test exception NullPointerException");
			case 1:throw new IllegalArgumentException("test exception IllegalArgumentException");
			default:return "ok!!!!!";
		}
	}
}
