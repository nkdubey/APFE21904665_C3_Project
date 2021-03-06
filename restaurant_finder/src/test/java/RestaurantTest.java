import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RestaurantTest {
    Restaurant restaurant;

    //REFACTOR ALL THE REPEATED LINES OF CODE
    @BeforeEach
    public void beforeEach() {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        restaurant = new Restaurant("Amelie's cafe", "Chennai", openingTime, closingTime);
        restaurant.addToMenu("Sweet corn soup", 119);
        restaurant.addToMenu("Vegetable lasagne", 269);
    }


    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE


        restaurant = Mockito.spy(restaurant);

        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("10:31:00"), LocalTime.parse("11:29:00"), LocalTime.parse("19:59:00"));

        assertTrue(restaurant.isRestaurantOpen());
        assertTrue(restaurant.isRestaurantOpen());
        assertTrue(restaurant.isRestaurantOpen());
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() {
        //WRITE UNIT TEST CASE HERE

        //WRITE UNIT TEST CASE HERE


        restaurant = Mockito.spy(restaurant);

        Mockito.when(restaurant.getCurrentTime()).thenReturn(LocalTime.parse("01:30:00"), LocalTime.parse("10:29:00"), LocalTime.parse("22:01:00"));

        assertFalse(restaurant.isRestaurantOpen());
        assertFalse(restaurant.isRestaurantOpen());
        assertFalse(restaurant.isRestaurantOpen());
    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1() {


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie", 319);
        assertEquals(initialMenuSize + 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {


        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize - 1, restaurant.getMenu().size());
    }

    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {

        assertThrows(itemNotFoundException.class,
                () -> restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //<<<<<<<<<<<<<<<<<<<<<<<ORDER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    @Test
    public void when_add_one_menu_item_sweet_corn_soup_order_price_would_be_199() {
        List<Item> items = restaurant.getMenuItem("Sweet corn soup");

        int orderPrice = items.stream().mapToInt(o -> o.getPrice()).sum();

        assertEquals(Integer.valueOf(119), orderPrice);

    }

    @Test
    public void when_add_b_menu_item_sweet_corn_soup_and_vegetable_lasagne_order_price_338() {
        List<Item> items = restaurant.getMenuItem("Sweet corn soup", "Vegetable lasagne");
        int orderPrice = items.stream().mapToInt(o -> o.getPrice()).sum();

        assertEquals(Integer.valueOf(388), orderPrice);
    }

    @Test
    public void when_add_all_menu_item_order_price_338() {
        List<Item> items = restaurant.getMenuItem("Sweet corn soup", "Vegetable lasagne");
        int orderPrice = items.stream().mapToInt(o -> o.getPrice()).sum();

        assertEquals(Integer.valueOf(388), orderPrice);
    }

    //<<<<<<<<<<<<<<<<<<<<<<<ORDER>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


}