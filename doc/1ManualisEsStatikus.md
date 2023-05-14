## Statikus kódanalízis
A statikus analízishez IntelliJ környezetben a SpotBugs eszközt használtuk.
A vizsgálat pillanatában 311 bug volt összesen 203 osztályban. A következőkben ezen hibák elemzése és bizonyos hibák javítása látható.
![Hibak](Kepernyokepek/1ManualisEsStatikus_kepek/hibak_szama.png)

## Kijavított hibák:

- A metódus neve csak egy nagybetűvel kezdődéssel tért el az Object alaposztály metódusától, ami miatt egyszerűen össze lehet őket téveszteni. A metódus neve javítva lett egy beszédesebbre.
![ConfusingName](Kepernyokepek\1ManualisEsStatikus_kepek\scary_group_item.png)
![ConfusingName](Kepernyokepek\1ManualisEsStatikus_kepek\scary_group_item_fixed.png)

- A képen látható kódrészben egyenlőség operátorral hasonlítottunk össze stringeket, de ez nem jó sok esetben ezért lecseréltük a `String.equals()` metódusra.
![StringEquals](Kepernyokepek\1ManualisEsStatikus_kepek\string_equals_trouble.png)
![StringEquals](Kepernyokepek\1ManualisEsStatikus_kepek\string_equals_trouble_fixed.png)

- Több helyen is csak 1 sorig létező random szám generátor objektumot hoztunk létre, ami nem hatékony, ezért külön osztálytól függő statikus változóba mentettük a random szám generátort.
![Random](Kepernyokepek\1ManualisEsStatikus_kepek\inherited_static_random.png)
![Random](Kepernyokepek\1ManualisEsStatikus_kepek\inherited_static_random_fixed.png)

- Több helyen is null érték ellenőrzés nélkül használtunk szöveges bemenetet, ezekhez az esetekhez hozzáadtunk egy null érték ellenőrzést.
![Null](Kepernyokepek\1ManualisEsStatikus_kepek\dereference_without_null_check.png)
![Null](Kepernyokepek\1ManualisEsStatikus_kepek\dereference_without_null_check_with_while_fixed.png)

- Több helye is a switch case struktúrában nem volt default ág, ezért hozzáadtunk ezekhez default ágakat.
![Switch](Kepernyokepek\1ManualisEsStatikus_kepek\switch_case_fallthrough.png)
![Switch](Kepernyokepek\1ManualisEsStatikus_kepek\switch_case_fallthrough_fixed.png)

## Manuális kód átvizsgálás
