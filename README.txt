321CD Cotelin Maria-Diana
~~~~~~~~~~~~~~~~~~~~~~~~~~~~ VideosDB~~~~~~~~~~~~~~~~~~~~~~~~~

Am refacut clasele din fileio in myclasses si am adaugat o clasa noua Database pe care
o folosesc la queries si recommendations. 
In main verific fiecare actiune data ca input si in functie de ce este apelez metoda 
corespunzatoare.

	> Commands
	Am facut o clasa care contine cate o metoda pentru fiecare comanda.
	In main am creat un obiect command cu actorii, filmele, serialele si
	utilizatorii.

	o Favorite
	Parcurg toti ultilizatorii pana gasesc utilizatorul din input, verific apoi
	daca video-ul cautat se afla in istoric, daca da il adaug intr-o lista de 
	filme favorite.

	o View
	Parcurg utilizatorii si daca il gasesc pe cel din input verific daca a vazut
	filmul dat, daca da maresc numarul de vizualizari din istoric, altfel adaug 
	1 la filmul respectiv in istoric.

	o Rating
	Caut utilizatorul din input si verific daca a vazut video-ul din input, daca da
	parcurg filmele si apoi serialele pana gasesc video-ul la care a dat rating. Am
	adaugat un HashMap la user in care retin filmele si serialele (si sezoanele)
	la care a dat rating. Daca filmul se afla in lista de filme rated ale utilizatorului
	afisez eroare, altfel il adaug si afisez mesajul. La seriale parcurg si sezoanele 
	pana il gasesc pe cel care primeste rating.
	La fiecare sezon am adaugat si o lista cu toti utilizatorii care au dat rate.
	Astfel, daca sezonul cautat are deja in lista RatedByUsers utilizatorul se
	afiseaza eroare, in caz contrar se adauga utilizatorul in lista, se adauga si
	ratingul si in lista utilizatorului de seriale la care a dat rate se adauga serialul.
	getRatedSerials este un HashMap cu numele serialelor si de cate ori s-a dat rate,
	adica la cate sezoane a dat utilizatorul rating, de aici noGrades.

	>Query
	Acest task s-a rezolvat in Database impreuna cu cel de la recomandari.
	Fiecare Query are aceleasi functii de sortare si acelasi mod de afisare (for
	pentru HashMap). In main am creat un Database cu actorii, filmele si serialele.

	Actor
	o Average
	Verific in care filme a jucat actorul si adaug la rating ratingul filmului si
	contorizez cate filme au fost.
	Acelasi lucru si la seriale.
	Filmele si serialele au metode in care se calculeaza ratingul, la seriale
	diferenta este ca si fiecare sezon are ratingul calculat.
	La final adaug intr-un HashMap toti actorii impreuna cu ratingurile lor.
	Sortez HashMap-ul cu ajutorul unui LinkedHashMap in functie de criteriu, 
	am creat cate o functie pentru sortare ascendenta si una pentru sortare 
	descendenta. (sortByValue1 si sortByValue2)
	Parcurg actorii sortati si ii pun in stringul pe care vreau sa il afisez, folosesc
	count pentru a nu pune virgule la final cand nu este nevoie. 
	
	o Awards
	Parcurg actorii si verific daca fiecare are premiile din lista de premii din input.
	Daca da ii pun intr-un HashMap impreuna cu numarul de premii. Am adaugat 
	la clasa Actor o metoda care calculeaza numarul de premii (totalAwards).
	I-am sortat si afisat.

	o Filter Description
	Am folosit clasa Pattern pentru a verifica daca exista cuvintele din input in 
	descrierile actorilor. Daca da, am adaugat toti actorii intr-un HashMap cu 
	value 1 pentru fiecare. Am sortat HashMapul (se va folosi ca departajare
	numele) si apoi am afisat actorii.

	Video
	Am retinut genul si anul video-urilor cautate la fiecare query.

	o Rating
	Folosesc aceeasi metoda si pentru filme si pentru seriale, verificand ulterior
	ce am primit ca input la ObjectType. Folosesc un HashMap in care pun
	toate video-urile care contin anul si genul specificat, avand ca value ratingul.
	Sortez si afisez.

	o Favorite
	Asemanator cu rating, doar ca verific in plus cati utilizatori au video-ul la
	favorite si fac un HashMap cu numele si numarul de adaugari la favorite.
	Sortez si afisez.

	o Longest
	Acelasi lucru, doar ca pun durata video-ului in HashMap.
	Sortez si afisez.

	o Most viewed
	La fel, doar ca parcurg si toti utilizatorii care au vazut video-ul si adaug la
	views de cate ori l-au vazut. Ii pun in HashMap impreuna cu views, sortez
	si afisez.

	User
	o Number of ratings
	Folosesc getRatedSerials si getRatedMovies pe care le-am incrementat la 
	Commands->Rating. Adaug utilizatorii in HashMap impreuna cu numarul de
	video-uri la care au dat rate.

	> Recommendations

	o Standard
	Parcurg filmele si serialele si retin primul video care nu a fost vazut de 
	utilizator si il afisez.

	o Best unseen
	Folosesc variabila rating pentru a compara ratingurile de la toate video-urile
	nevazute de utilizator. De fiecare data cand dau de un video care nu a fost
	vazut verific daca are ratingul mai mare decat rating, daca da il retin si
	modific variabila rating.
	La final il afisez.

	o Popular
	Verific daca utilizatorul este Premium si retin in variabila verif acest lucru,
	Parcurg toate filmele si adun numarul de vizualizari de la fiecare utilizator.
	Parcurg toate genurile filmului si adaug intr-un HashMap genul si numarul de
	vizualizari. La fel si cu serialele. Sortez HashMapul si parcurg utilizatorii pana
	il gasesc pe cel din input. Parcurg video-urile in ordine si verific daca contin
	genul cel mai vizionat. Daca da retin numele si la final afisez.
	
	o Favorite
	Am refacut functia de sortare ca sa aiba al doilea criteriu ordinea aparitiei.
	Acelasi lucru ca la Popular, dar verific daca videoul apare in lista de videouri
	favorite ale utilizatorului. Pun in HashMap numele filmului si de cate ori apare.
	Sortez si verific daca video-ul curent apare in HashMap, daca da il afisez.
	
	o Search 
	Parcurg filmele si serialele, daca contin genul din input le pun in HashMap 
	impreuna cu ratingul. Le sortez si afisez.
Probleme intampinate:
	Cerintele au fost putin dificile, nu neaparat rezolvarea, dar mie personal
        mi-a luat putin mai mult sa imi dau seama exact ce trebuie sa fac si 
        unde sa scriu cod.
	Au fost cateva teste care nu afisau nimic si nu prea iti dadeai seama daca ce
        ai scris e corect sau nu ca primeai puncte pe ele. La un task mi-am dat seama ca
        il fac gresit la testul 9 ca era singurul care nu trecea si a fost putin 
        incomod sa compar tot outputul cu ce era in ref.
	La sortarea HashMap-ului a fost putin dificil pentru ca cea mai usoara metoda
         era cu stream-uri care se fac prin ultimele laboratoare.
