# Задание: Курсовая работа. Менеджер личных финансов
***
# **Описание**
***

**Нашей целью будет написать серверное мавен-приложение, играющее роль менеджера личных финансов. У приложения в корневой папке должен находиться текстовый файл `categories.tsv`, категоризирующий название каждой покупки (разделитель - символ `Tab`, в джаве пишется как `'\t'`):**

1. булка	еда
2. колбаса	еда
3. сухарики	еда
4. курица	еда
5. тапки	одежда
6. шапка	одежда
7. мыло	быт
8. акции	финансы

**На сервер будут приходить запросы на этот порт с информацией о покупке в формате json:**

`{"title": "булка", "date": "2022.02.08", "sum": 200}`

**Дата будет именно в таком формате - `год.месяц.число`. Обратите внимание, что приходит название покупки, а не категория. Если названия в файле категорий нет, то должна выбиратья категория `другое`.**

**Сервер же в ответ должен будет отдавать текстовый JSON вида (можно в одну строку):**

`{
"maxCategory": {
"category": "еда",
"sum": 350000
}
}`

**Этот объект состоит из поля, каждое из которых отображает максимальную по абсолютным тратам категорию за весь период.**

**Сервер должен запускаться в `main` класса `Main` (без пакета) и слушать запросы, приходящие на порт `8989`.**

**Разделите логику сервера (обслуживание запросов) от логики подсчёта максимальных категорий (отдельный класс**); на последний класс напишите юнит-тесты. Добавление тестов (и исправление найденных ими дефектов, если таковые будут) сделайте в отдельной ветке и смёржите в основную ветку через пулл-реквест (закрывая пулл-реквест, не удаляйте созданную ветку). Все дополнительные задания также должны делаться через ветки и закрытые пулл-реквесты в основную ветку.

### Напоминание как выглядит простой сервер

1. try (ServerSocket serverSocket = new ServerSocket(8989);) { // стартуем сервер один(!) раз
2. while (true) { // в цикле(!) принимаем подключения
3. try (
4. Socket socket = serverSocket.accept();
5. BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
6. PrintWriter out = new PrintWriter(socket.getOutputStream());
7. ) {
8. // обработка одного подключения
9. }
10. }
11. } catch (IOException e) {
12. System.out.println("Не могу стартовать сервер");
13. e.printStackTrace();
14. }