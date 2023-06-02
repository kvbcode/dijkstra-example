## Пример реализации алгоритма Дейкстры

1. Найти узел с наименьшей стоимостью среди необработанных
2. Перебрать соседей текущего узла
    - Если к соседу проще добраться через текущий узел:
      - Обновляем вес соседа
      - Текущий узел становится родителем для соседа
    - Отмечаем текущий узел как обработанный

Для поиска результирующего пути рекурсивно идем по списку родителей нужного узла.
