package com.patterns.processor.homework;

import com.patterns.model.Message;
import com.patterns.processor.homework.exceptions.EvenSecondException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;

public class EvenSecondExceptionProcessorTest {
    @Test
    @DisplayName("Процессор бросает исключение, если секунда четная")
    void shouldThrowEvenSecondException() {
        // given
        var evenSecond = LocalDateTime.of(1970, 1, 1, 0, 0, 2);
        var dateTimeProvider = mock(DateTimeProvider.class);
        given(dateTimeProvider.getNow()).willReturn(evenSecond);

        var processor = new EvenSecondExceptionProcessor(dateTimeProvider);
        var message = new Message.Builder(1L).field1("I am not empty so you would not be lonely").build();

        // expect
        assertThrows(EvenSecondException.class, () -> processor.process(message));
        verify(dateTimeProvider, times(1)).getNow();
    }
}
