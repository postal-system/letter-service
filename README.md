# Letter Service
Сервис реализует CRUD операции над доменным объектом Letter, сервис аналогичен Parcel Service

## Используемые технологии:
- Maven
- Spring-boot: web, data-jpa, test
- Liquibase
- PostgreSQL
- Swagger
- Mapstuct
- Testcontaine
- Checkstyle

## Особенности реализации
### LetterServiceImpl.save(Letter letter)
````
    @Override
    public synchronized UUID save(Letter letter) {
        if (repository.existsById(letter.getId())) {
            throw new IdAlreadyExistException(...);
        }
        ...
        return repository.save(letter).getId();
    }
````
Метод сохранения объекта <b>"Letter"</b> в БД синхронизирован для того, что бы избежать ситуации <b>check-then-act</b> при которой
разные потоки пытаются добавить объект в БД, при этом первый добавит объект, действия второго обновят его состояние.

Существование объекта проверяется для того что бы подтвердить что объект с данным ID не был добавлен ранее
и было гарантированно, что в случае попытки его повторного добавления об этом стало известно вызванивающему метод



