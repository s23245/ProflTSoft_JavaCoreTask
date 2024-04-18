
## Основні класи

1. `Hero` - об'єкт, що представляє героя з JSON файлу.
2. `StatisticsCount` - клас, який відповідає за обчислення статистики.
3. `JSONReader` - клас, який відповідає за читання та парсинг JSON файлів.
4. `Main` - клас, який відповідає за виконання програми.
5. `XMLWriter` - клас, який відповідає за запис статистики у XML файл.

## Приклади вхідних і вихідних файлів

### Приклад вхідного JSON файлу:

[
  {"heroClassName": "Warrior", "heroLevel": 1, "manaAmount": 100, "abilities": "Slash, ShieldBash, Charge", "heroMainElement": "EARTH"},
  {"heroClassName": "Healer", "heroLevel": 1, "manaAmount": 150, "abilities": "Heal, Revive, Shield", "heroMainElement": "WATER"}
]

### Приклад вихідного XML файлу:
`
<ArrayList>
  <item>
    <value>Fireball</value>
    <count>12</count>
  </item>
  <item>
    <value>Meteor</value>
    <count>12</count>
  </item>
  <item>
    <value>Firestorm</value>
    <count>12</count>
  </item>
</ArrayList>`

## Results of performance with different amount of threads
* **1.Amount of files in directory: 3**
  * Elapsed time with 1 threads: 56 ms
  * Elapsed time with 2 threads: 3 ms
  * Elapsed time with 4 threads: 2 ms
  * Elapsed time with 8 threads: 2 ms
* **2.Amount of files in directory: 10**
  * Elapsed time with 1 threads: 63 ms
  * Elapsed time with 2 threads: 5 ms
  * Elapsed time with 4 threads: 5 ms
  * Elapsed time with 8 threads: 4 ms