import com.BookStore.core.models.Book;
import com.BookStore.core.service.BookService;
import com.BookStore.web.controller.BookController;
import com.BookStore.web.converter.BookConverter;

import com.BookStore.web.dto.BookDto;
import com.BookStore.web.dto.BooksDataDto;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.*;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;


@RunWith(MockitoJUnitRunner.class)
public class BookControllerTest {

    @Mock
    private BookService bookService;

    @Mock
    private BookConverter bookConverter;

    @InjectMocks
    private BookController bookController;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void getBooks() throws Exception {
        List<Book> books = new ArrayList<>();

        Set<BookDto> bookDtos = new HashSet<>();
        bookDtos.add(mock(BookDto.class));
        bookDtos.add(mock(BookDto.class));

        when(bookService.findAll()).thenReturn(books);
        when(bookConverter.convertModelsToDtos(books)).thenReturn(bookDtos);

        BooksDataDto response = bookController.getBooks();

        assertEquals("There should be two books", 2, response.getBooks().size());
    }

    @Test
    public void updateBook() throws Exception {
        Book book = new Book("ana", "ana", 1L, "ana", "ana", 1);

        BookDto bookDto = mock(BookDto.class);
        Map<String, BookDto> bookDtoMap = new HashMap<>();
        bookDtoMap.put("book", bookDto);

        when(bookService.updateBook(anyInt(), anyString(), anyString(),
                                    anyLong(), anyString(), anyString(),
                                    anyInt(), anyBoolean())).thenReturn(book);

        BookDto result = new BookDto();
        result.setTitle(book.getTitle());
        when(bookConverter.convertModelToDto(book)).thenReturn(result);
        Map<String, BookDto> map = bookController.updateBook(book.getId(), bookDtoMap);
        assertEquals("Titles should be equal", "ana", map.get("book").getTitle());
    }

    @Test
    public void createBook() throws Exception {
        Book book = new Book("ana", "ana", 1L, "ana", "ana", 1);

        BookDto bookDto = mock(BookDto.class);
        Map<String, BookDto> bookDtoMap = new HashMap<>();
        bookDtoMap.put("book", bookDto);

        when(bookService.createBook(anyString(), anyString(), anyLong(),
                                    anyString(), anyString(), anyInt(), anyBoolean())).thenReturn(book);

        BookDto result = new BookDto();
        result.setTitle(book.getTitle());
        when(bookConverter.convertModelToDto(book)).thenReturn(result);

        Map<String, BookDto> map = bookController.createBook(bookDtoMap);
        assertEquals("Titles should be equal", "ana", map.get("book").getTitle());
    }


    @Test(expected = Exception.class)
    public void deleteBook() throws Exception {
        List<Book> books = new ArrayList<>();

        Set<BookDto> bookDtos = new HashSet<>();
        bookDtos.add(mock(BookDto.class));
        bookDtos.add(mock(BookDto.class));

        doThrow(new Exception()).when(bookService).deleteBook(anyInt());
        when(bookConverter.convertModelsToDtos(books)).thenReturn(bookDtos);

        ResponseEntity<BooksDataDto> response =
                (ResponseEntity<BooksDataDto>) bookController.deleteBook(bookDtos.iterator().next().getId());
        BooksDataDto booksDataDto = response.getBody();

        assertEquals("There should be one book", 1, booksDataDto.getBooks().size());
    }

}