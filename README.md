# CI/CD-Pipline
Unsere Pipline, definiert in .github/workflows/maven.yml, besteht aus folgenden Schritten

**Lint Code-Analyse**

Zunächst wird unser auf Code mit der Dependency "Checkstyles" analysiert. Die Regeln, worauf unser Code analysiert werden soll, wurden vorher in softwaretechnik24/checkstyle.xml definiert

```
    - name: Checkstyle Check
      working-directory: ./softwaretechnik24
      run: mvn checkstyle:check 
```

**Vorbereiten der Testumgebung**

Sollten keine Codefehler zu finden sein, bereiten wir unsere Umgebung für die Tests vor. Dieser Schritt ist erforderlich, da wir neben einfachen jUnit Tests auch UI-Tests besitzen. Hierzu ist es notwendig, dass die Umgebung, in der die Github-Action, läuft eine Desktop-Umgebung installiert hat.

```
    - name: Install Xvfb
      run: sudo apt-get install -y xvfb

    - name: Start Xvfb
      run: Xvfb :99 &

    - name: Set DISPLAY environment variable
      run: echo "DISPLAY=:99" >> $GITHUB_ENV
```

**Testen und Bauen des Projektes**

Im nächsten Schritt wird das Projekt zunächst getestet und gebaut.

```
    - name: Build with Maven
      run: mvn -B package --file ./softwaretechnik24/pom.xml

```
Hier bei werden alle Tests aus dem Ordner softwaretechnik24/src/test/java/softwaretechnik verwendet. Zu diesem Zeitpunkt haben wir drei Tests:
```
AppTest.java
Platzhalter Test, um das JavaFX Framework zu testen
```
```
AppUiTest.java
Ein einfacher UI-Test - hier wird nur überprüf ob die Anwendung ein Fenster mit dem vorgegebenen Namen öffnet.
```
```
UserTest.java
Test der User-Domäne

@VorJedemTest
wird die Test-Datenbank neu eingelegt

@NachJedemTest
wird die Test-Datenbank gelöscht, sodass die Tests sich gegenseitig nicht beeinflussen

@Test CreateUser()
Testet das Erstellen eines Nutzers

@Test FailCreateUser()
Zu erwartend Fehlschlagender Test. Es soll nicht möglich sein, dass man einen User mit Sonderzeichen im Namen erstellt.

@Test CreateAndDeleteUser()
Testet das erfolgreiche Erstellen und danach das Löschen eines Users

@Test CreateAndEditUser()
Testet das erfolgreiche Erstellen und danach das Bearbeiten eines USers

@Test FailCreateDuplicateUser()
Testet, ob das Erstellen eines Users mit einem bereits vorhanden User Namen fehlschlägt
```

** Erstellen einer Dokumentation und eines Dependency-Graphen**

Im nächsten Schritt wird automatisiert eine Dokumentation erstellt
```
    - name: Generate Javadoc
      run: mvn javadoc:javadoc --file ./softwaretechnik24/pom.xml
```

Danach ein Dependency-Graphen

```
    - name: Update dependency graph
      run: mvn com.github.ferstl:depgraph-maven-plugin:4.0.1:graph --file ./softwaretechnik24/pom.xml
```

** Hochladen der Ergebnisse um sie abzurufen **
Zum Schluss wird die generierte Dokumentation und die Testergebnisse hochgeladen

```
    - name: Upload Javadoc
      uses: actions/upload-artifact@v3
      with:
        name: javadoc
        path: target/site/apidocs/

    - name: Generate JaCoCo report
      run: mvn jacoco:report --file ./softwaretechnik24/pom.xml

    - name: Upload JaCoCo report
      uses: actions/upload-artifact@v3
      with:
        name: jacoco-report
        path: target/site/jacoco/
```


# Handout für Git

## Was ist Git und warum sollte es verwendet werden?
***  

Git ist ein verteiltes Versionskontrollsystem, das es Entwicklern ermöglicht, Änderungen im Codeverlauf zu verfolgen und zusammen an Projekten zu arbeiten. Die freie Software wird verwendet, um den Entwicklungsprozess übersichtlich zu halten, Änderungen rückgängig zu machen und gleichzeitig eine kollaborative Arbeitsweise zu ermöglichen.


## Grundlegende Git-Befehle:
***

* **git init**: Legt ein neues Git-Repository im aktuellen Verzeichnis an 
* **git add**: Fügt geänderte Dateien zur Staging-Area (Zwischenspeicher) hinzu, um sie für den nächsten Commit  vorzubereiten
* **git commit**: Speichert die Änderungen in der Historie des Repositories mit einer kurzen Beschreibung
* **git push**: Überträgt die lokalen Commits zu einem entfernten Repository, wie z.B. GitHub


## Branches und ihre Nutzung, Umgang mit Merge-Konflikten
***
  
    1. Was sind Git-Branches?
        Definition: Separate Entwicklungslinien in einem Git-Repository
        Zweck: Entwicklung neuer Features, Bugfixes oder Experimente ohne Beeinträchtigung des Hauptzweigs (z. B. main)

    2. Nutzung von Branches
        Erstellen: git branch <branch-name>
        Wechseln: git checkout <branch-name> oder git switch <branch-name>
        Zusammenführen: git merge <branch-name>
        Löschen: git branch -d <branch-name>

    3. Umgang mit Merge-Konflikten
        Konflikt erkennen: Git zeigt betroffene Dateien an
        Dateien prüfen: Suchen nach Konfliktmarkierungen (<<<<<<, ======, >>>>>>)
        Konflikt lösen: Änderungen auswählen und Konfliktmarkierungen entfernen
        Änderungen hinzufügen: git add <dateiname>
        Merge abschließen: git commit

    4. Best Practices
        Regelmäßige Updates: Halte deinen Branch aktuell mit dem Hauptbranch
        Kurze Branch-Lebensdauer: Reduziere Komplexität und Konflikte
        Code-Reviews: Nutze Pull-Requests für Reviews vor dem Merge


 ## Git mit IntelliJ/PyCharm benutzen: Local Repository und Remote Repository
***
  
Es gibt zwei Arten von Repositories:
* **Lokales Repository:** Speichert den Code und alle Versionen auf dem lokalen Rechner.
* **Remote Repository:** Speichert den Code auf einem entfernten Server (z.B. GitHub, Gitlab), was eine Zusammenarbeit ermöglicht.


*IntelliJ* und *PyCharm* bieten integrierte Unterstützung für Git. Es kann die Versionskontrolle direkt in der IDE genutzt werden. Ein lokales Repository wird verwendet, um Änderungen am Code auf dem eignen Rechner nachzuverfolgen. Das Remote Repository dient dazu, den Code online zu speichern und ihn mit anderen Entwicklern zu teilen.


 ## Nützliche Git-Tools und Plattformen (z.B. GitHub)
***

Es gibt verschiedene Git-Tools und Plattformen, welche die Arbeit mit Git erleichtern. Im folgenden werden beispielhaft einige genannt:

1. **GitHub**: Plattform zur Verwaltung von Git-Repositories mit Funktionen wie Pull Requests, Issue Tracking, CI/CD-Integration und GitHub Pages für statische Websites.
2. **GitLab**: Ahnlich die GibHub, bietet jedoch Self-Hosting-Optionen, integrierte CI/CD-Pipelines und detailleirte Projektmanagement-Tools.
3. **Bitbucket**: Git-Plattfrom, die sich mit Atlassian-Produkten wie Jira integriert.
4. **Sourcetree und GitKraken**: Grafische Git-Clients, die eine benutzerfreundliche Oberfläche zur Verwaltung von Branchen, Commits und Merges bieten.


Erstellt von:
* Was ist Git und warum sollte es verwendet werden? (Pia Wöhl)
* Grundlegende Git-Befehle (Pia Wöhl)
* Branches und ihre Nutzung, Umgang mit Merge-Konflikten (Nina Gierke)
* Git mit IntelliJ/PyCharm benutzen: Local Repository und Remote Repository (Lina Florin)
* Nützliche Git-Tools und Plattformen (z.B. GitHub) (Lina Florin)
