# Ulid
Ulid is a java implementation of the universally unique lexicographically sortable identifier standard specification as stated in this document: https://github.com/ulid/javascript/blob/master/README.md

It provides a simple API with one static method `get` for generating a new ULID string. It also provides a constructor where you can provided the seed value for`SecureRandom` variable used to generate the random part of the ULID string.

## Usage
For a fast usage of the library without instantiating new variables use the `get` method like so:
```java
long time = System.currentMillis();
String ulidString = Ulid.get(time);
System.out.println("The generated ulid string is %s".formatted(ulidString));

```

if you want to control the `SecureRandom` variable seed value, you can create a new object instance of a `Ulid` class passing in your seed value to the constructor. An example usage can be found below:

```java

byte[] seed = new byte[]{113,12,18,98,13};
Ulid ulid = new Ulid(seed);
long time = System.currentMillis();
String ulidString = ulid.generate(time);

System.out.println("The generated ulid string is %s".formatted(ulidString));
```