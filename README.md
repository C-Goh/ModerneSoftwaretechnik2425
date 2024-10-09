# ModerneSoftwaretechnik2425

Handout für Git

Was ist Git und warum sollte es verwendet werden?
    o Grundlegende Git-Befehle (z. B. git init, git add, git commit, git push)

    o Git mit IntelliJ/PyCharm benutzen: Local Repository und Remote Repository
    o Nützliche Git-Tools und Plabormen (z. B. GitHub)


# Branches und ihre Nutzung, Umgang mit Merge-Konflikten
-----------------------------------------------------------------------------------------------------------------------------------
  
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
