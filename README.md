# operativ_tarsulat

## Logol�s haszn�lata:
Minden f�ggv�ny elej�n:<br />
```java
Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),"Els� argumentum �rt�ke","M�sodik argumentum �rt�ke","...");
```
Minden f�ggv�ny v�g�n ha nincs visszat�r�si �rt�k (pont a f�ggv�ny v�ge el�tt):
```java
Skeleton.LogReturn();
```
Ha van visszat�r�si �rt�k (pont a return el�tt):
```java
Skeleton.LogReturn(visszateroValtozo.toString()); // Vagy a visszat�r�s �rt�k�t sz�veg form�ban m��shogy megadva
```
P�lda:
```java
///
///	For testing purposes, adds a virologist to the list
///
public void AddVirologist(Virologist v) {
	Skeleton.LogFunctionCall(new Object() {}.getClass().getEnclosingMethod().getName(),v.getName() /* Argumentum �rt�kei */);
	virologists.add(v);
	Skeleton.LogReturn(); /* Nincs visszat�r�si �rt�k */
}
```