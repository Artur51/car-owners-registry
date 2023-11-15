package data.registry.cars.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UtilsTest {

    @Test
    public void testParseSortMethod_Should_ParseAscValue() {
        String sort = "name:asc";
        Sort result = Utils.parseSort(sort);
        Sort expected = Sort.by("name").ascending();
        assertEquals(expected, result);
    }

    @Test
    public void testParseSortMethod_Should_ParseDescValue() {
        String sort = "otherName:desc";
        Sort result = Utils.parseSort(sort);
        Sort expected = Sort.by("otherName").descending();
        assertEquals(expected, result);
    }

    @Test
    public void testParseSortMethod_Should_ThrowExceptionInCaseSortingOrderIsInvalid() {
        String sort = "name:wrongValue";

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> Utils.parseSort(sort));
        assertNotNull(thrown);
        assertEquals(HttpStatus.BAD_REQUEST.value(), thrown.getStatusCode().value());
        assertEquals("400 BAD_REQUEST \"Unexpected sort parameter value name:wrongValue; value values are: asc or desc\"", thrown
                .getMessage());
    }

    @Test
    public void testParseSortMethod_Should_ThrowExceptionInCaseSortingOrderIsNotProvided() {
        String sort = "onlyName";

        ResponseStatusException thrown = Assertions.assertThrows(ResponseStatusException.class, () -> Utils.parseSort(sort));
        assertNotNull(thrown);
        assertEquals(HttpStatus.BAD_REQUEST.value(), thrown.getStatusCode().value());
        assertEquals("400 BAD_REQUEST \"Sort parameter onlyName is invalid\"", thrown.getMessage());
    }

}
