DECK

Scelte personali:
* struttura deck come stack
* metodo shuffle di libreria Collections
* utilizzo Optional nel metodo draw (o forse no per complessità di utilizzo in altri metodi)
* costanti definite nelle classi di appartenenza per garantire coerenza e incapsulamento
Struttura dati:
* Stack: permette di modellare al meglio il comportamento di un mazzo di carte

Metodi:
* Collections.shuffle

Pattern utilizzati
* Factory: creazione del deck di carte


GAME LOGIC

Scelte personali:
* uso dell'operatore terziario per snelllire corpo dei metodi
* separazione dei compiti del metodo compareCards in sottometodi privati (principoio ???)


STATE

Pattern utilizzati
* State: creo una classe per ogni stato del gioco e le gestisco tramite la classe Context. Questo permette l'indipendenza dell'implementazione dei singoli stati e facilita l'aggiunta di eventuali nuovi stati o il loro riuso in altri giochi


PLAYER

Scelte personali:
* Poichè un player può essere o User o Cpu e, lavorando con un approccio modulare e favorendo una semplice ampliazione delle modalità/funzionalità, si è scelto di implementare player come classe astratta (in particolare il metodo astratto è chooseCard()) e le sue sottoclassi sono User e Cpu


CPU STRATEGY

Pattern utilizzati:
* Si utilizza il design Strategy per differenziare le varie difficoltà di logiche della Cpu per scegliere la carta da giocare nel turno (chooseCard).
* Per ridurre il codice si è utilizzato un'interfaccia funzionale CpuStrategy e un metodo statico con uso di lambda per ogni strategia. Link: https://www.baeldung.com/java-strategy-pattern



VIEW

Scelte personali:
* Uso di JavaFX (libreria esterna) ver. javafx-sdk-23.0.1
* Uso di jdk-21