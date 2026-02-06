1. Per què s’atura l’execució al cap d’un temps?

Perquè quan no hi ha places disponibles, els assistents que volen fer una reserva entren en estat d’espera amb wait().
Aquests fils queden bloquejats fins que un altre assistent cancel·la una reserva i es fa notifyAll().

2. Què passaria si la probabilitat fora 70% reservar – 30% cancel·lar? I al revés?

70% reservar – 30% cancel·lar
Les places s’omplen ràpidament i molts fils queden en wait() esperant que s’allibere alguna plaça.

boolean ferReserva = rnd.nextInt(100) < 70;


30% reservar – 70% cancel·lar
Hi ha moltes cancel·lacions inexistents i gairebé sempre hi ha places disponibles, per tant hi ha poques esperes.

boolean ferReserva = rnd.nextInt(100) < 30;

3. Per què cal una llista i no només una variable sencera?

Perquè la llista permet saber quin assistent té una reserva.
Amb només una variable sencera no es podrien controlar duplicats ni cancel·lacions inexistents.