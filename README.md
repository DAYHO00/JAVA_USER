# Java Language Programming

- Java Language Programming 수업에서 수행한 총 3개의 과제를 정리한 저장소입니다.  
- 기본 문법부터 GUI, 스레드, 객체지향 설계, 예외 처리까지 단계적으로 구현했습니다.

---

## 📚 목차

- [기술 스택](#-기술-스택)
- [Homework 1: Java Basics](#-homework-1-java-basics)
- [Homework 2: Calculator & Multithreading](#-homework-2-calculator--multithreading)
- [Homework 3: OOP & Exception Design](#-homework-3-oop--exception-design)

---

## 🛠 기술 스택

- **Language**: Java
- **GUI**: AWT
- **Concepts**:
  - Control Flow, Loop
  - String / Random
  - Scanner I/O
  - GUI Event Handling
  - Multithreading
  - OOP (Inheritance, Encapsulation)
  - Custom Exception

---

## 🧩 Homework 1: Java Basics

Java 기본 문법과 제어 구조를 활용한 5문제 구현 과제입니다.

### ✔ Problem 구성
1. **Recurrence Sum**
   - 점화식 수열 계산
   - bitwise 연산만 사용하여 구현
2. **Hourglass Shape**
   - `*` 과 공백으로 대칭 모래시계 출력
3. **Random Hit / Miss**
   - 난수 인덱스로 문자열 탐색
4. **String Basic Ops**
   - 문자열 길이, 첫 글자, 마지막 글자 출력
5. **Bank Deposit / Withdrawal**
   - Scanner 기반 입출금 메뉴 시스템
   - 잔액 부족 처리

### ✔ 핵심 포인트
- 반복문 필수 사용
- 조건문 / 문자열 처리
- Scanner 기반 사용자 입력

---

## 🧮 Homework 2: Calculator & Multithreading

GUI 및 스레드 기반 응용 프로그램 구현 과제입니다.

### ✔ 과제 1: Scientific Calculator
- AWT 기반 계산기 구현
- 사칙연산, 거듭제곱, 로그, 팩토리얼, 제곱근 지원
- 괄호 연산 구현 (Extra Point)
- **RPN(Reverse Polish Notation)** 기반 수식 계산

### ✔ 과제 2: Ball Thread Simulation
- 여러 개의 공을 스레드로 동시 실행
- 충돌 감지 및 분열 처리
- Canvas + Thread 기반 애니메이션

### ✔ 핵심 포인트
- `ActionListener` 이벤트 처리
- 스레드 동기 실행
- GUI + 로직 분리

---

## 🚗 Homework 3: OOP & Exception Design

객체지향 설계와 커스텀 예외 처리를 중심으로 한 과제입니다.

### ✔ Q1: Vehicle Management System
- `Vehicle` 기반 상속 구조 (`Car`, `Motorcycle`)
- 캡슐화 + `toString()` 오버라이딩
- 사용자 정의 예외:
  - InvalidVehicleDetailException
  - DuplicateVehicleException
  - VehicleNotFoundException
- `VehicleManager`로 추가 / 삭제 / 검색 관리

### ✔ Q2: Bank Account Management System
- `BankAccount` 기반 상속 구조
  - `SavingsAccount`
  - `CheckingAccount`
- 입출금 로직 캡슐화
- 초과 출금 / 중복 계좌 예외 처리
- `BankManager`로 계좌 관리

### ✔ 핵심 포인트
- Inheritance & Polymorphism
- Exception 기반 안정성 확보
- 자료구조(List, Map) 활용

---
