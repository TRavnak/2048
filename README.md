# rri-board-game-TRavnak
rri-board-game-TRavnak created by GitHub Classroom

## Osnutek načrta
Naredil bom igro 2048. Za igro sem se odločil, ker igro zelo dobro poznam (sem jo veliko igral)
Ohranil bom približno enak zgled igre kot je na že narejeni igri

### Dinamika igre
Premikanje števil (ploščic) po igralni površini - z ukazom premik se premaknejo vse ploščice
Združevanje ploščic Če skupaj padeta 2 ploščici z enako vrednostjo, se združita v eno ploščico z vrednostjo vsot teh dveh ploščic
Generiranje nove ploščice - po vsakem premiku se generira nova ploščica (na praznem mestu). Generira se vrednost 2 ali 4

### Mehanika igre
Premik v 4 smeri (gor, dol, levo, desno) - premik s smernimi tipkami (na telefonu z zdrsom po zaslonu)
Začetek nove igre

### Elementi igre
Stanje igre - trenutni rezultat in najboljši rezultat (HUD)
Gumb za novo igro
Polje 4x4 s ploščicami

## Logika igre
Stanja v igri bom opisal s EnumSet. 
Zaenkrat bo v igri maksimalna vrednost 2048 (v prihodnosti bo mogoče višja)
Primer stanja celice:
  CELL_EMPTY
  CELL_2
  CELL_4
  ...
  CELL_2048
