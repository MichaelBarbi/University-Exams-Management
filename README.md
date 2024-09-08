UNIVERSITY EXAMS MANAGEMENT

A Java desktop application developed for the OOP university exam as part of the Computer Science degree at Unimore. 
The application allows you to manage the exams of different students, enabling you to perform CRUD operations on locally stored files related to exams, students, and subjects.

![Home](https://github.com/MichaelBarbi/University-Exams-Management/blob/master/readmeImages/home.png)

--------------------------------------------------------------------------------------------------

PREREQUISITES (ITA)

The file with the instructions is also present in the project structure.



REGOLE PER LO SVOLGIMENTO

Il progetto deve essere svolto usando il linguaggio Java e possedere i seguenti requisiti implementativi:

  • Essere dotato di interfaccia grafica tramite cui interagire con il programma stesso.
  
  • Sfruttare i meccanismi di incapsulamento, ereditarietà e polimorfismo.
  
  • Per l’ereditarietà è possibile sfruttare classi astratte e interfacce; si considerano escluse le relazioni di ereditarietà diretta da classi di libreria Java.
  
  • Sfruttare le classi di sistema Java per la gestione dell'input/output.
  
  • Utilizzare le strutture dati di libreria e i generics, motivando le scelte fatte.
  
  • Dividere in modo coerente le classi in package.
  
  • Il programma deve essere eseguibile da linea di comando.
  
  
Il software deve essere accompagnato da pagine di documentazione HTML (ad esempio le pagine generate
tramite Javadoc o altri strumenti come Doxygen) che descrivano le scelte di progetto effettuate e la struttura
del sistema software.

Nel seguito del testo, i paragrafi evidenziati in azzurro sono facoltativi, e servono per differenziare il voto.
Lo svolgimento della parte obbligatoria contribuisce al voto per 25 punti. Il contributo delle parti facoltative
è riportato nelle rispettive descrizioni. Si noti che il punteggio massimo rimane comunque 30/30.



DESCRIZIONE DEL PROGETTO

Questo progetto mira all’implementazione di uno strumento per la gestione statistica degli esami
universitari. In particolare, ci si pone lo scopo di fornire ad un ipotetico docente un modo rapido ed intuitivo
per raccogliere e gestire esami universitari di diverso tipo tenendo quindi sotto controllo le carriere
accademiche degli studenti.

Il presente progetto si propone di descrivere e sviluppare una applicazione Java che abbia le seguenti
funzionalità (dettagliate nei paragrafi successivi):

• Gestione degli esami;

• Salvataggio e caricamento degli esami inseriti;

• Visualizzazione dati statistici

• Stampa della tabella degli esami.


GESTIONE DEGLI ESAMI

La gestione degli esami prevede la visualizzazione in forma tabellare delle prove d’esame sostenute dagli
studenti. In particolare, ciascuna entry nella tabella deve avere le seguenti informazioni:

• Nome e Cognome dello studente

• Nome dell’insegnamento

• Voto finale (nel range [18 -30])

• Lode (Sì/No)

• Numero crediti

Ogni voce rappresenta quindi una prova d’esame di uno studente.

L’utente deve avere la possibilità di aggiungere, modificare e cancellare le voci della tabella.

Quando l’utente aggiunge l’esito di un esame all’interno della tabella, l’applicazione deve proporre la scelta
tra due diverse tipologie di esame. Semplice o Composto.

Un esame si dice semplice se il suo voto finale è determinato da un’unica prova. Diversamente un esame è
composto se il voto finale è determinato dall’esito di almeno due prove intermedie. Più nel dettaglio, il voto
finale di un esame composto è la media pesata dei voti inseriti per le prove intermedie che lo compongono.
Quindi, in caso di scelta di inserimento di un esame composto, l’utente anziché inserire direttamente il voto
finale, deve specificare di quante prove è composto ed il peso di ciascuna. Il peso deve essere visto come una
percentuale e le prove intermedie non prevedono lodi.

Per implementare questa caratteristica e le funzionalità collegate si suggerisce di utilizzare il polimorfismo
in Java.

Se l’utente clicca su una riga della tabella che contiene una prova d’esame composta, l’applicazione deve
poter mostrare l’esito e i pesi delle prove intermedie.

Tramite un meccanismo di filtro sulla tabella, l’utente può decidere se visualizzare tutte le informazioni
inserite (nessun filtro), oppure filtrare per studente o per singolo insegnamento.
Per queste operazioni, si può anche utilizzare un campo di testo editabile la cui stringa inserita viene
comparata alle entry della tabella.
A seconda del filtro scelto, il form contenente la tabella deve mostrare all’utente la media pesata dei voti
finali sostenuti nelle prove d’esame. Si trascuri l’effetto delle lodi.

Si predispongano appropriati controlli per garantire la correttezza dei dati inseriti.

[Facoltativo 2 punti: opzionalmente, si cerchi di implementare l’opportuna strategia in grado di evitare
situazioni in cui “Programmazione ad Oggetti” e “Programmazione Ad Oggetti” siano viste dal sistema come
due insegnamenti separati].


SALVATAGGIO E CARICAMENTO DEGLI ESAMI INSERITI

L’utente deve avere la possibilità di salvare la tabella su un file, specificandone il nome, e di ricaricare il file
specificando il nome del file salvato in precedenza.

[Facoltativo 2 punti: nel caso in cui si tenti di salvare l’elenco degli esami in un file che esiste già, deve essere
chiesto all’utente se desidera sovrascrivere il file esistente].

[Facoltativo 5 punti: si implementi un meccanismo di salvataggio automatico basato su un thread che
periodicamente salva le informazioni in un file temporaneo.]

[Facoltativo 5 punti: il sistema deve tenere traccia del fatto che l’ultima modifica sulla tabella da parte
dell’utente sia stata salvata o meno. In questo modo, intercettando l’evento di chiusura del frame principale
il sistema può avvertire l’utente che vi sono modifiche non salvate e di conseguenza chiedere se operare o
meno il salvataggio su disco].


VISUALIZZAZIONE DATI STATISTICI

L’utente deve avere la possibilità di visualizzare in un grafico le statistiche inerenti alle prove sostenute. Più
nel dettaglio, una volta che l’utente ha scelto di filtrare per studente o per insegnamento, l’interfaccia grafica
deve mostrare un pulsante che se premuto visualizza un istogramma di frequenze relativo ai voti.
Stampa della tabella degli esami

[Facoltativo 1 punto: Si dia all’utente la possibilità di stampare la tabella. Si sfruttino le classi di libreria Java
per stampare tramite una delle stampanti configurate dal sistema operativo].

--------------------------------------------------------------------------------------------------

USER GUIDE

EXAMS

In the exams section, the user can perform various actions on exams:

• Add a new exam: The user can choose to add either a new simple exam or a new complex exam. 
or the first type, you need to specify the student's matriculation number, the subject, the grade, and, if applicable, the honors. 
For the second type, the exam is composed of multiple sub-exams. Therefore, you need to specify two or more sub-exams before continuing.

• Remove an exam

• Modify an exam

• Save the new list of exams to a file

• Filter the existing exams by subject name or student matriculation number

![Exams section](https://github.com/MichaelBarbi/University-Exams-Management/blob/master/readmeImages/exams.png) ![Simple exam form](https://github.com/MichaelBarbi/University-Exams-Management/blob/master/readmeImages/simpleExam.png) 
![Complex exam form](https://github.com/MichaelBarbi/University-Exams-Management/blob/master/readmeImages/complexExam.png)

CONFIGURATION

In the configuration section, the user can insert new students and new subjects into the local file-based database. 
For a new student, the matriculation number, first name, and last name are required. For the subject, the title and the number of CFU (ECTS credits - European Credit Transfer and Accumulation System) are required. 
Every action carried out in this section is automatically saved to the file, so you don't need to save manually.

![Students form](https://github.com/MichaelBarbi/University-Exams-Management/blob/master/readmeImages/students.png)
![Subjects form](https://github.com/MichaelBarbi/University-Exams-Management/blob/master/readmeImages/subjects.png)

STATISTICS

In the statistics section, based on the category you select (either subject or student), you will see a histogram of the current exam marks saved in the file.

![Statistics section](https://github.com/MichaelBarbi/University-Exams-Management/blob/master/readmeImages/stats.png)

FILE

In the file section, you can save the current list of exams to your file system for future import with the purpose of modifying it. You can also print the list of exams, create a new file and enable/disable automatic saving.

![File Section](https://github.com/MichaelBarbi/University-Exams-Management/blob/master/readmeImages/file.png)
