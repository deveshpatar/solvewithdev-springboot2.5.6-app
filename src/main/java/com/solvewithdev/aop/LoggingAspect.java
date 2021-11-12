package com.solvewithdev.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

import com.solvewithdev.entity.UserLogin;

@Aspect
public class LoggingAspect {

	@Pointcut("execution(* get* ()")
	public void allGetters() {
	}

	@Before("args(name)")
	public void allCustomers(UserLogin name) {
		System.out.println("Customer  trying to login into application : " + name);
	}

	@Around("@annotation(com.devesh.aop.Loggable)")
	public Object myAroundAdvice(ProceedingJoinPoint pjp) {
		Object returnValue = null;
		try {
			System.out.println("Before advice execution : ");
			returnValue = pjp.proceed();
			System.out.println("After returning advice  :");
		} catch (Throwable e) {
			System.out.println("After Exception throwing");
		}

		System.out.println("After finally");
		return returnValue;
	}
}