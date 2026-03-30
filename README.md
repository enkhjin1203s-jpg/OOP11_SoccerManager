# Soccer Manager App

An Android application for managing soccer teams, players and matches — built to demonstrate **Generics**, **Iterators**, and **Lambda Expressions** in Java.

---

## Project Structure

```
com.example.soccermanager
│
├── model/
│   ├── SoccerEntity.java        ← Interface: getId(), getName()
│   ├── Team.java                ← Implements SoccerEntity
│   ├── Player.java              ← Implements SoccerEntity
│   └── Match.java               ← Implements SoccerEntity
│
├── iterator/
│   ├── CustomIterator.java      ← Generic iterator interface: hasNext(), next()
│   └── TeamIterator.java        ← Implements CustomIterator<Team> with cursor
│
├── repository/
│   ├── Repository.java          ← Generic class: add(), getAll(), filter(Predicate<T>)
│   ├── TeamRepository.java      ← Extends Repository<Team>, adds filterByLeague()
│   ├── PlayerRepository.java    ← Extends Repository<Player>, adds filterByTeam()
│   └── MatchRepository.java     ← Extends Repository<Match>, adds filterByTeam()
│
├── data/
│   └── DataProvider.java        ← Hardcoded sample data for all three entity types
│
├── ui/
│   ├── ViewPagerAdapter.java    ← Supplies fragments to TabLayout
│   ├── TeamsFragment.java       ← Teams tab: RecyclerView + iterator + lambda sort/search
│   ├── PlayersFragment.java     ← Players tab: RecyclerView + lambda sort/search
│   ├── MatchesFragment.java     ← Matches tab: RecyclerView + lambda sort/search
│   ├── TeamAdapter.java         ← RecyclerView adapter for Team cards
│   ├── PlayerAdapter.java       ← RecyclerView adapter for Player cards
│   └── MatchAdapter.java        ← RecyclerView adapter for Match cards
│
└── MainActivity.java            ← Toolbar, TabLayout, SearchView, sort menu
```

---
### 1. Generics

The central class is `Repository<T extends SoccerEntity>`. The bounded type parameter `T extends SoccerEntity` means it can store any entity type — Team, Player or Match — as long as it implements the `SoccerEntity` interface. This eliminates the need to write separate storage classes for each type.

```java
// One generic class works for all three types
Repository<Team>   teamRepo   = new TeamRepository();
Repository<Player> playerRepo = new PlayerRepository();
Repository<Match>  matchRepo  = new MatchRepository();
```

The `filter()` method accepts a `Predicate<T>`, which is a functional interface — meaning you can pass a **lambda expression** directly into it:

```java
public List<T> filter(Predicate<T> predicate) {
    List<T> result = new ArrayList<>();
    for (T item : items) {
        if (predicate.test(item)) result.add(item);
    }
    return result;
}
```

---

### 2. Iterators

`CustomIterator<T>` is a custom generic iterator interface with two methods:

```java
public interface CustomIterator<T> {
    boolean hasNext();
    T next();
}
```

`TeamIterator` implements it using a cursor integer that tracks the current position independently from the list — this is the key design of the Iterator pattern:

```java
public class TeamIterator implements CustomIterator<Team> {
    private final List<Team> teams;
    private int cursor = 0;

    public boolean hasNext() { return cursor < teams.size(); }
    public Team next()       { return teams.get(cursor++); }
}
```

Used in `TeamsFragment` to traverse the list before displaying it:

```java
TeamIterator iterator = new TeamIterator(teams);
while (iterator.hasNext()) {
    ordered.add(iterator.next());
}
```

---

### 3. Lambda Expressions

Lambdas are used in three different contexts:

**Filtering** — passed as a `Predicate<T>` to `Repository.filter()`:
```java
// Filter teams by league
teamRepository.filter(t -> t.getLeague().equalsIgnoreCase("La Liga"));

// Live search across name and league
teamRepository.filter(t ->
    t.getName().toLowerCase().contains(query) ||
    t.getLeague().toLowerCase().contains(query));
```

**Sorting** — passed as a `Comparator` to `List.sort()`:
```java
sorted.sort(Comparator.comparing(t -> t.getName()));
sorted.sort(Comparator.comparing(t -> t.getLeague()));
```

**Tab setup** — passed as a callback to `TabLayoutMediator`:
```java
new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
    switch (position) {
        case 0: tab.setText("TEAMS");   break;
        case 1: tab.setText("PLAYERS"); break;
        case 2: tab.setText("MATCHES"); break;
    }
}).attach();
```

---
### Requirements
- Android Studio (latest stable)
- Android SDK API 26+
- Java 8+

### Steps
1. Clone the repository:
   ```bash
   git clone https://github.com/YOUR_USERNAME/SoccerManager.git
   ```
2. Open Android Studio → **File → Open** → select the `SoccerManager` folder
3. When prompted, select **"Use Embedded JDK"**
4. Wait for Gradle sync to complete
5. Press ▶ **Run** — deploy to emulator or device
