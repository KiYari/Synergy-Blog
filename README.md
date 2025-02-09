# Запуск проекта
Для старта необходим Docker и Docker-compose, иначе придется запускать сбилженные модули вручную + поднимать БД(присутствует миграция, так что просто указать БД):

Для запуска с докером: 
1. mvn clean install в верхней папке
2. Пройтись по core модулям, прописать docker build . -t <module_name>(не успел билд автоматизировать)
3. setx POSTGRES_HOST localhost(для windows)
4. docker-compose up в самой верхней папке

Без докера:
1. Сбилдить все проекты(можно в верхней папке mvn clean install)
2. Поднять БД(можно использовать и стороннюю)
3. В БД название database: blog, логпас проставить: admin admin(конфиг не настроил на другие)
4. setx POSTGRES_HOST <host>: host прописывать без протокола
5. Пройтись по модулям, запустить их( через java -jar)

# Анализ по выполненной задаче:
+ Нужно было построить веб приложение-блог.
+ В качестве языка программирования был выбран Java - кроссплатформенный верхнеуровневый ЯП, который позволяет легко строить распределенные системы
+ Как технологический стек решил использовать стандартный java стек - spring + hibernate
+ Так же для избежания проблем с БД было решено использовать миграцию БД - liquibase 
+ Начал с планирования архитектуры: Было выбрано решение реализовывать микросервисную инфраструктуру в связи с тем, что это поможет избежать огромного количества проблем при расширении проекта
+ Далее надо было позаботиться о способе деплоя системы, было решено использовать систему контейнеризации - Docker
+ В качестве метода разработки было принято решение работать по системе DDD(Domain Driven Design) - чтобы было гораздо проще расширять функционал

# Рекомендации по выявлению ошибок в ходе выполнения задачи
В ходе выполнения столкнулся с несколькими ошибками:
1. Нужно было использовать классы из другого модуля(для избежания повторения кусков кода). РЕШЕНИЕ: использовать локальный maven репозиторий для использования классов с других модулей
2. Из 1 вытекает другая проблема: потенциальные цикличные зависимости. РЕШЕНИЕ: Тут только быть внимательным и разбивать такие зависимости. К примеру, можно сделать модуль common, в котором будут общие для всех модулей классы
3. Была проблема с определением владельца POST\PUT запросов - можно было через ручной POST создать пост за авторством другого человека. РЕШЕНИЕ: создал фильтр, который проверял POST\PUT\DELETE запросы на владельца
4. Есть пачка микросервисов. Нам с фронта неудобно ходить в каждый из них HTTP запросами. РЕШЕНИЕ: Был запланирован Gateway API, который бы внутренними средствами запрашивал с микросервисом нужные данные и выдавал на фронт
5. Для дальнейшего потенциального расширения неудобно каждый раз вручную создавать БД, тестовый пользователей, админов и ТД. РЕШЕНИЕ: использовать миграцию БД, которая бы делал это за нас.
