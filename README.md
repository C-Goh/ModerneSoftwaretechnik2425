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
