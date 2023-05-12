## Build keretrendszer beüzemelése
Ebben a projektben még nem volt használt keretrendszer.
A többi feladathoz használt eszközök közül több is a Maven alapú build rendszerre épül ezért ezt választottuk.
A Maven használatához szükséges volt a pom.xml fájl létrehozása, amelyben a projekt függőségeit és a build folyamatot lehet beállítani.

## CI beüzemelése
A CI beüzemeléséhez a GitHub Actions-t választottuk.
A GitHub Actions-t azért választottuk, mert a GitHub-on van a projektünk és a GitHub Actions ingyenesen használható nyílt forráskódú projektekhez.
A CI folyamatot a .github/workflows mappában lévő maven.yml fájlban definiáltuk.
A GitHub Actions előre definiált Maven folyamatából indultunk ki.