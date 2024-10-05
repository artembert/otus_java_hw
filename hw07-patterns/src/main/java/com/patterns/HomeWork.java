package com.patterns;

import com.patterns.handler.ComplexProcessor;
import com.patterns.listener.homework.HistoryListener;
import com.patterns.model.Message;
import com.patterns.model.ObjectForMessage;
import com.patterns.processor.homework.DateTimeProviderImpl;
import com.patterns.processor.homework.EvenSecondExceptionProcessor;
import com.patterns.processor.homework.SwapFieldsProcessor;
import java.util.List;
import java.util.function.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HomeWork {
    private static final Logger logger = LoggerFactory.getLogger(HomeWork.class);

    /*
    Реализовать to do:
      ✅ 1. Добавить поля field11 - field13 (для field13 используйте класс ObjectForMessage)
      ✅ 2. Сделать процессор, который поменяет местами значения field11 и field12
      ✅ 3. Сделать процессор, который будет выбрасывать исключение в четную секунду (сделайте тест с гарантированным результатом)
            Секунда должна определяьться во время выполнения.
            Тест - важная часть задания
            Обязательно посмотрите пример к паттерну Мементо!
      ✅ 4. Сделать Listener для ведения истории (подумайте, как сделать, чтобы сообщения не портились)
         Уже есть заготовка - класс HistoryListener, надо сделать его реализацию
         Для него уже есть тест, убедитесь, что тест проходит
    */

    public static void main(String[] args) {
        /*
          по аналогии с Demo.class
          из элеменов "to do" создать new ComplexProcessor и обработать сообщение
        */
        var dateTimeProvider = new DateTimeProviderImpl();
        var processors = List.of(new SwapFieldsProcessor(), new EvenSecondExceptionProcessor(dateTimeProvider));
        Consumer<Exception> errorHandler = exception -> logger.error("Handled exception: {}", exception.getMessage());

        var complexProcessor = new ComplexProcessor(processors, errorHandler);
        var historyListener = new HistoryListener();
        complexProcessor.addListener(historyListener);

        var objectForMessage = new ObjectForMessage();
        objectForMessage.setData(List.of("apple", "banana", "peach"));

        var message = new Message.Builder(1L)
                .field1("field1")
                .field10("field10")
                .field11("field11")
                .field12("field12")
                .field13(objectForMessage)
                .build();

        var result = complexProcessor.handle(message);
        logger.info("result:{}", result);

        complexProcessor.removeListener(historyListener);
    }
}
