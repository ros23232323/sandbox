package ie.ll.aop;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

import org.springframework.stereotype.Component;

@Aspect
@Component
public class ErrorHandlingAspect {

	@AfterReturning("execution(* ie..*Service.*(..))")
	public void logServiceAccess(JoinPoint joinPoint) {
		System.out.println("Completed: " + joinPoint);
	}

	@AfterThrowing( pointcut = "execution(* ie..*Service.*(..))", throwing = "e" )
	public void logAfterThrowingNullPointerException(JoinPoint joinPoint, NullPointerException e) {
		System.out.println("An exception has been thrown in " + joinPoint.getSignature().getName() + "()");
	}

//	@AfterThrowing( pointcut = "execution(* ie..*Service.throwExceptionTest(..))", 		throwing = "e" 	)
//	public void logAfterThrowingIllegalArgumentException(JoinPoint joinPoint, IllegalArgumentException e) {
//		System.out.println("An exception has been thrown in " + joinPoint.getSignature().getName() + "()");
//	}

	@Around("execution(* ie..*Service.throwExceptionTest(..))")
	public Object intercept(ProceedingJoinPoint thisJoinPoint) {
		try {
			return thisJoinPoint.proceed();
		}
		catch (NullPointerException e) {
			System.out.println("An exception ("+e.getClass().getName()+") has been thrown in " + thisJoinPoint.getSignature().getName() + "()");
		}
		catch (RuntimeException e) {
		System.out.println("An exception ("+e.getClass().getName()+") has been thrown in " + thisJoinPoint.getSignature().getName() + "()");
		}
		catch (Throwable throwable) {
			throwable.printStackTrace();
		}
		return "Error recovered from";
	}

}
