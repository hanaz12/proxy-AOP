# Spring Proxy & AOP Deep Dive

This document serves as a detailed study guide on **Spring Proxies** and **Aspect-Oriented Programming (AOP)**, explaining how they work together in Spring applications.

---

## 1️⃣ Proxies in Spring

### What is a Proxy?
A **proxy** is an object that wraps another "real" object, intercepting method calls before (or instead of) forwarding them to the target object.

In Spring, proxies are used for:
- **Lazy loading** (e.g., Hibernate/JPA entities)
- **Transactional management** (e.g., `@Transactional`)
- **Security checks** (e.g., Spring Security)
- **Aspect-Oriented Programming (AOP)** (e.g., logging, caching)

---

### Proxy Types in Spring
Spring supports two main proxy mechanisms:

1. **JDK Dynamic Proxy**
   - Works with **interfaces**.
   - Created using `java.lang.reflect.Proxy`.
   - Example: A `MyService` interface with a `MyServiceImpl` implementation.

2. **CGLIB Proxy**
   - Works with **classes** (no interface required).
   - Uses subclassing to create a proxy that extends the target class.
   - Requires a default constructor.
   - Slower to create than JDK proxies but faster during method invocation.

---

### How Proxies Work
1. A method call is made on the proxy object.
2. The proxy evaluates:
   - If the target object is available.
   - If not (e.g., in **lazy initialization**), it creates or retrieves the target.
3. The proxy applies additional behavior (e.g., logging, transactions, security).
4. The call is forwarded to the target object.

---

### Proxies and Lazy Loading
In Hibernate/JPA:
- Lazy-loaded entities are represented by proxies.
- The proxy holds only the entity's identifier (ID).
- Accessing a non-initialized property triggers a database query to load the data.
- **Bypassing lazy proxies**:
  - Using explicit JPQL/SQL with selected fields (projections) returns raw data instead of proxies.

---

## 2️⃣ Aspect-Oriented Programming (AOP)

### What is AOP?
AOP separates **cross-cutting concerns** (e.g., logging, security, transactions) from the main business logic. Instead of duplicating code (e.g., logging in every method), you define it **once** in an **Aspect**, and Spring applies it wherever it matches.

---

### Core AOP Concepts

| Term              | Description                                                                 |
|-------------------|-----------------------------------------------------------------------------|
| **Aspect**        | A class containing cross-cutting logic (e.g., a logging aspect).            |
| **Advice**        | The action taken at a specific join point (e.g., "log before method call"). |
| **Join Point**    | A point in program execution (e.g., method call, exception thrown).         |
| **Pointcut**      | An expression selecting specific join points (e.g., "all methods in `service` package"). |
| **Target Object** | The object being proxied.                                                  |
| **Proxy**         | The wrapper object created by Spring AOP.                                   |
| **Weaving**       | Linking aspects with target objects (Spring AOP uses runtime weaving via proxies). |

---

### Advice Types in Spring AOP

#### 1. `@Before`
- Executes **before** the method.
- Cannot stop method execution unless it throws an exception.

```java
@Before("execution(* com.example.service.*.*(..))")
public void logBefore(JoinPoint jp) {
    System.out.println("Before method: " + jp.getSignature().getName());
}

#### 2. '@AfterReturning'

Executes after a method completes successfully.
Can access the method's return value.

@AfterReturning(pointcut = "execution(* com.example.service.*.*(..))", returning = "result")
public void logAfterReturning(Object result) {
    System.out.println("Method returned: " + result);
}
