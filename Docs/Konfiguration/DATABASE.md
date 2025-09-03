# 📦 DATABASE Setup

Dieses Verzeichnis enthält alles, was du brauchst, um deine MySQL-Datenbank für das PrimeDrive-Projekt mit Docker zu initialisieren, zu droppen, zu restoren und DeltaScripts sauber auszuführen. 🚀

---

## 📁 Struktur

```bash
DATABASE/
├── DeltaScripts/       # Alle SQL-Migrationen in numerischer Reihenfolge
│   └── 000.000.000.000.sql # init script schema_version
│   └── 000.000.000.001.sql # create tables or add default datasets, currently just a example
├── Deploy/             # Bash-Skripte für Setup, Restore etc.
│   ├── restore.sh
│   └── update.sh
├── Docker/             # Dockerfile &
│   └── Dockerfile
└── docker-compose.yml  # docker-compose für MySQL-Container
└── .env                # Konfiguration (Benutzername, Passwort, DB-Name etc.)
```

---

## ⚙️ Konfiguration

Erstelle eine `.env`-Datei mit folgendem Inhalt:

```env
DB_USER=root
DB_PASS=geheim
DB_NAME=PrimeDrive
DB_HOST=127.0.0.1
DB_PORT=3306
```

---

## 🐳 Docker starten

```bash
cd Database
docker compose up -d
```

→ Container mit MySQL läuft nun lokal erreichbar über `127.0.0.1:3306`

---

## 🔄 Datenbank resetten & wiederherstellen

```bash
cd DATABASE/Deploy
./restore.sh
```

→ Dropt und erstellt die Datenbank neu
→ Führt alle SQL-Dateien in `DeltaScripts/` sequenziell in einer Transaktion aus
→ Loggt jede Migration in `schema_version`-Tabelle

---

## 📜 DeltaScripts Format

Benennungsschema: `000.000.000.001.sql`
→ Nummerierung entscheidet über Ausführungsreihenfolge
→ Transaktionen sind empfohlen (`START TRANSACTION; ... COMMIT;`)
→ Jede ausgeführte Datei wird in `schema_version` geloggt

---

## 📁 Beispiel-Migration

```sql
-- 000.000.000.001.sql
START TRANSACTION;

CREATE TABLE PlattformNutzerkonto (
    id INT AUTO_INCREMENT PRIMARY KEY,
    benutzername VARCHAR(255) NOT NULL,
    passwort VARCHAR(255) NOT NULL,
    rolle VARCHAR(100),
    eMail VARCHAR(255) UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
) ENGINE=InnoDB;

COMMIT;
```

---

## 🪡 Tipps

- Verwende `INT` + `AUTO_INCREMENT` für IDs
- Trenne jede Änderung in eine eigene Datei
- `DROP PROCEDURE ...` bei temporären SQL-Prozeduren nicht vergessen
- Nutze keine `DELIMITER` – Transaktionen reichen meist

---

🔥 Bei Fragen: frag den 🔧 Fatlum direkt 😎

---

## 🚀 Actions

Hier findest du die zentralen Aktionen und Verknüpfungen rund um das Projekt.

[➡️ Zum README](../../README.md#️-database-dokumentation--setup)
