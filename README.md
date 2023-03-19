# Ресторанный бизнес / общепит. Управление меню.
<h1 align="center">Hi there, I'm Elina</a> 
<img src="https://github.com/blackcater/blackcater/raw/main/images/Hi.gif" height="32"/></h1>
<h3 align="center">Software programming student from Russia 🇷🇺</h3>

Данный проект реализован в рамках курсовой работы в университете, он позволяет осуществлять управление меню сотрудниками ресторана или иного заведения общепита.

## 🔭 Инструкция по запуску проекта
1. Откройте проект в Idea. В idea -> File -> Open -> Папка с проектом
2. Перейдите в файл application.properties и замените значения YOURDBNAME, YOURUSERNAME/YOURPASS - на ваши данные для подключения к MySQL.
3. Перейдите в файл DemoApplication и запустите приложение через метод main
4. Далее в БД автоматически сгенерируются таблицы
5. Перейдите в браузер и введите http://localhost:8080/login
6. Зарегистрируйте одного пользователя
7. После регистрации перейдите в БД, в таблицу users и замените значение REGISTERED на ADMIN
8. Авторизируйтесь на сайте

## Оглавление

1. [Структура проекта](#Структура-проекта)
   1. [Описание проекта](#Описание-проекта)
   2. [Описание веб-сайта](#Описание-веб-сайта)
   3. [Models](#Models)
   4. [Views](#Views)
   5. [Controllers](#Controllers)
2. [Страницы сайта](#Страницы-сайта)
   1. [Регистрация](#Регистрация)
   2. [Авторизация](#Авторизация)
   3. [Блок Header ](#Блок-Header)
   4. [Блюда](#Блюда)
   5. [Подборки](#Подборки)
   6. [Назначение сотрудников](#Назначение-сотрудников)
   7. [Добавление блюда](#Добавление-блюда)
   8. [Добавление подборки](#Добавление-подборки)
   9. [Просмотр блюд подборки](#Просмотр-подборки)

## Структура проекта

### Описание проекта
Проект написан на языке программирования Java с использованием фреймворка 
Spring, базы данных MySQL, языка разметов HTML и таблицы стилей CSS. 
Структура проекта реализована по шаблону MVC. 
### Описание веб-сайта
Веб-сайт представляет собой сайт для управления меню  
в ресторане/общепите. Пользователь может 
просматривать блюда и подборки из меню. Управление же меню доступно
зарегестрированным сотрудникам. На сайте присутствует форма
регистрация и авторизации. У 
зарегистрированных пользователей может быть одна из 
трёх ролей: шеф-повар, менеджер, администратор.
Роль определяет функционал, позволяющий управлять меню. 
Шеф-повар добавляет описание блюд, ингредиенты, калорийность.
Менеджер составляет подборки меню из блюд от шефа.
Админ создает менеджера и шефа, а также может 
добавлять новые блюда (названия) и удалять созданные.
### Models
Сервис работает с базой данных, в которой хранятся блюда и подборки блюд. Поэтому под категорию моделей
попадают все классы данных, 
хранящихся в БД, репотизориев и сервисов. 
[Ссылка](https://github.com/ElenKor/restWeb/tree/master/src/main/java/com/example/demo/models) 
на директорию models. 
### Views
Работу сервиса отображают страницы сайта. Поэтому под 
категорию представлений попадают HTML шаблоны страниц,
которые могут отображать различную информацию, в 
зависимости от различных условий (роль пользователя, 
наполнение базы данных и т.д.).
[Ссылка](https://github.com/ElenKor/restWeb/tree/master/src/main/resources/templates)
на директорию templates.
### Controllers
Для того, чтобы загружать страницы сайта с нужной 
информацией, а также для обработки POST запросов 
нужны классы контроллеры. Они достают из
базы данных нужные данные, а затем отправляют их на страницы, 
которые их уже отображают нужным образом. Также 
контроллеры формы на страницах, добавляют в БД новые
данные.
[Ссылка](https://github.com/ElenKor/restWeb/tree/master/src/main/java/com/example/demo/controllers)
на директорию controllers.
## Страницы сайта
### Регистрация
Страница регистрации пользволяет зарегестрироваться новым сотрудникам на сайте. 
Далее функции будут доступны сотруднику согласно его должности, 
определенную администратором.
![Alt-text](https://github.com/ElenKor/restWeb/blob/master/src/main/resources/dist/images/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-01-17%20%D0%B2%2000.58.47.png)
### Авторизация
Страница для авторизации зарегестрированных пользователей.
![Alt-text](https://github.com/ElenKor/restWeb/blob/master/src/main/resources/dist/images/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-01-17%20%D0%B2%2000.58.32.png)
### Блок Header 
На каждой странице (кроме страницы авторизации и регистрации) есть 
верхний блок. В нем расположены расположены ссылки на другие страницы сайта,
а также кнопка выхода из аккаунта. Наполненность ссылками на другие страницы 
зависит от роли пользователя.
### Блюда
На главной странице сервиса расположено меню блюд с картинками и кратким описанием.
![Alt-text](https://github.com/ElenKor/restWeb/blob/master/src/main/resources/dist/images/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-01-17%20%D0%B2%2001.05.50.png)
### Подборки
На странице с подборками блюд располагаются список 
блюд, организованных менеджером по категориям. 
![Alt-text](https://github.com/ElenKor/restWeb/blob/master/src/main/resources/dist/images/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-01-17%20%D0%B2%2000.58.05.png)
### Назначение сотрудников
**Данная страница доступна только администратору**
На странице показаны все зарегестрированные пользователи, которым администратор может выдать права доступа
![Alt-text](https://github.com/ElenKor/restWeb/blob/master/src/main/resources/dist/images/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-01-17%20%D0%B2%2000.59.13.png)
### Добавление блюда
**Данная страница доступна только администратору и шеф-повару.**
На данной странице Шеф-повар имеет возможность добавить блюдо с подробным описанием.
Администратор же добавляет только названия блюд
![Alt-text](https://github.com/ElenKor/restWeb/blob/master/src/main/resources/dist/images/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-01-17%20%D0%B2%2008.38.33.png)
![Alt-text](https://github.com/ElenKor/restWeb/blob/master/src/main/resources/dist/images/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-01-17%20%D0%B2%2008.38.58.png)
### Добавление подборки
**Даная страница доступна только менеджеру.**
![Alt-text](https://github.com/ElenKor/restWeb/blob/master/src/main/resources/dist/images/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-01-17%20%D0%B2%2001.01.54.png)
### Просмотр подборки
Данная страница доступная всем пользователям
![Alt-text](https://github.com/ElenKor/restWeb/blob/master/src/main/resources/dist/images/%D0%A1%D0%BD%D0%B8%D0%BC%D0%BE%D0%BA%20%D1%8D%D0%BA%D1%80%D0%B0%D0%BD%D0%B0%202023-01-18%20%D0%B2%2001.07.35.png)








