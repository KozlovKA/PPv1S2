# Вторая Лабараторная работа по курсу ППвИС

## Тема:"ПОСТРОЕНИЕ ГРАФИЧЕСКОГО ПОЛЬЗОВАТЕЛЬСКОГО ИНТЕРФЕЙСА НА ОСНОВЕ ДИАЛОГОВ"


### Козлов К.А-821701
### Вариант 4
 
Условия поиска и удаления:
- по номеру телефона или фамилии;
- по номеру счета или адресу
- по ФИО и цифрам встречающемся в одном из номеров (может
быть заполнен только один элемент ФИО, например имя).

Приложение построено по шаблону проектирования MVC
### Пакеты:
-controller
-listener
-loader
-model
-saver
-view
В contoller интерпритируются действия пользователя оповещая model о необходимости изменений
В listener описываются действия которые должны происходить при нажатии на кнопки
В loader прописан парсер из xml файла и перевод данных в переменные 
В view задается внешний вид программы 
