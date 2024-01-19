package nl.novi.techiteasy.services;

import nl.novi.techiteasy.dtos.television.TelevisionDto;
import nl.novi.techiteasy.models.Television;
import nl.novi.techiteasy.repositories.TelevisionRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.apache.logging.log4j.ThreadContext.isEmpty;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.boot.BootstrapRegistry.InstanceSupplier.of;


@ExtendWith(MockitoExtension.class)

class TelevisionServiceTest {

    @Mock
    TelevisionRepository tvRepos;

    @InjectMocks
    TelevisionService tvService;

    Television television;

    @BeforeEach
    void InIt() {
        television = new Television(1234L, "LG", 400);
        television.setId(1234L);
        television.setName("LG");
        television.setPrice(400.00);
    }

    @AfterEach
    void tearDown() {
        television = null;
    }

    @Test
    @DisplayName("Should get all televisions")
    void test1() {

        // Arrange
        Television television = new Television();
        television.setId(1234L);
        television.setName("LG");
        television.setPrice(400.00);
        when(tvRepos.findAll()).thenReturn(List.of(television));

        // Act
        List<TelevisionDto> result = tvService.getAllTelevisions();

        // Assert
        assertEquals(1234L, result.get(0).id);
        assertEquals("LG", result.get(0).name);
        assertEquals(400.00, result.get(0).price);
    }

    @Test
    @DisplayName("Should delete television")
    void test2() {
        // Arrange
        Television television = new Television();
        television.setId(1234L);
        television.setName("LG");
        television.setPrice(400.00);
        when(tvRepos.findById(anyLong())).thenReturn(Optional.of(television));

        // Act
        tvService.deleteTelevision(1234L);

        // Assert
        verify(tvRepos, Mockito.times(1)).deleteById(1234L);  // verify meet hoe vaak (aantal times) de methode wordt aangeroepen, handig om void methode te testen
    }

}