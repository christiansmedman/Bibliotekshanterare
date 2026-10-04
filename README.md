# Bibliotekshanterare
Assignment 1 Java programming JUV26D


REFLEKTION:
En av mina mål var att hålla "main" klassen i "Bibliotekshanteraren" så clean som möjligt, därav gjorde jag en metod för varje case i switch loopen (även printMenu för utskriften av meny).
Alla metoder i Libray.java är mer en "behind the scenes" struktur som hanterar felinmatning och hantering om arrayns kapacitet överskrids (copyOf för att förstora arrayn).
Jag valde att ge varje member och book ett ID som assignas vid registrering för att enklare kunna låna och returnera böckerna.





KÄLLKRITIK:

Jag tänkte göra Book till en record först, men läste nånstans att det kan störa till det efter att värdet ändras vid utlåning i systemet? Då den hade vart immutable så kan inte värdet ändras, annars hade en ny record 
skapats med det nya värdet efter varje utlång eller inlämning? Så att Book som class var smidigare. Kan vara så att jag uppfattat detta fel men var iallafall därför jag gjorde Member till record istället för Book! 


Känns också som att det blev väldigt mycket kod för ett lite simplare program? Vill gärna höra om jag kan korta ner programmet så det ser renare ut!

EDIT: Läste nu efter jag skickade in att en del av uppgiften var att Book skulle vara en Record... så that's my bad att jag inte läste instruktionerna nogrannare.
