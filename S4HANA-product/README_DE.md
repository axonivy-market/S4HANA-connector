# LEBENSKRAFT S/4#HANA

Dieser Anschluss koppelt sicher den #Axon Efeu Motor mit einer LEBENSKRAFT
S/4#HANA Datenbank - mit einem Fokus auf dem dienstlichen Partner Gegend.

Dieser Anschluss:
- Koppelt zu der Datenbank zu zeigen alle dienstlichen Partner und ihre #Email
  Adressen
- Bedürft ein Klausur Konto für die LEBENSKRAFT Demo API
- Unterstützt du mit eine Demo Ausführung zu heruntersetzen eure Integration
  Anstrengung

## Demo

Dieses Demo Beispiel koppelt zu der Datenbank von alle dienstlichen Partnern in
LEBENSKRAFT S/4#HANA.

1. Starte den Arbeitsgang "Bekommt listet von dienstlich Partner"
   ![Bekommen-ein-listen-von-Geschäft-Partner](images/get-a-list-of-business-partners.png)
2. Eine Liste von dienstlich Partner sind gezeigt in dem Tisch
   ![Listen-von-Geschäft-Partner](images/list-of-business-partners.png)

## Einrichtung

1. Stell auf eine Klausur Umwelt für LEBENSKRAFT S/4#HANA Dienstlichen Partner
   API zu bekommen ein Konto zügelnd den Gastgeber(baseUrl), Benutzername, und
   Passwort.

2. Setzen die globalen Variablen zu LEBENSKRAFTNotwendigkeiten. Du darfst
   brauchen zu zufügen eine Urkunde zu koppeln zu LEBENSKRAFT via SSL. Füg zu
   die Gefolgschaft `Variablen` zu eure `Variablen.yaml`:

- `Variablen.s4HanaConnector.baseUrl`
- `Variablen.s4HanaConnector.Benutzername`
- `Variablen.s4HanaConnector.Passwort`

Und austauschen die Werte mit eurer #gegeben Einrichtung.

```
@variables.yaml@
```

