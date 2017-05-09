# PGRF_JOGL_CITY_FLIGHT
Computer Graphic - Java OpenGL - Student project (PGRF2 - FIM - UHK) - Flying through the city - 2015

# Průlet městem
Author: Radek Souček / AI3 / 3. ročník studia / 2015

## Abstrakt
Cílem semestrálního projektu „Průlet městem“ v prostředí Java OpenGL bylo vymodelování části města a dynamický pohyb v něm v roli pozorovatele / letadla včetně detekce kolizí pozorovatele s objekty města. Model dynamického pohybu letadla je řízen klávesami, kdy klávesy W/S ovládají plyn a brzdu a pomocí kurzorových šipek se ovládají směrovky letadla pro zatáčení či stoupání resp. klesání. Samozřejmostí je i postupné zvyšování rychlosti letadla na maximální rychlost či naopak snižování rychlosti na minimální rychlost, kdy dojde ke ztrátě řízení a pádu letadla. Detekce kolizí s objekty je řešena pomocí kolizních obálek okolo objektů, kdy v projektu je použit základní typ obálek ve tvaru osově zarovnaného kvádru. Kolizní obálky jsou uchovány v seznamu, kdy při každém vykreslení obrazovky se testuje, zda pozice pozorovatele / letadla se nachází v prostoru některé kolizní obálky.

Výsledný projekt obsahuje různé režimy průletu městem. Implementován byl denní a noční režim, kdy denní režim může navíc obsahovat i ranní mlhu. V projektu je možno přepínat i mezi různými druhy osvětlení. Projekt se také částečně zabývá problematikou blendingu, neboli míchání barev a správného postupného vykreslování, kde to je znázorněno např. na modelu autobusu či stromu.

Ovládání / Controls: [https://github.com/xDx11/PGRF_JOGL_CITY_FLIGHT/wiki/Controls](https://github.com/xDx11/PGRF_JOGL_CITY_FLIGHT/wiki/Controls)

Ukázky / Examples: [https://github.com/xDx11/PGRF_JOGL_CITY_FLIGHT/wiki/Examples](https://github.com/xDx11/PGRF_JOGL_CITY_FLIGHT/wiki/Examples)
