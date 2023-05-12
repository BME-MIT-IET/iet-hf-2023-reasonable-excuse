# operativ_tarsulat

## Logolás használata:
Minden függvény elején:<br />
```java
Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),"Elsõ argumentum értéke","Második argumentum értéke","...");
```
Minden függvény végén ha nincs visszatérési érték (pont a függvény vége elõtt):
```java
Skeleton.LogReturn();
```
Ha van visszatérési érték (pont a return elõtt):
```java
Skeleton.LogReturn(visszateroValtozo.toString()); // Vagy a visszatérés értékét szöveg formában mááshogy megadva
```
Példa:
```java
///
///	For testing purposes, adds a virologist to the list
///
public void AddVirologist(Virologist v) {
	Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),v.getName() /* Argumentum értékei */);
	virologists.add(v);
	Skeleton.LogReturn(); /* Nincs visszatérési érték */
}
```