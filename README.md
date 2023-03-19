# REST API для игры в крестики нолики

Web api реализован для разработки сайта и мобильного приложения для игры в крестики нолики 3x3 для двух игроков. Игра проходит по обычным правилам.


## 🔭 Инструкция по запуску проекта
1. Откройте проект в Idea. В idea -> File -> Open -> Папка с проектом
2. Перейдите в файл application.properties и замените значения YOURDBNAME, YOURUSERNAME/YOURPASS - на ваши данные для подключения к MySQL.
3. Перейдите в файл DemoApplication и запустите приложение через метод main
4. Далее в БД автоматически сгенерируются таблицы
5. Перейдите в браузер и введите http://localhost:8080/login
6. Зарегистрируйте одного пользователя
7. После регистрации перейдите в БД, в таблицу users и замените значение REGISTERED на ADMIN
8. Авторизируйтесь на сайте

## Endpoints
GET api/games
Возвращает список всех игр.
{
    "id": 1,
    "board": "string",
    "currentPlayer": 0,
    "isOver": true,
    "winner": 0
}

{
  "id": 0,
  "board": "string",
  "currentPlayer": 0,
  "isOver": true,
  "winner": 0
}

{
  "id": 0,
  "board": "string",
  "currentPlayer": 0,
  "isOver": true,
  "winner": 0
}

{
  "id": 0,
  "board": "string",
  "currentPlayer": 0,
  "isOver": true,
  "winner": 0
}










