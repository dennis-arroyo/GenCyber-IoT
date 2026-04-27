# GenCyber-IoT

A beginner-friendly **SQL Injection Demonstration Tool** built in Java + JavaFX, designed to introduce young students to cybersecurity concepts during a [GenCyber](https://www.gen-cyber.com/) summer camp.

This was one of my early personal projects. It's not polished, and there are plenty of things I would do differently today — but it served its real purpose: getting middle- and high-school students curious about how attackers think, and how a single line of insecure code can give them the keys to a database.

## What it does

The app pretends to be a simple login screen connecting to a MySQL database running on a Raspberry Pi. Students walk through three steps:

1. **Enter the Pi's IP address** — introduces the idea that devices on a network have addresses.
2. **Enter a username and password** — looks like an ordinary login form.
3. **See what happens** — when the username is typed normally, the app behaves as expected. When the username is replaced with a classic SQL injection payload (e.g. `' OR '1'='1`), the query returns data the user shouldn't have access to.

The "aha" moment lives in `src/logic/MySQLAccess.java`:

```java
String query = "SELECT * FROM Users WHERE User = '" + username + "'";
```

That single line of string concatenation is the entire vulnerability. Students see it, break it, and then we talk about prepared statements as the fix.

## Why I built it

I wanted something more hands-on than slides. Telling a 12-year-old "never trust user input" is forgettable. Letting them type `' OR '1'='1 --` into a login box and watch a database spill its contents is not.

The "IoT" in the name comes from the camp's framing — the database lived on a Raspberry Pi, which the students could physically see and touch. It made the abstract idea of "a server" tangible.

## Tech stack

- **Java** with **JavaFX** (FXML for the UI layouts)
- **MySQL** via JDBC (`mysql-connector.jar`)
- **IntelliJ IDEA** project structure

## Project layout

```
src/
├── gui/      JavaFX UI — Main, controllers, FXML scenes, images
├── logic/    Backend — MySQLAccess (DB connection + the vulnerable query)
└── images/   UI assets
```

Entry point: `src/gui/Main.java`.

## Running it

You'll need:

- JDK with JavaFX configured
- A MySQL server reachable from the machine running the app, with a `Test` database containing `Users` and `Country` tables
- The `mysql-connector` JAR on the classpath

Open the project in IntelliJ, point it at a JDK that includes JavaFX, and run `gui.Main`. The credentials hardcoded in `MySQLAccess.java` (`pi` / `raspberry`) match the camp's Raspberry Pi setup — change them for your environment.

## Disclaimer

This project intentionally contains an SQL injection vulnerability. **That is the point.** Do not use this code as a template for real software. If you're learning how to do this *correctly*, look up `PreparedStatement` in the JDBC docs — that's the lesson the kids walked away with, and it's the one that matters.

## License

Personal / educational use. Feel free to fork it for your own classroom.
