import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)

class RestaurantTest {
    Restaurant restaurant;

    @BeforeEach
    public void setup(){
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant my_res =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
    }

    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time() throws Exception {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant my_res = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        LocalTime time = LocalTime.parse("11:00:00");
        assertTrue(my_res.isRestaurantOpen(time));
    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time() throws Exception {
        LocalTime openingTime = LocalTime.parse("10:30:00");
        LocalTime closingTime = LocalTime.parse("22:00:00");
        Restaurant my_res = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        LocalTime time = LocalTime.parse("23:00:00");
        assertFalse(my_res.isRestaurantOpen(time));
    }




    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
}