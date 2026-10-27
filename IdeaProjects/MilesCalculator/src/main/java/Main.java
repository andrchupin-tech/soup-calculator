public class Main {
    public static void main(String[] args) {
        // Начальные данные (берем из примера №2)
        int currentAccount = 100;          // Текущий счет клиента
        int replenishmentAmount = 1000;    // Сумма пополнения

        int bonusRubles = 0;               // Переменная для бонусов

        // Проверяем, превысила ли сумма пополнения порог (строго больше 1000)
        if (replenishmentAmount > 1000) {
            // Начисляем 1 рубль за каждые ПОЛНЫЕ 100 рублей пополнения
            bonusRubles = replenishmentAmount / 100;
        }

        // Рассчитываем итоговый счет (текущий + пополнение + бонусы)
        int finalAccount = currentAccount + replenishmentAmount + bonusRubles;

        // Выводим результат на экран
        System.out.println("Итоговый счёт: " + finalAccount);
        System.out.println("Бонусные рубли: " + bonusRubles);
    }
}