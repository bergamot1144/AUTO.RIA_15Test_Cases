# Проект UI-автотестов для AUTO.RIA (Selenide + Cucumber + TestNG + Allure)

## 1. Назначение проекта

Набор BDD‑сценариев и UI‑тестов для проверки ключевых пользовательских фич сайта `auto.ria.com`: подбор транспорта,
фильтры, просмотр карточек, добавление в избранное. Проект ориентирован на стабильный запуск из Maven, генерацию
Allure‑отчетов и понятную структуру Page Object.

## 2. Технологический стек

- Java 17+ (предпочтительно)
- Maven
- Selenide (UI‑автоматизация над Selenium)
- Cucumber 7 + Gherkin (BDD)
- TestNG (раннер и ассерты)
- Allure (репорты, вложения, шаги)
- Firefox (по умолчанию) с кастомным профилем

## 3. Структура проекта

- `src/main/java/pages` — Page Object классы
    - `HomePage` — главная страница, фильтры, добавление в избранное, навигация
    - `FavouritesPage` — страница избранного, валидация присутствия добавленных элементов
    - `CatalogPage`, `ProductPage`, `NewCarsPage`, `VehicleCheckerPage` — вспомогательные страницы
    - `TransportData` — модель данных транспорта (title, price, year, location)
- `src/main/java/support/Config` — единая конфигурация браузера и Allure
- `src/test/resources/features` — Gherkin‑фичи
- `src/test/java/steps` — степ‑дефиниции, раннер `CucumberTestNGRunner`, хуки `CucumberHooks`
- `src/test/resources/testng.xml` — конфигурация TestNG‑сьюта
- `pom.xml` — зависимости, плагины, surefire и allure‑maven

## 4. Запуск тестов

1. Из корня проекта:

```bash
mvn clean test
```

2. Просмотр отчета Allure:

```bash
mvn allure:serve
```

Примечания:

- Директория `target/allure-results` создаётся только после выполнения тестов.
- Для headless‑запуска можно использовать `Config.setupBrowserHeadless()` в инициализации.

## 5. Allure

- Подключены зависимости `allure-cucumber7-jvm`, `allure-selenide`, `allure-testng` (через TestNG).
- В `Config` инициализируется `AllureSelenide` (скриншоты и HTML).
- Плагин `io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm` активирован в `CucumberTestNGRunner` и `pom.xml` (через
  `configurationParameters`).

Обычный сценарий использования:

- Запустить `mvn clean test` → получаем `target/allure-results`
- Запустить `mvn allure:serve` → отчёт поднимется в браузере локально

## 6. Жизненный цикл браузера

- Управляется Selenide. Для каждого сценария Cucumber используется изоляция с закрытием браузера в `@After` (см.
  `CucumberHooks`).
- Это предотвращает утечку состояния между тестами (например, избранное не «мигрирует» между сценариями).

## 7. Антидетект и стабильность кликов

- В `Config` настраивается `FirefoxProfile`: отключение `dom.webdriver.enabled`, `useAutomationExtension`, уведомлений и
  push.
- Переопределяется `userAgent`, добавляются аргументы `--disable-blink-features=AutomationControlled` и др.
- Для надежности взаимодействий используется:
    - масштаб страницы (`document.body.style.zoom`),
    - наведение `hover()` перед кликом,
    - запасной JavaScript‑клик на критичных элементах (сердце избранного).

## 8. Ключевой тест: «Добавление в избранное»

- `HomePage.clickHeartButtons(int count)` добавляет N карточек в избранное:
    - сбор данных карточки (`title`, `price`, `year`, `location`)
    - масштаб страницы и наведение на кнопку
    - клик (JS → fallback на обычный)
    - верификация изменения и фиксация данных в локальном списке
- На странице `FavouritesPage` метод `containsTransport(...)` гибко сравнивает значения, учитывая различия
  форматирования.

## 9. Page Object подход

- Локаторы инкапсулированы в страницах.
- Методы страниц выполняют атомарные действия высокого уровня (клик, ввод, выбор).
- Сценарии читаются из Gherkin‑файлов и маппятся на степы, которые вызывают методы Page Object.

## 10. Конфигурация Maven / TestNG / Cucumber

- `pom.xml` настроен на TestNG: зависимость `cucumber-testng`, раннер `CucumberTestNGRunner`, `testng.xml`.
- `maven-surefire-plugin` запускает `testng.xml` и передает `cucumber.plugin` для Allure.

## 11. Как воспроизвести локально (шаги)

1. Установить JDK и Maven, убедиться, что Maven доступен из IDE или терминала.
2. Открыть проект в IntelliJ IDEA.
3. Проверить `src/test/resources/testng.xml` (по умолчанию включает раннер Cucumber).
4. Выполнить `mvn clean test`.
5. Выполнить `mvn allure:serve` и дождаться открытия отчёта.

## 12. Траблшутинг

- «Directory target/allure-results not found»: сначала выполните `mvn clean test`.
- Тест клика «не срабатывает»: проверьте масштаб/прокрутку и что используется JS‑клик fallback.
- Состояние между тестами сохраняется: убедитесь, что `@After` в `CucumberHooks` закрывает WebDriver.
- Медленные элементы: увеличьте `Configuration.timeout` в `Config`.

## 13. Команды

```bash
# Запуск тестов
mvn clean test

# Локальный отчёт Allure
mvn allure:serve

# Генерация статического отчёта (если нужно)
mvn allure:report
```

## 14. Что проверять на защите

- Объяснить стек и почему он выбран.
- Показать структуру Page Object и Gherkin‑сценариев.
- Продемонстрировать изоляцию браузера через хуки.
- Показать Allure‑отчёт, шаги, скриншоты и артефакты.
- Рассказать о приёмах повышения стабильности (масштаб, scroll/hover, JS‑клик, антидетект‑настройки).

## 15. Расширение

- Добавить параметризацию окружений (dev/stage/prod) через Maven профили.
- Интегрировать в CI (GitHub Actions / Jenkins) с публикацией Allure‑отчётов.

# AUTO.RIA_15Test_Cases
