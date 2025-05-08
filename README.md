# 🎫 Система бронирования билетов

**Описание:**

Проект представляет собой многослойную архитектуру системы бронирования билетов на рейсы. Он включает в себя модели, репозитории и сервисы, разделённые по слоям, что обеспечивает модульность и удобство поддержки кода.

---

## 🧱 Структура проекта

* **Модели (Model):** Классы, представляющие основные сущности системы: `Ticket`, `Passenger`, `Flight`.
* **Репозитории (Repository):** Интерфейсы для доступа к данным, такие как `TicketRepository`, `PassengerRepository`, `FlightRepository`.
* **Сервисы (Service):** Интерфейсы, определяющие бизнес-логику приложения: `BookingService`, `FlightService`, `PassengerService`.

---

## 🔧 Технологии

* Java 8 и выше
* Maven для управления зависимостями и сборки проекта
* Структура проекта соответствует стандартам Maven

---

## 🚀 Запуск проекта

1. Клонируйте репозиторий:

   ```bash
   git clone https://github.com/AlexH73/booking_final_project_57_1.git
   ```
2. Перейдите в директорию проекта:

   ```bash
   cd booking
   ```
3. Соберите проект с помощью Maven:

   ```bash
   mvn clean install
   ```

---

## 📁 Структура каталогов

```


booking/
├── src/
│   ├── main/
│   │   └── java/
│   │       └── com/
│   │           └── example/
│   │               └── booking/
│   │                   ├── model/
│   │                   ├── repository/
│   │                   └── service/
│   └── test/
│       └── java/
│           └── com/
│               └── example/
│                   └── booking/
│                       ├── model/
│                       ├── repository/
│                       └── service/
├── pom.xml
└── README.md
```



---

## 👥 Авторы и разработчики

* **Автор проекта:**

    * [iliasM-1733](https://github.com/iliasM-1733)

* **Разработчики:**

    * [AlexH73](https://github.com/AlexH73)
    * [Gott-II](https://github.com/Gott-II)
    * [irisyoga](https://github.com/irisyoga)
    * [Juliaaa25](https://github.com/Juliaaa25)
    * [JuriBuerkle](https://github.com/JuriBuerkle)
    * [Yulianna-B](https://github.com/Yulianna-B)

---

## 🤝 Присоединяйтесь к проекту

Мы приветствуем вклад сообщества в развитие проекта. Если у вас есть предложения, улучшения или исправления, пожалуйста:

1. Откройте [Issue](https://github.com/AlexH73/booking/issues) для обсуждения изменений.
2. Создайте форк репозитория и внесите необходимые изменения.
3. Отправьте Pull Request для рассмотрения и объединения ваших изменений.

Ваш вклад поможет сделать проект лучше!

---

## 📄 Лицензия

Этот проект лицензируется под лицензией [MIT](https://github.com/AlexH73/booking_final_project_57_1?tab=MIT-1-ov-file).
