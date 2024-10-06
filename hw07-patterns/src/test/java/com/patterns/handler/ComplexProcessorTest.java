package com.patterns.handler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.patterns.listener.Listener;
import com.patterns.model.Message;
import com.patterns.processor.Processor;
import com.patterns.processor.ProcessorConcatFields;
import com.patterns.processor.ProcessorUpperField10;
import com.patterns.processor.homework.SwapFieldsProcessor;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class ComplexProcessorTest {

    @Test
    @DisplayName("Тестируем вызовы процессоров")
    void handleProcessorsTest() {
        // given
        var message = new Message.Builder(1L)
                .field1("field-to-join-1")
                .field2("field-to-join-2")
                .field3("field-to-join-3")
                .field4("concat: field-to-join-1 field-to-join-2 field-to-join-3")
                .field5("UPPER_ME!")
                .field10("upper_me!")
                .field11("swap_with_field12")
                .field12("swap_with_field11")
                .build();

        var messageToProcess = new Message.Builder(1L)
                .field1("field-to-join-1")
                .field2("field-to-join-2")
                .field3("field-to-join-3")
                .field10("upper_me!")
                .field11("swap_with_field11")
                .field12("swap_with_field12")
                .build();

        var processor1 = mock(ProcessorConcatFields.class);
        given(processor1.process(any())).willCallRealMethod();

        var processor2 = mock(SwapFieldsProcessor.class);
        given(processor2.process(any())).willCallRealMethod();

        var processor3 = mock(ProcessorUpperField10.class);
        given(processor3.process(any())).willCallRealMethod();

        List<Processor> processors = List.of(processor1, processor2, processor3);

        var complexProcessor = new ComplexProcessor(processors, ex -> {});

        // when
        var result = complexProcessor.handle(messageToProcess);

        // then
        verify(processor1).process(message);
        verify(processor2).process(message);
        verify(processor3).process(message);
        assertThat(result).usingRecursiveComparison().isEqualTo(message);
    }

    @Test
    @DisplayName("Тестируем обработку исключения")
    void handleExceptionTest() {
        // given
        var message = new Message.Builder(1L).field8("field8").build();

        var processor1 = mock(Processor.class);
        when(processor1.process(message)).thenThrow(new RuntimeException("Test Exception"));

        var processor2 = mock(Processor.class);
        when(processor2.process(message)).thenReturn(message);

        var processors = List.of(processor1, processor2);

        var complexProcessor = new ComplexProcessor(processors, ex -> {
            throw new TestException(ex.getMessage());
        });

        // when
        assertThatExceptionOfType(TestException.class).isThrownBy(() -> complexProcessor.handle(message));

        // then
        verify(processor1, times(1)).process(message);
        verify(processor2, never()).process(message);
    }

    @Test
    @DisplayName("Тестируем уведомления")
    void notifyTest() {
        // given
        var message = new Message.Builder(1L).field9("field9").build();

        var listener = mock(Listener.class);

        var complexProcessor = new ComplexProcessor(new ArrayList<>(), ex -> {});

        complexProcessor.addListener(listener);

        // when
        complexProcessor.handle(message);
        complexProcessor.removeListener(listener);
        complexProcessor.handle(message);

        // then
        verify(listener, times(1)).onUpdated(message);
    }

    private static class TestException extends RuntimeException {
        public TestException(String message) {
            super(message);
        }
    }
}
